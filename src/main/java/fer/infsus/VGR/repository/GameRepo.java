package fer.infsus.VGR.repository;

import fer.infsus.VGR.model.Game;
import fer.infsus.VGR.model.Genre;
import fer.infsus.VGR.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepo extends JpaRepository<Game, Integer> {
    List<Game> findAllByGenresContaining(Genre genre);
    List<Game> findAllByPlatformsContaining(Platform platform);
}
