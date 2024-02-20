package by.teachmeskills.musicservice.repository;

import by.teachmeskills.musicservice.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
