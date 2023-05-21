package fer.infsus.VGR.ControllerTest;

import fer.infsus.VGR.controller.GameController;
import fer.infsus.VGR.model.Game;
import fer.infsus.VGR.model.GameReviews;
import fer.infsus.VGR.model.Review;
import fer.infsus.VGR.repository.GameRepo;
import fer.infsus.VGR.repository.ReviewRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GameControllerTest {

    @Mock
    private GameRepo gameRepo;

    @Mock
    private ReviewRepo reviewRepo;

    private GameController gameController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        gameController = new GameController(gameRepo, reviewRepo);
    }

    @Test
    public void testFetchGames() {
        Game game1 = new Game();
        game1.setId(1);
        game1.setName("Test igra 1");

        Game game2 = new Game();
        game2.setId(2);
        game2.setName("Test igra 2");

        List<Game> games = Arrays.asList(game1, game2);

        when(gameRepo.findAll()).thenReturn(games);

        ResponseEntity<List<Game>> response = gameController.fetchGames();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Test igra 1", response.getBody().get(0).getName());
        assertEquals("Test igra 2", response.getBody().get(1).getName());

        verify(gameRepo, times(1)).findAll();
        verifyNoMoreInteractions(gameRepo);
    }

    @Test
    public void testCreateGame() {
        Game game = new Game();
        game.setId(1);
        game.setName("Test igra 1");

        when(gameRepo.save(game)).thenReturn(game);

        ResponseEntity<Game> response = gameController.createGame(game);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test igra 1", response.getBody().getName());

        verify(gameRepo, times(1)).save(game);
        verifyNoMoreInteractions(gameRepo);
    }

    @Test
    public void testDeleteGame_ExistingGame() {
        int gameId = 1;

        when(gameRepo.existsById(gameId)).thenReturn(true);

        ResponseEntity<String> response = gameController.deleteGame(gameId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Game has been deleted", response.getBody());

        verify(gameRepo, times(1)).existsById(gameId);
        verify(gameRepo, times(1)).deleteById(gameId);
        verifyNoMoreInteractions(gameRepo);
    }

    @Test
    public void testDeleteGame_NonExistingGame() {
        int gameId = 1;

        when(gameRepo.existsById(gameId)).thenReturn(false);

        ResponseEntity<String> response = gameController.deleteGame(gameId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Game does not exist", response.getBody());

        verify(gameRepo, times(1)).existsById(gameId);
        verifyNoMoreInteractions(gameRepo);
    }
}
