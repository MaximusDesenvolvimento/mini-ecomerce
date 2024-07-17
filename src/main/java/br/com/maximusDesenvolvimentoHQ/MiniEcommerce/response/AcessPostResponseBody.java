package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response;

public class AcessPostResponseBody {
    private String token;

    public AcessPostResponseBody(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
