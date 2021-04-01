package com.alevel.module3.model;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    //уникальный идентификатор
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //максимальная длина 100 символов
    @Column(name="title",length = 100)
    private String title;

    //максимальная длина 1000 символов
    @Column(name="content",length = 1000)
    private String content;

    //автор сатьи
    @ManyToOne
    @JoinColumns(
            @JoinColumn(
                    name = "author_id",
                    referencedColumnName = "id"
            )
    )
    private User author;

    //модератор (тот кто апрувит статью)
    @ManyToOne
    @JoinColumns(
            @JoinColumn(
                    name = "moderator_id",
                    referencedColumnName = "id"
            )
    )
    private User moderator;

    //рейтинг статьи, может быть как отрицательный так и положительный
    @Column(name="rating")
    private int rating;

    //статус
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getModerator() {
        return moderator;
    }

    public void setModerator(User moderator) {
        this.moderator = moderator;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", moderator=" + moderator +
                ", rating=" + rating +
                ", status=" + status +
                '}';
    }
}
