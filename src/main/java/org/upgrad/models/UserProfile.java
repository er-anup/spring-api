package org.upgrad.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "user_profile")
public class UserProfile {

    @Id
    @Column(name = "id")
    private int id ;

    @Column(name = "user_id")
    private int userId ;

    @Column(name = "firstname")
    private String firstName ;

    @Column(name = "lastname")
    private String lastName  ;

    @Column(name = "aboutme")
    private String aboutMe ;

    @Column(name = "dob")
    private Date dob ;

    @Column(name = "contactnumber")
    private String contactNumber ;

    @Column(name = "country")
    private String country ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() { return userId;}

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
