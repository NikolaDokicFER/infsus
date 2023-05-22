package fer.infsus.VGR.controller;

import fer.infsus.VGR.model.Game;
import fer.infsus.VGR.model.GameReviews;
import fer.infsus.VGR.model.Genre;
import fer.infsus.VGR.model.Review;
import fer.infsus.VGR.repository.GameRepo;
import fer.infsus.VGR.repository.GenreRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/genre/")
public class GenreController {
    private final GenreRepo genreRepo;
    private final GameRepo gameRepo;

    @GetMapping
    public ResponseEntity<List<Genre>> fetchAllGenre() {
        List<Genre> genres = genreRepo.findAll();

        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @GetMapping("{genreId}")
    public ResponseEntity<List<Game>> fetchGamesByGenre(@PathVariable("genreId") Integer genreId) {
        if (genreRepo.existsById(genreId)) {
            Genre genre = genreRepo.getById(genreId);
            List<Game> games = gameRepo.findAllByGenresContaining(genre);

            return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Game>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }
}
