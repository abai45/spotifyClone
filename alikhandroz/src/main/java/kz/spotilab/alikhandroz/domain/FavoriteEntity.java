package kz.spotilab.alikhandroz.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "favorite_song")
@IdClass(FavoriteId.class)
public class FavoriteEntity implements Serializable {
    @Id
    private UUID songPublicId;

    @Id
    @Column(name = "user_email")
    private String userEmail;

}
