package org.upgrad.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_profile")
public class UserProfile implements Serializable {

    @Id
    @Column(name="id")
    private int id;

    @Column
    private int user_id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String aboutMe;

    @Column
    private String contactNNumber;

    @Column
    private String country;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JsonManagedReference
    private User user;


    public UserProfile() {
    }

    public UserProfile(String firstName) {
        this.firstName = firstName;
    }


}
