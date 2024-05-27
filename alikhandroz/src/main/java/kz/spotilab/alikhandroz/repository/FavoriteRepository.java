package kz.spotilab.alikhandroz.repository;

import kz.spotilab.alikhandroz.domain.FavoriteEntity;
import kz.spotilab.alikhandroz.domain.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoriteId> {
}
