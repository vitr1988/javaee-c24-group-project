package by.teachmeskills.musicservice.controller.mvc;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tracks")
public class TracksMvcController {

    @GetMapping
    public String showTracksPage(Model model) {
        //TODO передать в модель данные треков, реализовать фильтрацию, пагинацию
        return "tracks";
    }

}
