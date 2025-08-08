package com.JB2.demo.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.JB2.demo.Helper.Helper;
import com.JB2.demo.Impl.UserServiceImpl;
import com.JB2.demo.entity.User;


//is controller me jitne methods hai wo har ek request klie chalege that @ControllerAdvice means
@ControllerAdvice
public class AddLoggedInUser {

    @Autowired
    private UserServiceImpl userServiceImpl;

     private Logger logger = LoggerFactory.getLogger(AddLoggedInUser.class);

     @ModelAttribute
    public void AddLoggedInUser(Model model,Authentication authentication){   

     if(authentication == null) {
        return;
     }  
     else{
    String username = Helper.getEmailOFLoggedInUsers(authentication);
    User user = userServiceImpl.getUserByEmail(username);
    logger.info("User Logged in :{}" , username);
    System.out.println("User profile");
    model.addAttribute("LoggedInUser", user);
    }
    
    }

}

