package module.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    //уникальный идентификатор
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    //должен быть уникальным, не null
    private String login;
    //должен быть уникальным и содержать символ '@'
    private String email;
    //не меньше 18
    private int age;
    //подписки

    private List<Subscription> subscriptions;
    //является ли данный пользователь автором (у пользователя должно быть 1 или более публикаций)
    private Boolean isAuthor;
    //может ли пользователь модерировать статьи
    private Boolean isModerator;
    //0 если пользователь не "автор"
    private List<User> followers;

    public User(EntityManager entityManager) {
        List<Subscription> subscriptions = entityManager.createQuery(
                "select sb " +
                        "from Subscription sb " +
                        "where sb.user.od = :userId", Subscription.class)
                .setParameter( "userId", )
                .getResultList();
    }

    public User() {

    }
}

@Entity
@Table(name = "subscriptions")
class Subscription {
    long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    User user;
}
