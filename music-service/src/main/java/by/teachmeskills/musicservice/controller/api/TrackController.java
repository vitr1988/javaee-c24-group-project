package by.teachmeskills.musicservice.controller.api;

import by.teachmeskills.musicservice.dto.TrackDto;
import by.teachmeskills.musicservice.service.TrackService;
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

@RestController
@RequestMapping("/api/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @GetMapping
    public List<TrackDto> getTracks() {
        return trackService.getAllTracks();
    }

    @GetMapping(value = "/{id}")
    public TrackDto getTrackById(@PathVariable("id") Long id) {
        return trackService.getTrackById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackDto create(@RequestBody TrackDto trackDto) {
        return trackService.save(trackDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TrackDto> update(@RequestBody TrackDto trackDto, @PathVariable("id") Long id) {
        TrackDto updatedTrack = trackService.update(trackDto, id);
        if (updatedTrack == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedTrack);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        trackService.delete(id);
    }

}
