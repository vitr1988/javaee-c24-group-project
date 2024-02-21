package by.teachmeskills.musicservice.repository;

import by.teachmeskills.musicservice.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}