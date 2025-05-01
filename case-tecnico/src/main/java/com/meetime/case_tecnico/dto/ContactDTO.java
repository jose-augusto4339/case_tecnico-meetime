package com.meetime.case_tecnico.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactDTO {

    private String email;

    private String firstname;

    private String lastname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastNnme(String lastName) {
        this.lastname = lastName;
    }
}
