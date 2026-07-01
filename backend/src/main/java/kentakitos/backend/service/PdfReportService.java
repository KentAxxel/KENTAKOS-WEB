package kentakitos.backend.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;
import com.itextpdf.text.pdf.security.PdfPKCS7;
import com.itextpdf.text.pdf.security.PrivateKeySignature;

import kentakitos.backend.entity.AuditLogs;
import kentakitos.backend.repository.AuditLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.List;

@Service
public class PdfReportService {

    @Autowired
    private AuditLogsRepository auditLogsRepository;

    public byte[] generateAndSignAuditReport() throws Exception {
        // 1. Obtener los logs de la BD
        List<AuditLogs> logs = auditLogsRepository.findAll();

        // 2. Crear PDF en memoria
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        
        document.open();
        
        // Título
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph titulo = new Paragraph("Reporte de Auditoría de Seguridad - Kentakitos", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);

        // Tabla
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1.5f, 3f, 2f, 2.5f});

        // Cabeceras
        Font fontCabecera = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        String[] cabeceras = {"Acción", "Detalles", "IP", "Hash"};
        for (String c : cabeceras) {
            PdfPCell cell = new PdfPCell(new Phrase(c, fontCabecera));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        // Datos
        Font fontDatos = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
        for (AuditLogs log : logs) {
            table.addCell(new Phrase(log.getAction(), fontDatos));
            table.addCell(new Phrase(log.getDetails(), fontDatos));
            table.addCell(new Phrase(log.getIpAddress(), fontDatos));
            
            // Hash truncado para que quepa en el PDF
            String hash = log.getHash() != null && log.getHash().length() > 20 
                          ? log.getHash().substring(0, 20) + "..." : log.getHash();
            table.addCell(new Phrase(hash, fontDatos));
        }

        document.add(table);
        document.close();

        byte[] unsignedPdf = baos.toByteArray();

        // 3. Firmar Digitalmente el PDF
        return signPdf(unsignedPdf);
    }

    private byte[] signPdf(byte[] pdfBytes) throws Exception {
        // Cargar Keystore (certificado .p12 generado con keytool)
        KeyStore ks = KeyStore.getInstance("PKCS12");
        InputStream is = new ClassPathResource("kentakitos.p12").getInputStream();
        ks.load(is, "kentakitos123".toCharArray());

        String alias = ks.aliases().nextElement();
        PrivateKey pk = (PrivateKey) ks.getKey(alias, "kentakitos123".toCharArray());
        Certificate[] chain = ks.getCertificateChain(alias);

        // Preparar lector y estampador de PDF
        PdfReader reader = new PdfReader(pdfBytes);
        ByteArrayOutputStream baosSigned = new ByteArrayOutputStream();
        
        // PAdES standard, \0 significa firma invisible, append mode false
        PdfStamper stamper = PdfStamper.createSignature(reader, baosSigned, '\0');

        // Configurar apariencia de la firma
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason("Reporte Oficial de Auditoría");
        appearance.setLocation("Servidor de Kentakitos");
        
        // Hacer la firma VISIBLE en la primera página (Abajo a la izquierda)
        com.itextpdf.text.Rectangle rect = new com.itextpdf.text.Rectangle(36, 36, 250, 100);
        appearance.setVisibleSignature(rect, 1, "FirmaDigitalKentakitos");

        // BouncyCastle provider is needed for cryptographic algorithms
        java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        // Crear la firma usando BouncyCastle
        ExternalSignature pks = new PrivateKeySignature(pk, DigestAlgorithms.SHA256, "BC");
        ExternalDigest digest = new BouncyCastleDigest();
        
        MakeSignature.signDetached(appearance, digest, pks, chain, null, null, null, 0, CryptoStandard.CMS);

        return baosSigned.toByteArray();
    }
}
