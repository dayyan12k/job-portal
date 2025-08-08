package com.JB2.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.JB2.demo.Forms.JobApplicationForm;
import com.JB2.demo.Helper.Helper;
import com.JB2.demo.Repository.JobRepo;
import com.JB2.demo.Repository.UserRepo;
import com.JB2.demo.entity.Job;
import com.JB2.demo.entity.User;

@Controller
public class ApplyController {

    @Autowired
    private JobRepo jobRepo;
    
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/apply-debug")
    @ResponseBody
    public String debugApply() {
        return "ApplyController is working!";
    }

    @GetMapping("/apply/{jobId}")
    public String showApplyForm(@PathVariable Long jobId, Model model, Authentication authentication) {
        // Check authentication
        if (authentication == null) {
            return "redirect:/login";
        }
        
        // Get job
        Job job = jobRepo.findById(jobId).orElse(null);
        if (job == null) {
            return "redirect:/view_jobs";
        }
        
        // Get user email
        String userEmail = Helper.getEmailOFLoggedInUsers(authentication);
        
        // Create form
        JobApplicationForm applicationForm = new JobApplicationForm();
        applicationForm.setJobId(jobId);
        
        // Pre-fill user data if available
        User user = userRepo.findByEmail(userEmail).orElse(null);
        if (user != null) {
            applicationForm.setApplicantName(user.getName());
            applicationForm.setApplicantEmail(user.getEmail());
        }
        
        // Add to model
        model.addAttribute("job", job);
        model.addAttribute("applicationForm", applicationForm);
        System.out.println("_+++++++++++++++++++++++++++++++++__________________________======================================}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
        return "apply";
    }
}
