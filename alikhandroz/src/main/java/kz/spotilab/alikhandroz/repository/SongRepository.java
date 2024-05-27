package kz.spotilab.alikhandroz.repository;

import kz.spotilab.alikhandroz.domain.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<SongEntity, Long> {

}
