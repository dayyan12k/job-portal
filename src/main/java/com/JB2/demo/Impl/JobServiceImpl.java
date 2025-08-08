package com.JB2.demo.Impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JB2.demo.Repository.JobRepo;
import com.JB2.demo.Repository.UserRepo;
import com.JB2.demo.ServiceInterface.JobService;
import com.JB2.demo.entity.Job;
import com.JB2.demo.entity.User;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepo jobRepo;
    
    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean saveJobForUser(Long jobId, String userEmail) {
        try {
            Optional<Job> jobOpt = jobRepo.findById(jobId);
            Optional<User> userOpt = userRepo.findByEmail(userEmail);
            
            if (jobOpt.isPresent() && userOpt.isPresent()) {
                Job job = jobOpt.get();
                User user = userOpt.get();
                
                // Initialize savedJobs list if it's null
                if (user.getSavedJobs() == null) {
                    user.setSavedJobs(new ArrayList<>());
                }
                
                // Check if job is not already saved
                if (!user.getSavedJobs().contains(job)) {
                    user.getSavedJobs().add(job);
                    userRepo.save(user);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean unsaveJobForUser(Long jobId, String userEmail) {
        try {
            Optional<Job> jobOpt = jobRepo.findById(jobId);
            Optional<User> userOpt = userRepo.findByEmail(userEmail);
            
            if (jobOpt.isPresent() && userOpt.isPresent()) {
                Job job = jobOpt.get();
                User user = userOpt.get();
                
                if (user.getSavedJobs() != null && user.getSavedJobs().contains(job)) {
                    user.getSavedJobs().remove(job);
                    userRepo.save(user);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isJobSavedByUser(Long jobId, String userEmail) {
        try {
            Optional<User> userOpt = userRepo.findByEmail(userEmail);
            Optional<Job> jobOpt = jobRepo.findById(jobId);
            
            if (userOpt.isPresent() && jobOpt.isPresent()) {
                User user = userOpt.get();
                Job job = jobOpt.get();
                
                return user.getSavedJobs() != null && user.getSavedJobs().contains(job);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Job getJobById(Long jobId) {
        return jobRepo.findById(jobId).orElse(null);
    }
}
