package fer.infsus.VGR.controller;

import fer.infsus.VGR.model.*;
import fer.infsus.VGR.repository.GameRepo;
import fer.infsus.VGR.repository.GenreRepo;
import fer.infsus.VGR.repository.PlatformRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/platform/")
public class PlatformController {
    private final PlatformRepo platformRepo;
    private final GameRepo gameRepo;

    @GetMapping
    public ResponseEntity<List<Platform>> fetchAllPlatform() {
        List<Platform> platforms = platformRepo.findAll();

        return new ResponseEntity<>(platforms, HttpStatus.OK);
    }

    @GetMapping("{platformId}")
    public ResponseEntity<List<Game>> fetchGamesByGenre(@PathVariable("platformId") Integer platformId) {
        if (platformRepo.existsById(platformId)) {
            Platform platform = platformRepo.getById(platformId);
            List<Game> games = gameRepo.findAllByPlatformsContaining(platform);

            return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Game>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }
}
