package kz.spotilab.alikhandroz.repository;

import kz.spotilab.alikhandroz.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
