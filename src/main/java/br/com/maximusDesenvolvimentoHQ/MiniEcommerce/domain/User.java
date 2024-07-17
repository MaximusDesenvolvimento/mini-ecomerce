package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.PasswordAuthentication;

@Document
public class User {

    @Indexed
    private String id;
    private String name;
    private int idade;
    private String password;
    private String userName;
    private String sexo;
    private String telefone;
    private String email;
    private Adress adress;

    public User(String id, String name, int idade, String password, String userName, String sexo, String telefone, String email, Adress adress) {
        this.id = id;
        this.name = name;
        this.idade = idade;
        this.password = password;
        this.userName = userName;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.adress = adress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
