package kz.spotilab.alikhandroz.repository;

import kz.spotilab.alikhandroz.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findOneByEmail(String email);
}
