package com.JB2.demo.Forms;
import java.util.List;

import com.JB2.demo.entity.Company;
import com.JB2.demo.entity.User;


public class JobForm {
    private Long companyId;
    private String name;
    private String description;
    private String location;
    private int salary;
    private User postedBy;
    private Company company;
    private List<User> applicants;

    // ✅ No-args constructor
    public JobForm() {
    }

    // ✅ All-args constructor
    public JobForm(Long id, String name, String description, String location, int salary,
                   User postedBy, Company company, List<User> applicants) {
        this.companyId = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.postedBy = postedBy;
        this.company = company;
        this.applicants = applicants;
    }

    // ✅ Getters and Setters
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long id) {
        this.companyId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<User> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<User> applicants) {
        this.applicants = applicants;
    }

    // ✅ Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long companyId;
        private String name;
        private String description;
        private String location;
        private int salary;
        private User postedBy;
        private Company company;
        private List<User> applicants;

        public Builder id(Long id) {
            this.companyId = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder salary(int salary) {
            this.salary = salary;
            return this;
        }

        public Builder postedBy(User postedBy) {
            this.postedBy = postedBy;
            return this;
        }

        public Builder company(Company company) {
            this.company = company;
            return this;
        }

        public Builder applicants(List<User> applicants) {
            this.applicants = applicants;
            return this;
        }

        public JobForm build() {
            return new JobForm(companyId, name, description, location, salary, postedBy, company, applicants);
        }
    }
}

