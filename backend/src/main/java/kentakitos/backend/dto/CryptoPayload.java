package kentakitos.backend.dto;

public class CryptoPayload {
    private String payload;

    public CryptoPayload() {}

    public CryptoPayload(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
