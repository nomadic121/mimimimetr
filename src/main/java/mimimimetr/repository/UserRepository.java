package mimimimetr.repository;

import mimimimetr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    Optional<User> findOneByName(String name);

}
