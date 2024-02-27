package by.teachmeskills.musicservice.controller.api;

import by.teachmeskills.musicservice.dto.PlaylistDto;
import by.teachmeskills.musicservice.service.PlaylistService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Valid
@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PlaylistDto> findAll() {
        return playlistService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlaylistDto findById(@PathVariable Long id) {
        return playlistService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaylistDto save(@Valid @RequestBody PlaylistDto playlistDto) {
        return playlistService.save(playlistDto);
    }

    @PatchMapping("/{playlistId}/{trackId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStateInPlaylist(@PathVariable Long playlistId, @PathVariable Long trackId) {
        playlistService.changeStateOfTrackInPlaylist(playlistId, trackId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        playlistService.delete(id);
    }
}
