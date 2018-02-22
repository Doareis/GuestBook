package com.guestbook.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by douglas.reis on 11/15/2017.
 */
public class SecurityUser extends User {

    private static final long serialVersionUID = 1L;

    private String nome;

    public SecurityUser(String nome, String login, String password, Collection<? extends GrantedAuthority> authorities) {
        super(login, password, authorities);

        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}