package com.JB2.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JB2.demo.entity.Job;
import com.JB2.demo.entity.JobApplication;
import com.JB2.demo.entity.User;

@Repository
public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {
    
    // Find all applications for a specific job
    List<JobApplication> findByJob(Job job);
    
    // Find all applications by a specific user
    List<JobApplication> findByApplicant(User applicant);
    
    // Find all applications for a specific job ID
    List<JobApplication> findByJobId(Long jobId);
    
    // Find all applications by a specific user ID
    List<JobApplication> findByApplicantId(Long applicantId);
    
    // Check if a user has already applied for a specific job
    Optional<JobApplication> findByJobAndApplicant(Job job, User applicant);
    
    // Check if a user has already applied for a specific job by IDs
    Optional<JobApplication> findByJobIdAndApplicantId(Long jobId, Long applicantId);
    
    // Find applications by applicant email
    List<JobApplication> findByApplicantEmail(String email);
    
    // Count applications for a specific job
    Long countByJobId(Long jobId);
    
    // Count applications by a specific user
    Long countByApplicantId(Long applicantId);
    // count the applications by the 
    // Long countByUserId(Long userId);
}
