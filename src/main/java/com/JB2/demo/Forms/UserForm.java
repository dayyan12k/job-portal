package com.JB2.demo.Forms;

public class UserForm {
    private String name;
    private String email;
    private String password;
    private Long phoneNumber;
    private String address;
    private String LinkedIn;

    // No-args constructor
    public UserForm() {
    }

    // All-args constructor
    public UserForm(String name, String email, String password, Long phoneNumber, String address, String LinkedIn) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.LinkedIn = LinkedIn;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

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

    // Builder pattern
    public static class Builder {
        private String name;
        private String email;
        private String password;
        private Long phoneNumber;
        private String address;
        private String LinkedIn;

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

        public Builder linkedIn(String linkedIn) {
            this.LinkedIn = linkedIn;
            return this;
        }

        public UserForm build() {
            return new UserForm(name, email, password, phoneNumber, address, LinkedIn);
        }
    }
}
