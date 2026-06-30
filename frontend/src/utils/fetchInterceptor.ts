import { CustomCipher } from './CustomCipher';

const originalFetch = window.fetch;

window.fetch = async (input: RequestInfo | URL, init?: RequestInit): Promise<Response> => {
    
    // Solo interceptar peticiones a nuestra API
    const urlString = input.toString();
    const isApiRequest = urlString.includes('/api/');
    
    if (isApiRequest && init && init.body) {
        // Encriptar el cuerpo de la petición (JSON)
        try {
            const bodyString = typeof init.body === 'string' ? init.body : JSON.stringify(init.body);
            const encryptedBody = CustomCipher.cifrar(bodyString);
            
            // Enviar un solo objeto con la propiedad payload
            init.body = JSON.stringify({ payload: encryptedBody });
        } catch (e) {
            console.error('Error cifrando la petición:', e);
        }
    }

    const response = await originalFetch(input, init);
    
    if (isApiRequest) {
        // Clonar para no consumir el body original
        const clone = response.clone();
        try {
            const json = await clone.json();
            
            // Si la respuesta tiene el formato { payload: "..." }, descifrarlo
            if (json && typeof json === 'object' && typeof json.payload === 'string') {
                const decryptedStr = CustomCipher.descifrar(json.payload);
                const originalData = JSON.parse(decryptedStr);
                
                // Retornar una nueva respuesta parseada transparente para la app
                return new Response(JSON.stringify(originalData), {
                    status: response.status,
                    statusText: response.statusText,
                    headers: response.headers
                });
            }
        } catch (e) {
            // Si falla el parseo (ej: no era JSON), ignorar
        }
    }

    return response;
};
