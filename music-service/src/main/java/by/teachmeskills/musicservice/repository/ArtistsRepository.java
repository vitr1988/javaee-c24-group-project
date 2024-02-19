package by.teachmeskills.musicservice.repository;

import by.teachmeskills.musicservice.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistsRepository extends JpaRepository<Artist, Long> {
}
