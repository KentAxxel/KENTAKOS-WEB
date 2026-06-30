package kentakitos.backend.util;

import java.security.SecureRandom;

public class CustomCipher {

    private static final String ANILLO_1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ANILLO_2 = "abcdefghijklmnopqrstuvwxyz";
    private static final String ANILLO_3 = "0123456789.,!@#$%^&*()_+-=";
    private static final String TODOS_LOS_CARACTERES = ANILLO_1 + ANILLO_2 + ANILLO_3;
    private static final SecureRandom random = new SecureRandom();

    /**
     * Cifra un texto utilizando el algoritmo de 'engranajes' (expansión a bloques de 8 con ruido).
     */
    public static String cifrar(String texto, int k) {
        if (texto == null) return null;
        StringBuilder texto_cifrado = new StringBuilder();
        
        // En Java el operador % puede dar números negativos, por lo que aplicamos: ((k % 8) + 8) % 8
        // para asegurar el mismo comportamiento matemático que Python.
        int indice_oculto = ((k % 8) + 8) % 8;

        for (char charItem : texto.toCharArray()) {
            if (charItem == ' ') {
                texto_cifrado.append(' ');
                continue;
            }

            char char_cifrado = '\0';

            if (ANILLO_1.indexOf(charItem) != -1) {
                int p = ANILLO_1.indexOf(charItem);
                int c = ((p + k) % 26 + 26) % 26;
                char_cifrado = ANILLO_1.charAt(c);
            } else if (ANILLO_2.indexOf(charItem) != -1) {
                int p = ANILLO_2.indexOf(charItem);
                int c = ((p - k) % 26 + 26) % 26;
                char_cifrado = ANILLO_2.charAt(c);
            } else if (ANILLO_3.indexOf(charItem) != -1) {
                int p = ANILLO_3.indexOf(charItem);
                int c = ((p + k) % 26 + 26) % 26;
                char_cifrado = ANILLO_3.charAt(c);
            } else {
                texto_cifrado.append(charItem);
                continue;
            }

            StringBuilder bloque = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                if (i == indice_oculto) {
                    bloque.append(char_cifrado);
                } else {
                    bloque.append(TODOS_LOS_CARACTERES.charAt(random.nextInt(TODOS_LOS_CARACTERES.length())));
                }
            }
            texto_cifrado.append(bloque.toString());
        }
        return texto_cifrado.toString();
    }

    /**
     * Descifra un texto que fue encriptado con el algoritmo de 'engranajes'.
     */
    public static String descifrar(String texto_cifrado, int k) {
        if (texto_cifrado == null) return null;
        StringBuilder texto_descifrado = new StringBuilder();
        int indice_oculto = ((k % 8) + 8) % 8;

        int i = 0;
        while (i < texto_cifrado.length()) {
            char charActual = texto_cifrado.charAt(i);
            
            if (charActual == ' ') {
                texto_descifrado.append(' ');
                i++;
                continue;
            }

            if (TODOS_LOS_CARACTERES.indexOf(charActual) == -1) {
                texto_descifrado.append(charActual);
                i++;
                continue;
            }

            if (i + 8 > texto_cifrado.length()) {
                break; // Texto corrupto o bloque incompleto
            }

            String bloque = texto_cifrado.substring(i, i + 8);
            char char_cifrado = bloque.charAt(indice_oculto);

            if (ANILLO_1.indexOf(char_cifrado) != -1) {
                int c = ANILLO_1.indexOf(char_cifrado);
                int p = ((c - k) % 26 + 26) % 26;
                texto_descifrado.append(ANILLO_1.charAt(p));
            } else if (ANILLO_2.indexOf(char_cifrado) != -1) {
                int c = ANILLO_2.indexOf(char_cifrado);
                int p = ((c + k) % 26 + 26) % 26;
                texto_descifrado.append(ANILLO_2.charAt(p));
            } else if (ANILLO_3.indexOf(char_cifrado) != -1) {
                int c = ANILLO_3.indexOf(char_cifrado);
                int p = ((c - k) % 26 + 26) % 26;
                texto_descifrado.append(ANILLO_3.charAt(p));
            }

            i += 8;
        }
        return texto_descifrado.toString();
    }
}
