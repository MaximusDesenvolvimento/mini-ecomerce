package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.enums.RoleEnum;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Indexed
    private String id;
    private String name;
    private int age;
    private String password;
    private String gender;
    private String phoneNumber;
    private String email;
    private Adress adress;
    private RoleEnum role;

    public User(String id, String name, int age, String password, String gender, String phoneNumber, String email, Adress adress, RoleEnum role) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
