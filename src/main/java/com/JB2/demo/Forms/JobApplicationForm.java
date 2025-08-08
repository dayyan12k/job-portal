package com.JB2.demo.Forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobApplicationForm {

    @NotBlank(message = "Name is required")
    private String applicantName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String applicantEmail;

    @NotBlank(message = "Experience is required")
    private String experience;

    @NotNull(message = "Resume file is required")
    private MultipartFile resumeFile;

    private Long jobId;

    // Constructors
    public JobApplicationForm() {}

    public JobApplicationForm(String applicantName, String applicantEmail, String experience, 
                             MultipartFile resumeFile, Long jobId) {
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.experience = experience;
        this.resumeFile = resumeFile;
        this.jobId = jobId;
    }

    // Getters and Setters
    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public MultipartFile getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(MultipartFile resumeFile) {
        this.resumeFile = resumeFile;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "JobApplicationForm{" +
                "applicantName='" + applicantName + '\'' +
                ", applicantEmail='" + applicantEmail + '\'' +
                ", experience='" + experience + '\'' +
                ", resumeFile=" + (resumeFile != null ? resumeFile.getOriginalFilename() : "null") +
                ", jobId=" + jobId +
                '}';
    }
}
