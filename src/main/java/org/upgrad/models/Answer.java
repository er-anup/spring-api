package org.upgrad.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    private int userId ;

    @Column(name = "question_id")
    private int questionId;

    @Column(name = "modifiedOn")
    private Date modifiedOn ;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) { this.userId = userId; }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn( Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
