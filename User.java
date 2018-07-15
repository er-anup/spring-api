package org.upgrad.models;
import javax.persistence.*;

/*
    Author - Mananpreet Singh
    Date - 2 July, 2018
    Description - Persistence Class for Users table
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private int id ;
    @Column(name = "userName")
    private String userName ;
    @Column(name = "email")
    private String email ;
    @Column(name = "password")
    private String password ;
    @Column(name = "role")
    private String role ;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User_Profile user_profile;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


    public User_Profile getUser_profile() {
        return user_profile;
    }

    public void setUser_profile(User_Profile user_profile) {
        this.user_profile = user_profile;
    }

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }
}
