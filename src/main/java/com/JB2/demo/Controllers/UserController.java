package com.JB2.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.JB2.demo.Helper.Helper;
import com.JB2.demo.Impl.JobApplicationServiceImpl;
import com.JB2.demo.Impl.UserServiceImpl;
import com.JB2.demo.entity.User;

@Controller
public class UserController {


  @Autowired
  JobApplicationServiceImpl jobApplication;
  @Autowired
  UserServiceImpl userServiceImpl;
   //dashbaord controller 
  @RequestMapping("/user/dashboard")  
  public String dashboard(){
    return "user/dashboard";
  }

  //profile controller
    @RequestMapping("/user/profile")  
  public String profile(Model model, Authentication authentication){
    String email = Helper.getEmailOFLoggedInUsers(authentication);
    Long applicationCount  = jobApplication.countApplicationsByUser(email);
    model.addAttribute("applicationCount",applicationCount);
    User user  = userServiceImpl.getUserByEmail(email);
    int savedJobsCount = user.getSavedJobs().size();
    model.addAttribute("savedJobsCount",savedJobsCount);

    return "user/profile";
  }
}
