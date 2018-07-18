package org.upgrad.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Notification {

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
}
