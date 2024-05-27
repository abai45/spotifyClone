package kz.spotilab.alikhandroz.domain;

import jakarta.persistence.*;
import kz.spotilab.alikhandroz.enums.SubscribtionEnum;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "spotify_user")
@Getter
@Setter
public class UserEntity extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGenerator")
    @SequenceGenerator(name = "userSequenceGenerator", sequenceName = "user_generator", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email")
    private String email;

    private SubscribtionEnum subscribtion = SubscribtionEnum.FREE;

    @Column(name = "image_url")
    private String imageUrl;

    @Override
    public Long getId() {
        return id;
    }
}
