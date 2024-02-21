package by.teachmeskills.musicservice.entity;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tracks")
@TypeDef(typeClass = PostgreSQLIntervalType.class, defaultForType = Duration.class)
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "length", nullable = false)
    private Duration length;

    @Column(name = "release")
    private LocalDateTime release;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "downloads")
    private Long downloads;

    @ManyToMany(mappedBy = "tracks")
    private List<Genre> genres;

    @ManyToMany(mappedBy = "tracks")
    private List<Playlist> playlists;

    @OneToMany(mappedBy = "track")
    private List<TrackFile> trackFiles;

}