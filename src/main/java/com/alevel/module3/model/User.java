package com.alevel.module3.model;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    //уникальный идентификатор
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "subscription_id")}
    )
    private List<User> subscriptions = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_followers",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "follower_id")}
    )
    private List<User> followers = new ArrayList<>();

    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    //должен быть уникальным, не null
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    //должен быть уникальным и содержать символ '@'
    @Column(name = "email", nullable = false, unique = true)
    @Check(constraints = "email=\"%@%\"")
    private String email;

    //не меньше 18
    @Column(name = "age")
    @Check(constraints = "age > 18")
    private int age;

    //является ли данный пользователь автором (у пользователя должно быть 1 или более публикаций)
    @Column(name = "is_author")
    private Boolean isAuthor = false;

    //может ли пользователь модерировать статьи
    @Column(name = "is_moderator")
    private Boolean isModerator = false;
    //0 если пользователь не "автор"

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getAuthor() {
        return isAuthor;
    }

    public void setAuthor(Boolean author) {
        isAuthor = author;
    }

    public Boolean getModerator() {
        return isModerator;
    }

    public void setModerator(Boolean moderator) {
        isModerator = moderator;
    }

    public List<User> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        List<Integer> subscriptions_id = new ArrayList<>();
        for (User user: subscriptions) {
            subscriptions_id.add(user.getId());
        }
        List<Integer> followers_id = new ArrayList<>();
        for (User user: followers) {
            followers_id.add(user.getId());
        }
        return "User{" +
                "id=" + id +
                ", subscriptions=" + subscriptions_id +
                ", followers=" + followers_id +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", isAuthor=" + isAuthor +
                ", isModerator=" + isModerator +
                '}';
    }


}
