package by.teachmeskills.musicservice.repository;

import by.teachmeskills.musicservice.entity.PlaylistTrack;
import by.teachmeskills.musicservice.entity.PlaylistTrackId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, PlaylistTrackId> {

    Optional<PlaylistTrack> findByIdPlaylistIdAndIdTrackId(Long playlistId, Long trackId);
}
