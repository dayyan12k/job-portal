package com.JB2.demo.Config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.JB2.demo.Helper.Appconstants;
import com.JB2.demo.Repository.UserRepo;
import com.JB2.demo.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OnAuthenicationSuccessHandler implements AuthenticationSuccessHandler{

    @Autowired
    private UserRepo userRepo;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            String email = user.getAttribute("email").toString();
            String name = user.getAttribute("name").toString();
            String picture = user.getAttribute("picture").toString();
            User user1 = new User();
            user1.setEmail(email);
            user1.setName(name);
            user1.setPassword("password");
            user1.setPicture(picture);
            user1.setRoleList(List.of(Appconstants.ROLE_USER));
            User user2 = userRepo.findByEmail(email).orElse(null);
            if(user2 == null){
                userRepo.save(user1);
                System.out.println("USer Have been saved!!!!!!!!!!");
            }
                //redirecting after successfull authentication
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
