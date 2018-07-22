package org.upgrad.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.upgrad.models.User;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Notification {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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


    public String getUser() {
        return user.getUserName();
    }

    public void setUser(User user) {
        this.user = user;
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
                ", user_id='" + user + '\'' +
                ", message='" + message + '\'' +
                ", read='" + read + '\'' +
                '}';
    }
}
