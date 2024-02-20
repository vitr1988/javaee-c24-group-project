package by.teachmeskills.musicservice.repository;

import by.teachmeskills.musicservice.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TracksRepository extends JpaRepository<Track, Long> {
}
