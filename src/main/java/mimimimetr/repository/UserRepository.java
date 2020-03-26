package mimimimetr.repository;

import mimimimetr.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByName(String name);

    Optional<UserEntity> findOneByName(String name);

}
