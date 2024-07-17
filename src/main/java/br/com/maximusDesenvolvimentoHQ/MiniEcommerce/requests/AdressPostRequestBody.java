package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;

import org.springframework.data.mongodb.core.index.Indexed;

public class AdressPostRequestBody {
    private String id;
    private String cep;
    private String rua;
    private String number;

    public AdressPostRequestBody(String id, String cep, String rua, String number) {
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
