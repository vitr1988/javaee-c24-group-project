package by.teachmeskills.musicservice.controller.api;

import by.teachmeskills.musicservice.dto.TrackDto;
import by.teachmeskills.musicservice.service.FileService;
import by.teachmeskills.musicservice.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;
    private final FileService fileService;

    @GetMapping
    public List<TrackDto> getTracks() {
        return trackService.getAllTracks();
    }

    @GetMapping(value = "/{id}")
    public TrackDto getTrackById(@PathVariable("id") Long id) {
        return trackService.getTrackById(id);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadTrackFile(@PathVariable Long id) {

        Resource resource = fileService.downloadFile(id);

        if (resource.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + UriUtils.encode(resource.getFilename(), StandardCharsets.UTF_8) + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackDto create(@RequestPart(name = "data") TrackDto trackDto, @RequestPart(name = "file", required = false) MultipartFile file) {
        TrackDto result = trackService.save(trackDto);
        if (file != null) {
            fileService.uploadFile(file, result);
        }
        return result;
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

    @DeleteMapping("/{id}/file")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFile(@PathVariable Long id) {
        fileService.removeFile(id);
    }
}
