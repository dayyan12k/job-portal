package com.JB2.demo.Forms;

public class Companyform {

    private Long id;
    private String name;
    private String industry;
    private String description;

    // No-args constructor
    public Companyform() {
    }

    // All-args constructor
    public Companyform(Long id, String name, String industry, String description) {
        this.id = id;
        this.name = name;
        this.industry = industry;
        this.description = description;
    }

    // Getters and Setters
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String industry;
        private String description;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder industry(String industry) {
            this.industry = industry;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Companyform build() {
            return new Companyform(id, name, industry, description);
        }
    }
}
