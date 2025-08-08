package com.JB2.demo.ServiceInterface;

import java.util.List;

import com.JB2.demo.Forms.JobApplicationForm;
import com.JB2.demo.entity.JobApplication;

public interface JobApplicationService {
    
    // Apply for a job
    JobApplication applyForJob(JobApplicationForm applicationForm, String userEmail);
    
    // Check if user has already applied for a job
    boolean hasUserAppliedForJob(Long jobId, String userEmail);
    
    // Get all applications for a specific job
    List<JobApplication> getApplicationsForJob(Long jobId);
    
    // Get all applications by a specific user
    List<JobApplication> getApplicationsByUser(String userEmail);
    
    // Get application by ID
    JobApplication getApplicationById(Long applicationId);
    
    // Count applications for a job
    Long countApplicationsForJob(Long jobId);
    
    // Count applications by a user
    Long countApplicationsByUser(String userEmail);
    
    // Delete an application
    boolean deleteApplication(Long applicationId);
    
    // Save resume file and return file path
    String saveResumeFile(org.springframework.web.multipart.MultipartFile file, String applicantEmail);
}
