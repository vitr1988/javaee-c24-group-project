package by.teachmeskills.musicservice.controller.api;

import by.teachmeskills.musicservice.dto.ArtistDto;
import by.teachmeskills.musicservice.service.ArtistService;
import lombok.RequiredArgsConstructor;
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

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getArtists() {
        return ok(artistService.getAllArtists());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable("id") Long id) {
        return ok(artistService.getArtistById(id));
    }

    @PostMapping
    public ResponseEntity<ArtistDto> create(@RequestBody ArtistDto artistDto) {
        ArtistDto result = artistService.save(artistDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ArtistDto> update(@RequestBody ArtistDto artistDto, @PathVariable("id") Long id) {
        ArtistDto updatedArtist = artistService.update(artistDto, id);
        if (updatedArtist == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedArtist);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        artistService.delete(id);
    }


}
