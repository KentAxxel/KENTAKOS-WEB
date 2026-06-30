package kentakitos.backend.service;

import kentakitos.backend.entity.AuditLogs;
import kentakitos.backend.repository.AuditLogsRepository;
import kentakitos.backend.util.CustomCipher;
import kentakitos.backend.util.PasswordUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuditLogsService {

    private final AuditLogsRepository auditLogsRepository;
    
    // Llave para tu cifrado César engranajes
    private static final int SECRET_KEY = 5;

    public AuditLogsService(AuditLogsRepository auditLogsRepository) {
        this.auditLogsRepository = auditLogsRepository;
    }

    public void logEvent(String action, String username, String ipAddress, String details) {
        AuditLogs log = new AuditLogs();
        log.setAction(action);
        log.setUsername(username);
        log.setIpAddress(ipAddress);
        
        // Cifrar los detalles usando el Algoritmo César Mejorado del usuario
        String encryptedDetails = CustomCipher.cifrar(details, SECRET_KEY);
        log.setDetails(encryptedDetails);
        
        log.setTimestamp(LocalDateTime.now());

        // Implementación de Cadena de Hash (Blockchain logic)
        Optional<AuditLogs> lastLogOpt = auditLogsRepository.findTopByOrderByIdDesc();
        String previousHash = lastLogOpt.map(AuditLogs::getHash).orElse("GENESIS_BLOCK");
        
        log.setPreviousHash(previousHash);

        // Calcular el hash actual: Hash(previousHash + action + username + encryptedDetails + timestamp)
        String rawDataToHash = previousHash + action + username + encryptedDetails + log.getTimestamp().toString();
        String currentHash = PasswordUtils.hashSHA256(rawDataToHash);
        
        log.setHash(currentHash);

        auditLogsRepository.save(log);
    }
}
