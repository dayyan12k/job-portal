package com.JB2.demo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.JB2.demo.Helper.ResourceNotFoundException;
import com.JB2.demo.Repository.UserRepo;

@Service
public class SecurityCustomUserDetailsService implements  UserDetailsService{

    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username).orElseThrow(
            ()-> new ResourceNotFoundException("Invalid username ")
        );
    }
    
}
