package com.guestbook.service;

import com.guestbook.repository.UserRepository;
import com.guestbook.model.User;
import com.guestbook.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.lang.String.format;

/**
 * Created by douglas.reis on 11/15/2017.
 */
@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = repository.findByUsername(username);

        if (!userOpt.isPresent()) {
            throw new UsernameNotFoundException(format("User not found with login %s", username));
        }

        User user = userOpt.get();
        return new SecurityUser(user.getFirstName(), user.getUsername(), user.getPassword(), authorities(user));
    }

    private Collection<? extends GrantedAuthority> authorities(User user) {
        Collection<GrantedAuthority> result = new ArrayList<>();
        result.add(new SimpleGrantedAuthority(user.getRole()));
        return result;
    }

}
