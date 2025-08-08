package com.JB2.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String industry;
    private String description;
    // One company has many jobs
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<Job> jobs;

    // Getters and Setters
    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getIndustry() { return industry; }

    public void setIndustry(String industry) { this.industry = industry; }

    public String getDescription() {return description;}
    public void setDescription(String Description) {this.description = Description;}
    public List<Job> getJobs() { return jobs; }

    public void setJobs(List<Job> jobs) { this.jobs = jobs; }
}

