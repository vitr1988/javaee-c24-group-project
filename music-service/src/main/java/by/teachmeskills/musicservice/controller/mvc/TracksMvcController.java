package by.teachmeskills.musicservice.controller.mvc;


import by.teachmeskills.musicservice.dto.ArtistDto;
import by.teachmeskills.musicservice.dto.GenreDto;
import by.teachmeskills.musicservice.dto.TrackDto;
import by.teachmeskills.musicservice.service.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tracks")
public class TracksMvcController {

    private final TrackService trackService;

    @GetMapping
    public String showTracksPage(Model model, @RequestParam(required = false) String searchTitle,
                                 @RequestParam(required = false) List<ArtistDto> artistDtoList, @RequestParam(required = false) List<GenreDto> genreDtoList) {

        List<TrackDto> trackDtoList = trackService.getAllTracks();

        model.addAttribute("tracks", trackDtoList);

        //TODO реализовать фильтрацию, пагинацию
        return "tracks";
    }

}
