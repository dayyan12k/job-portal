package com.JB2.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;
    private int salary;
    @ManyToOne
    @JsonIgnore
    private User postedBy;
    @ManyToOne
    @JsonIgnore
    private Company company;

    @ManyToMany(fetch=FetchType.EAGER, mappedBy = "appliedJobs" )
    @JsonIgnore
    private List<User> applicants;
    
    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private List<com.JB2.demo.entity.JobApplication> jobApplications;
    
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public int getSalary() { return salary; }

    public void setSalary(int salary) { this.salary = salary; }

    public User getPostedBy() { return postedBy; }

    public void setPostedBy(User postedBy) { this.postedBy = postedBy; }

    public Company getCompany() { return company; }

    public void setCompany(Company company) { this.company = company; }

    public List<User> getApplicants() { return applicants; }

    public void setApplicants(List<User> applicants) { this.applicants = applicants; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public List<com.JB2.demo.entity.JobApplication> getJobApplications() { return jobApplications; }
    
    public void setJobApplications(List<com.JB2.demo.entity.JobApplication> jobApplications) { this.jobApplications = jobApplications; }
}
