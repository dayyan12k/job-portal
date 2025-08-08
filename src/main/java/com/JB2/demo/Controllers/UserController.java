package com.JB2.demo.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

   //dashbaord controller 
  @RequestMapping("/user/dashboard")  
  public String dashboard(){
    return "user/dashboard";
  }

  //profile controller
    @RequestMapping("/user/profile")  
  public String profile(Model model, Authentication authentication){
    return "user/profile";
  }
}
