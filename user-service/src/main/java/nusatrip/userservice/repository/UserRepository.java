package nusatrip.userservice.repository;

import nusatrip.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByName(String name);

  @Override
  Optional<User> findById(Long aLong);
}