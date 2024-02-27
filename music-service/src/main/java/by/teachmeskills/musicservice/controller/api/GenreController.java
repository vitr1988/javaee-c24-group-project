package by.teachmeskills.musicservice.controller.api;

import by.teachmeskills.musicservice.dto.GenreDto;
import by.teachmeskills.musicservice.service.GenreService;
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

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDto>> getGenres() {
        return ok(genreService.getAllGenres());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable("id") Long id) {
        return ok(genreService.getGenreById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GenreDto> create(@RequestBody @Valid GenreDto genreDto) {
        GenreDto result = genreService.save(genreDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GenreDto> update(@RequestBody @Valid GenreDto genreDto, @PathVariable("id") Long id) {
        GenreDto updatedGenre = genreService.update(genreDto, id);
        if (updatedGenre == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedGenre);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        genreService.delete(id);
    }

}
