package by.teachmeskills.musicservice.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "playlists_tracks")
public class PlaylistTrack {

    @EmbeddedId
    private PlaylistTrackId id;
}
