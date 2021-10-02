package com.example.sellers.config;

import com.example.sellers.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //ToDo да настроя достъпа да моите ресурси
        http.authorizeRequests()
                //всички img ше могат да се свалят
                .antMatchers("/img/**", "/js/**" , "/css/**").permitAll()
                //страницата с ресурси може да бъде достъпна само от User с Role ADMIN
                //.antMatchers("/result").hasRole("ADMIN")
                //даваме достъп до следните страници на всички
                .antMatchers("/" , "/users/login" , "/users/register").permitAll()
                //така слагаме защита всички останали страници да не са достъпни
                .antMatchers("/**").authenticated()
                .and().formLogin()
                .loginPage("/users/login")
                .usernameParameter("email")
                .passwordParameter("password")
                //ако е всичко ок отиваме на /home страницата
                .defaultSuccessUrl("/home")
                //ако има проблем го връщаме на страница за грешка
                .failureForwardUrl("/users/login-error");//ToDo да създадем страница за грешка
    }
}
