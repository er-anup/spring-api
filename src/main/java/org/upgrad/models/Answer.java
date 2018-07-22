package org.upgrad.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "ans")
    private String ans ;

    @Column(name = "date")
    private LocalDateTime date ;

    @Column(name = "user_id")
    private int user_id ;

    @Column(name = "question_id")
    private int question_id;

    @Column(name = "modifiedon")
    private LocalDateTime modifiedOn ;

    @Transient
    private User user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
 }
