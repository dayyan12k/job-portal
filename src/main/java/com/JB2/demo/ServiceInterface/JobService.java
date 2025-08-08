package com.JB2.demo.ServiceInterface;

import com.JB2.demo.entity.Job;
import com.JB2.demo.entity.User;

public interface JobService {
    
    boolean saveJobForUser(Long jobId, String userEmail);
    
    boolean unsaveJobForUser(Long jobId, String userEmail);
    
    boolean isJobSavedByUser(Long jobId, String userEmail);
    
    Job getJobById(Long jobId);
}
