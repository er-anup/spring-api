package org.upgrad.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.upgrad.repositories.UserRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class Notification {
    @Id
    private int id;
    private User user;
    private int user_id;
    private String message;
    private LocalDateTime date;
    private Boolean read;
    public Boolean getRead() {
        return read;
    }
    public void setRead(Boolean readStatus) {
        this.read = read;
    }
    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {this.user = user;}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", message='" + message + '\'' +
                ", read='" + read + '\'' +
                '}';
    }

}

