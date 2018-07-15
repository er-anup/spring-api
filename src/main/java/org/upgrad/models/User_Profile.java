package org.upgrad.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/*
    Author - Mananpreet Singh
    Date - 2 July, 2018
    Description - Persistence Class for User_profile table
 */
@Entity
public class User_Profile {

    @Id
    private int id ;
    private int user_id ;
    @Column(name = "firstname")
    String firstName ;
    @Column(name = "lastname")
    private String lastName  ;
    private String country ;
    @Column(name = "aboutme")
    private String aboutMe ;
    private Date dob ;  //  "yyyy-mm-dd"
    @Column(name = "contactnumber")
    private String contactNumber ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
