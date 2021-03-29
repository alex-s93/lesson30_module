package module.model;

public class Post {
    //уникальный идентификатор
    private int id;
    //максимальная длина 100 символов
    private String title;
    //максимальная длина 1000 символов
    private String content;
    //автор сатьи
    private User author;
    //модератор (тот кто апрувит статью)
    private User moderator;
    //рейтинг статьи, может быть как отрицательный так и положительный
    private int rating;
    //статус
    private PostStatus status;
}
