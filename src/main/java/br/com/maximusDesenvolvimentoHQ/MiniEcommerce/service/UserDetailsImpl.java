package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private String id;
    private String name;
    private String userName;
    private String email;
    private String password;

    public static  UserDetailsImpl build(User user){
        return new UserDetailsImpl(user.getId(),
                user.getName(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                new ArrayList<>()
                );
    }

    public UserDetailsImpl(String id, String name, String userName,String password, String email, Collection<? extends GrantedAuthority> authoritie) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password=password;
        this.email = email;
        this.authorities = authorities;
    }

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return true;
    }
}
