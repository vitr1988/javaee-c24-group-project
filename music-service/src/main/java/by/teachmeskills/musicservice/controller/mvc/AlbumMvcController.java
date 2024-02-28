package by.teachmeskills.musicservice.controller.mvc;

import by.teachmeskills.musicservice.dto.AlbumDto;
import by.teachmeskills.musicservice.dto.ArtistDto;
import by.teachmeskills.musicservice.dto.GenreDto;
import by.teachmeskills.musicservice.dto.TrackDto;
import by.teachmeskills.musicservice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumMvcController {

    private final AlbumService albumService;

    @GetMapping
    public String showAlbumsPage(Model model, @RequestParam(required = false) String searchTitle,
                                 @RequestParam(required = false) List<ArtistDto> artistDtoList, @RequestParam(required = false) List<GenreDto> genreDtoList) {

        List<AlbumDto> albumDtoList = albumService.getAllAlbums();

        model.addAttribute("albums", albumDtoList);
        log.info("ListAlbums: {}", Arrays.toString(albumDtoList.toArray()));

        //TODO реализовать фильтрацию, пагинацию
        return "albums";
    }


}
