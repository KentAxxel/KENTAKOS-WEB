export class CustomCipher {
    private static ANILLO_1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static ANILLO_2 = "abcdefghijklmnopqrstuvwxyz";
    private static ANILLO_3 = "0123456789.,!@#$%^&*()_+-=";
    private static TODOS = CustomCipher.ANILLO_1 + CustomCipher.ANILLO_2 + CustomCipher.ANILLO_3;
    
    private static SECRET_KEY = 5;

    public static cifrar(texto: string): string {
        if (!texto) return texto;
        
        const k = this.SECRET_KEY;
        let texto_cifrado = "";
        const indice_oculto = ((k % 8) + 8) % 8;

        for (let i = 0; i < texto.length; i++) {
            const charItem = texto[i];

            if (charItem === ' ') {
                texto_cifrado += ' ';
                continue;
            }

            let char_cifrado = '';

            if (this.ANILLO_1.indexOf(charItem) !== -1) {
                const p = this.ANILLO_1.indexOf(charItem);
                const c = ((p + k) % 26 + 26) % 26;
                char_cifrado = this.ANILLO_1[c];
            } else if (this.ANILLO_2.indexOf(charItem) !== -1) {
                const p = this.ANILLO_2.indexOf(charItem);
                const c = ((p - k) % 26 + 26) % 26;
                char_cifrado = this.ANILLO_2[c];
            } else if (this.ANILLO_3.indexOf(charItem) !== -1) {
                const p = this.ANILLO_3.indexOf(charItem);
                const c = ((p + k) % 26 + 26) % 26;
                char_cifrado = this.ANILLO_3[c];
            } else {
                texto_cifrado += charItem;
                continue;
            }

            let bloque = "";
            for (let j = 0; j < 8; j++) {
                if (j === indice_oculto) {
                    bloque += char_cifrado;
                } else {
                    const randomChar = this.TODOS[Math.floor(Math.random() * this.TODOS.length)];
                    bloque += randomChar;
                }
            }
            texto_cifrado += bloque;
        }

        return texto_cifrado;
    }

    public static descifrar(texto_cifrado: string): string {
        if (!texto_cifrado) return texto_cifrado;
        
        const k = this.SECRET_KEY;
        let texto_descifrado = "";
        const indice_oculto = ((k % 8) + 8) % 8;

        let i = 0;
        while (i < texto_cifrado.length) {
            const charActual = texto_cifrado[i];

            if (charActual === ' ') {
                texto_descifrado += ' ';
                i++;
                continue;
            }

            if (this.TODOS.indexOf(charActual) === -1) {
                texto_descifrado += charActual;
                i++;
                continue;
            }

            if (i + 8 > texto_cifrado.length) {
                break;
            }

            const bloque = texto_cifrado.substring(i, i + 8);
            const char_cifrado = bloque[indice_oculto];

            if (this.ANILLO_1.indexOf(char_cifrado) !== -1) {
                const c = this.ANILLO_1.indexOf(char_cifrado);
                const p = ((c - k) % 26 + 26) % 26;
                texto_descifrado += this.ANILLO_1[p];
            } else if (this.ANILLO_2.indexOf(char_cifrado) !== -1) {
                const c = this.ANILLO_2.indexOf(char_cifrado);
                const p = ((c + k) % 26 + 26) % 26;
                texto_descifrado += this.ANILLO_2[p];
            } else if (this.ANILLO_3.indexOf(char_cifrado) !== -1) {
                const c = this.ANILLO_3.indexOf(char_cifrado);
                const p = ((c - k) % 26 + 26) % 26;
                texto_descifrado += this.ANILLO_3[p];
            }

            i += 8;
        }

        return texto_descifrado;
    }
}
