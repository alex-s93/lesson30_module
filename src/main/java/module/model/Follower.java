package module.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "followers")
public class Follower {
    long userId;
    @ManyToMany
    @JoinTable(name = "users",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    User followers;
}
