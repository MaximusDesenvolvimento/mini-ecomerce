package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Adress;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.enums.RoleEnum;
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
    @NotEmpty(message = "O campo sexo não pode ser nulo ou vazio")
    private String sexo;
    @NotEmpty(message = "O campo telefone não pode ser nulo ou vazio")
    private String telefone;
    @NotEmpty(message = "O campo email não pode ser nulo ou vazio")
    private String email;
    @NotEmpty(message = "O campo adress não pode ser nulo ou vazio")
    private AdressPostRequestBody adress;

    private RoleEnum role;

    public UserPostRequestBody(String id, String name, int idade, String password, String sexo,
                               String telefone, String email, AdressPostRequestBody adress, RoleEnum role) {
        this.id = id;
        this.name = name;
        this.idade = idade;
        this.password = password;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.adress = adress;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotEmpty(message = "O campo name não pode ser nulo ou vazio") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "O campo name não pode ser nulo ou vazio") String name) {
        this.name = name;
    }

    @NotEmpty(message = "O campo idade não pode ser nulo ou vazio")
    @Min(18)
    public int getIdade() {
        return idade;
    }

    public void setIdade(@NotEmpty(message = "O campo idade não pode ser nulo ou vazio") @Min(18) int idade) {
        this.idade = idade;
    }

    public @NotEmpty(message = "O campo password não pode ser nulo ou vazio") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "O campo password não pode ser nulo ou vazio") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "O campo sexo não pode ser nulo ou vazio") String getSexo() {
        return sexo;
    }

    public void setSexo(@NotEmpty(message = "O campo sexo não pode ser nulo ou vazio") String sexo) {
        this.sexo = sexo;
    }

    public @NotEmpty(message = "O campo telefone não pode ser nulo ou vazio") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotEmpty(message = "O campo telefone não pode ser nulo ou vazio") String telefone) {
        this.telefone = telefone;
    }

    public @NotEmpty(message = "O campo email não pode ser nulo ou vazio") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "O campo email não pode ser nulo ou vazio") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "O campo adress não pode ser nulo ou vazio") AdressPostRequestBody getAdress() {
        return adress;
    }

    public void setAdress(@NotEmpty(message = "O campo adress não pode ser nulo ou vazio") AdressPostRequestBody adress) {
        this.adress = adress;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
