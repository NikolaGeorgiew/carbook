package com.example.carbook.config;

import com.example.carbook.model.enums.UserRoleEnum;
import com.example.carbook.repo.UserRepository;
import com.example.carbook.service.impl.CarBookUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final String remembermeKey;


    public SecurityConfiguration(@Value("${carbook.remember.me.key}") String remembermeKey) {
        this.remembermeKey = remembermeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                //Define which urls are visible by which users
                authorizeRequests -> authorizeRequests
                        //All static resources which are situated in js,images, scss, css, fonts are available for anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/fonts","/scss").permitAll()
                        //Allow anyone to see the home page, the registration page,login page, about page, contact page
                        .requestMatchers("/", "/login", "/register", "/about", "/contact", "/login-error","/blog", "/make-admin", "add-trip/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers("/admin-panel").hasRole(UserRoleEnum.ADMIN.name())
                        // all other requests are authenticated
                        .anyRequest().authenticated()

        ).formLogin(
                formLogin -> {
                    formLogin
                            //redirect here when access something which is not allowed.
                            //also this is the page where we perform login.
                            .loginPage("/login")
                            // The names of the input fields (in our case in login.html)
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/", true)
                            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! check
                            .failureForwardUrl("/login-error");
                }
        ).logout(
          logout -> {
              logout
                      //the URL where we should POST something in order to perform the logout
                      .logoutUrl("/logout")
                      //where to go when logged out
                      .logoutSuccessUrl("/")
                      //invalidate the HTTP sesssion
                      .invalidateHttpSession(true);
          }
        ).rememberMe(
                rememberMe -> {
                    rememberMe
                            .key(remembermeKey)
                            .rememberMeParameter("rememberme")
                            .rememberMeCookieName("rememberme");
                }
        ).
                build();

    }
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        //This service translates the carbook users and roles
        //to representation which spring security understands.
        return new CarBookUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
