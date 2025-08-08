package com.JB2.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private Long phoneNumber;
    private String address;
    private String LinkedIn;
    private String picture;
    @ManyToMany
    @JsonIgnore
    private List<Job> appliedJobs;

    @OneToMany(mappedBy = "postedBy")
    @JsonIgnore
    private List<Job> postedJobs;
    
    @ManyToMany
    @JsonIgnore
    private List<Job> savedJobs;
    
    @OneToMany(mappedBy = "applicant")
    @JsonIgnore
    private List<com.JB2.demo.entity.JobApplication> jobApplications;

    // No-args constructor
    public User() {}

    // All-args constructor
    public User(Long id, String name, String email, String password, Long phoneNumber, String address, String LinkedIn,
                List<Job> appliedJobs, List<Job> postedJobs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.LinkedIn = LinkedIn;
        this.appliedJobs = appliedJobs;
        this.postedJobs = postedJobs;
    }

    // Builder
    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private Long phoneNumber;
        private String address;
        private String LinkedIn;
        private String picture;
        private List<Job> appliedJobs;
        private List<Job> postedJobs;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder phoneNumber(Long phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder linkedIn(String LinkedIn) {
            this.LinkedIn = LinkedIn;
            return this;
        }

        public Builder appliedJobs(List<Job> appliedJobs) {
            this.appliedJobs = appliedJobs;
            return this;
        }

        public Builder postedJobs(List<Job> postedJobs) {
            this.postedJobs = postedJobs;
            return this;
        }

        public User build() {
            return new User(id, name, email, password, phoneNumber, address, LinkedIn, appliedJobs, postedJobs);
        }
    }

    // Getters and setters
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

     public String getpicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture =picture;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // public String getPassword() {
    //     return password;
    // }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkedIn() {
        return LinkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.LinkedIn = linkedIn;
    }

    public List<Job> getAppliedJobs() {
        return appliedJobs;
    }

    public void setAppliedJobs(List<Job> appliedJobs) {
        this.appliedJobs = appliedJobs;
    }

    public List<Job> getPostedJobs() {
        return postedJobs;
    }

    public void setPostedJobs(List<Job> postedJobs) {
        this.postedJobs = postedJobs;
    }
    
    public List<Job> getSavedJobs() {
        return savedJobs;
    }
    
    public void setSavedJobs(List<Job> savedJobs) {
        this.savedJobs = savedJobs;
    }
    
    public List<com.JB2.demo.entity.JobApplication> getJobApplications() {
        return jobApplications;
    }
    
    public void setJobApplications(List<com.JB2.demo.entity.JobApplication> jobApplications) {
        this.jobApplications = jobApplications;
    }

   

    //USerDetails Methods
    
    @Override
    public String getPassword() {
       return  this.password;
    }

    @Override
    public String getUsername() {
         return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //a list to store the roles basically 
    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       Collection<SimpleGrantedAuthority> roles= roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
       return roles;
    }

    public void setRoleList(List<String> roleList) {
    this.roleList = roleList;
}
}
