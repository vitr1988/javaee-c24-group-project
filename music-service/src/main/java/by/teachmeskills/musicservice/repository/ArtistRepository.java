package by.teachmeskills.musicservice.repository;

import by.teachmeskills.musicservice.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
