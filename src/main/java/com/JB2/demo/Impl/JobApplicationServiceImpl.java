package com.JB2.demo.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.JB2.demo.Forms.JobApplicationForm;
import com.JB2.demo.Repository.JobApplicationRepo;
import com.JB2.demo.Repository.JobRepo;
import com.JB2.demo.Repository.UserRepo;
import com.JB2.demo.ServiceInterface.JobApplicationService;
import com.JB2.demo.entity.Job;
import com.JB2.demo.entity.JobApplication;
import com.JB2.demo.entity.User;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    private JobApplicationRepo jobApplicationRepo;
    
    @Autowired
    private JobRepo jobRepo;
    
    @Autowired
    private UserRepo userRepo;
    
    private final String RESUME_UPLOAD_DIR = "uploads/resumes/";

    @Override
    public JobApplication applyForJob(JobApplicationForm applicationForm, String userEmail) {
        try {
            // Check if user has already applied
            if (hasUserAppliedForJob(applicationForm.getJobId(), userEmail)) {
                throw new RuntimeException("You have already applied for this job");
            }

            // Get job and user
            Job job = jobRepo.findById(applicationForm.getJobId())
                    .orElseThrow(() -> new RuntimeException("Job not found"));
            
            User user = userRepo.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Save resume file
            String resumeFilePath = saveResumeFile(applicationForm.getResumeFile(), userEmail);

            // Create job application
            JobApplication jobApplication = new JobApplication();
            jobApplication.setApplicantName(applicationForm.getApplicantName());
            jobApplication.setApplicantEmail(applicationForm.getApplicantEmail());
            jobApplication.setExperience(applicationForm.getExperience());
            jobApplication.setResumeFileName(applicationForm.getResumeFile().getOriginalFilename());
            jobApplication.setResumeFilePath(resumeFilePath);
            jobApplication.setJob(job);
            jobApplication.setApplicant(user);

            return jobApplicationRepo.save(jobApplication);
            
        } catch (Exception e) {
            throw new RuntimeException("Error applying for job: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean hasUserAppliedForJob(Long jobId, String userEmail) {
        User user = userRepo.findByEmail(userEmail).orElse(null);
        if (user == null) return false;
        
        return jobApplicationRepo.findByJobIdAndApplicantId(jobId, user.getId()).isPresent();
    }

    @Override
    public List<JobApplication> getApplicationsForJob(Long jobId) {
        return jobApplicationRepo.findByJobId(jobId);
    }

    @Override
    public List<JobApplication> getApplicationsByUser(String userEmail) {
        return jobApplicationRepo.findByApplicantEmail(userEmail);
    }

    @Override
    public JobApplication getApplicationById(Long applicationId) {
        return jobApplicationRepo.findById(applicationId).orElse(null);
    }

    @Override
    public Long countApplicationsForJob(Long jobId) {
        return jobApplicationRepo.countByJobId(jobId);
    }

    @Override
    public Long countApplicationsByUser(String userEmail) {
        User user = userRepo.findByEmail(userEmail).orElse(null);
        if (user == null) return 0L;
        
        return jobApplicationRepo.countByApplicantId(user.getId());
    }

    @Override
    public boolean deleteApplication(Long applicationId) {
        try {
            if (jobApplicationRepo.existsById(applicationId)) {
                jobApplicationRepo.deleteById(applicationId);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String saveResumeFile(MultipartFile file, String applicantEmail) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Resume file is empty");
            }

            // Create uploads directory if it doesn't exist
            Path uploadPath = Paths.get(RESUME_UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            
            String uniqueFilename = applicantEmail.replace("@", "_").replace(".", "_") + 
                                   "_" + UUID.randomUUID().toString() + fileExtension;

            // Save file
            Path filePath = uploadPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return filePath.toString();
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to save resume file: " + e.getMessage(), e);
        }
    }
}
