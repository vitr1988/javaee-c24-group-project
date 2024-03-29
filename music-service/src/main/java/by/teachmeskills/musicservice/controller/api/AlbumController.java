package by.teachmeskills.musicservice.controller.api;

import by.teachmeskills.musicservice.dto.AlbumDto;
import by.teachmeskills.musicservice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
@Slf4j
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping
    public List<AlbumDto> getAlbums() {
        return albumService.getAllAlbums();
    }

    @GetMapping(value = "/{id}")
    public AlbumDto getAlbumById(@PathVariable("id") Long id) {
        return albumService.getAlbumById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumDto create(@RequestBody @Valid AlbumDto albumDto) {
        return albumService.save(albumDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AlbumDto> update(@RequestBody @Valid AlbumDto albumDto, @PathVariable("id") Long id) {
        log.info("AlbumDto!!! {}", albumDto);
        AlbumDto updatedAlbum = albumService.update(albumDto, id);
        if (updatedAlbum == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedAlbum);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        albumService.delete(id);
    }
}
