package org.upgrad.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.upgrad.repository.UserRepository;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    private int id;
    private User user;
    @Column(name = "user_id")
    private int userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                ", user_id='" + userId + '\'' +
                ", message='" + message + '\'' +
                ", read='" + read + '\'' +
                '}';
    }
}
