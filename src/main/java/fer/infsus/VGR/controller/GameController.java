package fer.infsus.VGR.controller;

import fer.infsus.VGR.model.Game;
import fer.infsus.VGR.model.GameReviews;
import fer.infsus.VGR.model.Review;
import fer.infsus.VGR.repository.GameRepo;
import fer.infsus.VGR.repository.ReviewRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/games/")
public class GameController {
    private final GameRepo gameRepo;
    private final ReviewRepo reviewRepo;

    @GetMapping
    public ResponseEntity<List<Game>> fetchGames() {
        List<Game> games = gameRepo.findAll();

        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("{gameId}")
    public ResponseEntity<GameReviews> fetchGameById(@PathVariable("gameId") Game game) {
        GameReviews gameReviews = new GameReviews(game);
        List<Review> reviews = reviewRepo.findByGameId(game.getId());
        gameReviews.setReviews(reviews);

        return new ResponseEntity<>(gameReviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
            gameRepo.save(game);
            return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    @DeleteMapping("{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable("gameId") Integer gameId) {
        if (gameRepo.existsById(gameId)) {
            gameRepo.deleteById(gameId);
            return new ResponseEntity<String>("Game has been deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Game does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        if (gameRepo.existsById(game.getId())) {
            Game oldGame = gameRepo.getById(game.getId());
            oldGame.setCreatorStudio(game.getCreatorStudio());
            oldGame.setGenres(game.getGenres());
            oldGame.setName(game.getName());
            oldGame.setDescription(game.getDescription());
            oldGame.setPublishedDate(game.getPublishedDate());
            oldGame.setPlatforms(game.getPlatforms());
            gameRepo.save(oldGame);
        } else {
            gameRepo.save(game);
        }
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }
}
