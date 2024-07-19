package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.User;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.enums.RoleEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Log4j2
public class UserDetailsImpl implements UserDetails {

    private String id;
    private String name;
    private String userName;
    private String email;
    private String password;
    Collection<? extends GrantedAuthority> authorities;
    private RoleEnum role;

    public static UserDetailsImpl build(User user){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole() == RoleEnum.ROLE_ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        }
        return new UserDetailsImpl(user.getId(),
                user.getName(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                authorities,
                user.getRole());
    }

    public UserDetailsImpl(String id, String name, String userName,String password, String email, Collection<? extends GrantedAuthority> authorities, RoleEnum role) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password=password;
        this.email = email;
        this.authorities = authorities;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
