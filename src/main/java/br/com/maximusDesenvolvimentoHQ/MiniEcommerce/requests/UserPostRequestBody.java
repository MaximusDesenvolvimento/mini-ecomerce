package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Adress;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;


public class UserPostRequestBody {

    @Indexed
    private String id;
    @NotEmpty(message = "O campo name não pode ser nulo ou vazio")
    private String name;
    @NotEmpty(message = "O campo idade não pode ser nulo ou vazio")
    @Min(18)
    private int idade;
    @NotEmpty(message = "O campo password não pode ser nulo ou vazio")
    private String password;
    @NotEmpty(message = "O campo userName não pode ser nulo ou vazio")
    private String userName;
    @NotEmpty(message = "O campo sexo não pode ser nulo ou vazio")
    private String sexo;
    @NotEmpty(message = "O campo telefone não pode ser nulo ou vazio")
    private String telefone;
    @NotEmpty(message = "O campo email não pode ser nulo ou vazio")
    private String email;
    @NotEmpty(message = "O campo adress não pode ser nulo ou vazio")
    private AdressPostRequestBody adress;

    public UserPostRequestBody(String id, String name, int idade, String password, String userName, String sexo, String telefone, String email, AdressPostRequestBody adress) {
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

    public AdressPostRequestBody getAdress() {
        return adress;
    }

    public void setAdress(AdressPostRequestBody adress) {
        this.adress = adress;
    }
}
