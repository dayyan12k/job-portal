package com.JB2.demo.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.JB2.demo.Forms.JobApplicationForm;
import com.JB2.demo.Forms.JobForm;
import com.JB2.demo.Helper.Helper;
import com.JB2.demo.Impl.JobApplicationServiceImpl;
import com.JB2.demo.Impl.JobServiceImpl;
import com.JB2.demo.Repository.CompanyRepo;
import com.JB2.demo.Repository.JobRepo;
import com.JB2.demo.Repository.UserRepo;
import com.JB2.demo.entity.Company;
import com.JB2.demo.entity.Job;
import com.JB2.demo.entity.User;


@Controller
public class JobController {

    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private JobServiceImpl jobService;
    
    @Autowired(required = false)
    private JobApplicationServiceImpl jobApplicationService;

    @GetMapping("/user/post-job")
    public String showPostForm(Model model) {
        JobForm jobForm = new JobForm();
        model.addAttribute("jobForm" , jobForm);
        model.addAttribute("companies" , companyRepo.findAll());
        return "user/post-job";
    }
    @RequestMapping(value="/jobs" , method=RequestMethod.POST)
    public String processJobs(@ModelAttribute JobForm jobForm , Authentication authentication){
        Job job = new Job();
        job.setName(jobForm.getName());
        job.setDescription(jobForm.getDescription());
        job.setSalary(jobForm.getSalary());
        job.setLocation(jobForm.getLocation());
        //getcompany here
        Long Cid = jobForm.getCompanyId();
        Company company = companyRepo.findById(Cid).orElse(null);
        if(company != null){
            job.setCompany(company);
        }
        String username = Helper.getEmailOFLoggedInUsers(authentication);
        User user = userRepo.findByEmail(username).orElse(null);
        if(user != null){
            job.setPostedBy(user);
        }
        jobRepo.save(job);
        //set postedb by here
        return "user/post-job";
    }

    @RequestMapping("/view_jobs")
    public String view_jobs(Model model, Authentication authentication){
        model.addAttribute("jobs", jobRepo.findAll());
        model.addAttribute("helper", Helper.class);
        
        // Add current user email to check saved jobs
        if (authentication != null) {
            String userEmail = Helper.getEmailOFLoggedInUsers(authentication);
            model.addAttribute("currentUserEmail", userEmail);
            model.addAttribute("jobService", jobService);
            model.addAttribute("jobApplicationService", jobApplicationService);
        }
        
        return "view-jobs";
    }

    // Debug endpoints
    // @GetMapping("/apply/test")
    // @ResponseBody
    // public String testApplyEndpoint() {
    //     return "Apply endpoint is working!";
    // }
    
    // @GetMapping("/debug/controller")
    // @ResponseBody
    // public String debugController() {
    //     return "JobController is loaded and working! Time: " + System.currentTimeMillis();
    // }
    
    // Temporarily moved to ApplyController
    // @GetMapping("/apply/{jobId}")
    // public String showApplyForm(@PathVariable Long jobId, Model model, Authentication authentication) {
    //     // Moved to ApplyController for debugging
    // }




    //error line is here
    @PostMapping("/apply")
    public String applyForJob(@ModelAttribute JobApplicationForm applicationForm, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        String userEmail = Helper.getEmailOFLoggedInUsers(authentication);
        jobApplicationService.applyForJob(applicationForm, userEmail);
        return "redirect:/view_jobs";
    }

    @PostMapping("/user/save-job/{jobId}")
    @ResponseBody
    public String saveJob(@PathVariable Long jobId, Authentication authentication) {
        if (authentication == null) {
            return "error";
        }
        
        String userEmail = Helper.getEmailOFLoggedInUsers(authentication);
        boolean saved = jobService.saveJobForUser(jobId, userEmail);
        
        return saved ? "saved" : "error";
    }
    
    @PostMapping("/user/unsave-job/{jobId}")
    @ResponseBody
    public String unsaveJob(@PathVariable Long jobId, Authentication authentication) {
        if (authentication == null) {
            return "error";
        }
        
        String userEmail = Helper.getEmailOFLoggedInUsers(authentication);
        boolean unsaved = jobService.unsaveJobForUser(jobId, userEmail);
        
        return unsaved ? "unsaved" : "error";
    }
    
    @RequestMapping("/user/saved-jobs")
    public String viewSavedJobs(Model model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        
        String userEmail = Helper.getEmailOFLoggedInUsers(authentication);
        User user = userRepo.findByEmail(userEmail).orElse(null);
        
        if (user != null && user.getSavedJobs() != null) {
            model.addAttribute("savedJobs", user.getSavedJobs());
        } else {
            model.addAttribute("savedJobs", new ArrayList<>());
        }
        
        model.addAttribute("helper", Helper.class);
        model.addAttribute("currentUserEmail", userEmail);
        model.addAttribute("jobService", jobService);
        
        return "user/saved-jobs";
    }



     @RequestMapping("/user/applied-jobs")
    public String AppliedJobs(Model model, Authentication authentication){
        String email= Helper.getEmailOFLoggedInUsers(authentication);
        User user = userRepo.findByEmail(email).orElse(null);
        if(user != null && user.getAppliedJobs()!=null){
            model.addAttribute("appliedJobs", user.getAppliedJobs());
        }else{
            model.addAttribute("appliedJobs" , new ArrayList<>());
        }
         model.addAttribute("helper", Helper.class);
        model.addAttribute("currentUserEmail", email);
        model.addAttribute("jobService", jobService);
        System.out.println("{}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
        System.out.println(user.getAppliedJobs());

        return "user/applied-jobs";
    }



    //  // Apply for job mapping
    // @GetMapping("/apply/{jobId}")
    // public String showApplyForm(@PathVariable Long jobId, Model model, Authentication authentication) {
    //     // Check authentication
    //     if (authentication == null) {
    //         return "redirect:/login";
    //     }
        
    //     // Get job
    //     Job job = jobRepo.findById(jobId).orElse(null);
    //     if (job == null) {
    //         return "redirect:/view_jobs";
    //     }
        
    //     // Get user email
    //     String userEmail = Helper.getEmailOFLoggedInUsers(authentication);
        
    //     // Create form
    //     JobApplicationForm applicationForm = new JobApplicationForm();
    //     applicationForm.setJobId(jobId);
        
    //     // Pre-fill user data if available
    //     User user = userRepo.findByEmail(userEmail).orElse(null);
    //     if (user != null) {
    //         applicationForm.setApplicantName(user.getName());
    //         applicationForm.setApplicantEmail(user.getEmail());
    //     }
        
    //     // Add to model
    //     model.addAttribute("job", job);
    //     model.addAttribute("applicationForm", applicationForm);
        
    //     return "apply";
    // }
}
