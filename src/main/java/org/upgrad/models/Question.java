package org.upgrad.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content ;

    @Column(name = "date")
    private LocalDateTime date ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Category> category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUser() {
        return user.getUserName();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Category> getCategories() {
        return category;
    }

    public void setCategories(Set<Category> category) {
        this.category = category;
    }
}
