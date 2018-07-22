package org.upgrad.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel("User")
public class RegisterNewUser {

    @ApiModelProperty(value = "Your First Name", required = true)
    private String firstName;
    @ApiModelProperty(value = "Your Last Name")
    private String lastName;
    @ApiModelProperty(value = "Username", required = true)
    private String username;
    @ApiModelProperty(value = "Your Email ID", required = true)
    private String email;
    @ApiModelProperty(value = "Your Password", required = true)
    private String password;
    @ApiModelProperty(value = "Your Country", required = true)
    private String country;
    @ApiModelProperty(value = "About Me")
    private String aboutMe;
    @ApiModelProperty(value = "Date of Birth (yyyy-MM-dd) ", required = true)
    private String dateOfBirth;
    @ApiModelProperty(value = "Your Phone Number")
    private String phoneNumber;


    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
