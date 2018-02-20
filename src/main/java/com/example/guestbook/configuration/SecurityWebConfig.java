package com.example.guestbook.configuration;

import com.example.guestbook.service.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.example.guestbook.enums.UserRole.USER;

/**
 * Created by douglas.reis on 14/11/17.
 */
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserDetailService detailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/console/**").permitAll().and()
                .authorizeRequests().antMatchers("/templates/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/home").permitAll()
                .and().logout().logoutSuccessUrl("/login").permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/registration", "/registerUser");
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        // in memory simple test implementation
        builder
                .inMemoryAuthentication()
                .withUser("a").password("a").roles(USER.getName());

        builder.userDetailsService(detailService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
