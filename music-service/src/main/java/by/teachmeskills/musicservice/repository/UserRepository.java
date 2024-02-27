package by.teachmeskills.musicservice.repository;

import by.teachmeskills.musicservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
