package com.JB2.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.JB2.demo.Forms.UserForm;
import com.JB2.demo.Impl.UserServiceImpl;
import com.JB2.demo.Repository.JobRepo;
import com.JB2.demo.Repository.UserRepo;
import com.JB2.demo.entity.User;



@Controller
public class PageController {

    @Autowired
    private UserServiceImpl userSerivce;
    
    @Autowired
    private JobRepo jobRepo;
    
    @Autowired
    private UserRepo userRepo;
    @RequestMapping("/home")
    public String home(){
        return "home";
    }
    //about Page
    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    //register Page
   
    @GetMapping("/register")
    public String register(Model model) {
        //jab register call hoga tab ek blank object bhejna hai
        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);
        //also  set the defualt value
       //userForm.setName("dayyan");
        return new String("register");
    }

    //login
    //register Page
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //do-register
    @RequestMapping(value="/do-register" ,method=RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm){
        System.out.print("}{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setAddress(userForm.getAddress());
        user.setLinkedIn(userForm.getLinkedIn());
        userSerivce.saveUser(user);
        return "redirect:/register";
    }

    //service page
     @RequestMapping("/service")
    public String service(){
        return "service";
    }
    
    // Test endpoint to verify controller is working
    @GetMapping("/test-page-mapping")
    @ResponseBody
    public String testPageMapping() {
        return "PageController is working! Time: " + System.currentTimeMillis();
    }
    
   
    
    //add user
    // @RequestMapping("/user/addUser")
    // public ResponseEntity<User> saveUser(@RequestBody User user){
    //     User user1 = userSerivce.saveUser(user);
    //     return new ResponseEntity<>(user1 , HttpStatus.OK);
    // }
}
