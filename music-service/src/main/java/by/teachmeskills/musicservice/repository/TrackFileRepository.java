package by.teachmeskills.musicservice.repository;

import by.teachmeskills.musicservice.entity.TrackFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackFileRepository extends JpaRepository<TrackFile, Long> {
    TrackFile findTrackFileByTrackId(Long id);
}
