package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;

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
    private int age;
    @NotEmpty(message = "O campo password não pode ser nulo ou vazio")
    private String password;
    @NotEmpty(message = "O campo sexo não pode ser nulo ou vazio")
    private String gender;
    @NotEmpty(message = "O campo telefone não pode ser nulo ou vazio")
    private String phoneNumber;
    @NotEmpty(message = "O campo email não pode ser nulo ou vazio")
    private String email;
    @NotEmpty(message = "O campo adress não pode ser nulo ou vazio")
    private AdressPostRequestBody adress;

    private RoleEnum role;

    public UserPostRequestBody(String id, String name, int age, String password, String gender,
                               String phoneNumber, String email, AdressPostRequestBody adress, RoleEnum role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
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
    public int getAge() {
        return age;
    }

    public void setAge(@NotEmpty(message = "O campo idade não pode ser nulo ou vazio") @Min(18) int age) {
        this.age = age;
    }

    public @NotEmpty(message = "O campo password não pode ser nulo ou vazio") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "O campo password não pode ser nulo ou vazio") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "O campo sexo não pode ser nulo ou vazio") String getGender() {
        return gender;
    }

    public void setGender(@NotEmpty(message = "O campo sexo não pode ser nulo ou vazio") String gender) {
        this.gender = gender;
    }

    public @NotEmpty(message = "O campo telefone não pode ser nulo ou vazio") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotEmpty(message = "O campo telefone não pode ser nulo ou vazio") String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
