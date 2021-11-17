package com.example.sellers.config;

import com.example.sellers.model.entity.enums.UserRoleEnum;
import com.example.sellers.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // allow access to static resources to anyone
                .antMatchers("/img/**", "/js/**" , "/css/**").permitAll()
                // allow access to index, user login and registration to anyone
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                // така слагаме защита на /statistics да се вижда само от User с роля Admin
                .antMatchers("/statistics").hasRole(UserRoleEnum.ADMIN.name())
                //така слагаме защита всички останали страници да не са достъпни
                .antMatchers("/**").authenticated()
                .and()
                // configure login with HTML form
                .formLogin()
                // our login page will be served by the controller with mapping /users/login
                .loginPage("/users/login")
                // the name of the user name input field in OUR login form is username (optional)
                .usernameParameter("email")
                // the name of the user password input field in OUR login form is password (optional)
                .passwordParameter("password")
                // on login success redirect here
                .defaultSuccessUrl("/home")
                // on login failure redirect here
                .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                // which endpoint performs logout, e.g. http://localhost:8080/logout (!this should be POST request)
                .logoutUrl("/logout")
                // where to land after logout
                .logoutSuccessUrl("/")
                // remove the session from the server
                .invalidateHttpSession(true)
                // delete the session cookie
                .deleteCookies("JSESSIONID");
    }
}
