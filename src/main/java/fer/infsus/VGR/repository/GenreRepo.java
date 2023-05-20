package fer.infsus.VGR.repository;

import fer.infsus.VGR.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, Integer> {
}
