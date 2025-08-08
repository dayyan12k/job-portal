package com.JB2.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.JB2.demo.Impl.SecurityCustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Autowired
    private OnAuthenicationSuccessHandler handler;
    @Autowired
    SecurityCustomUserDetailsService userDetailsService;
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //have to create the object of userdetailsService that will be interact with the database
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    ///it is use to encrypt the password:
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        
        httpSecurity.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        httpSecurity.formLogin(formLogin ->{
            formLogin.loginPage("/login")
            .loginProcessingUrl("/authenticate")
            .successForwardUrl("/user/profile")
            .failureForwardUrl("/login?error=true")
            .usernameParameter("email")
            .passwordParameter("password");

        });


        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(formLogout -> {
            formLogout.logoutUrl("/do-logout")
            .logoutSuccessUrl("/login?logout=true");
        });



        //oauth configuration
       httpSecurity.oauth2Login(oauth->{
        oauth.loginPage("/login");
        oauth.successHandler(handler);
       });
        
        return httpSecurity.build();

    }
}
