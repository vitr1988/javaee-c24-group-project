package by.teachmeskills.musicservice.repository;

import by.teachmeskills.musicservice.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
