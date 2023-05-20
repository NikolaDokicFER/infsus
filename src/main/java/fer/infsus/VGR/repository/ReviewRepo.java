package fer.infsus.VGR.repository;

import fer.infsus.VGR.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
    List<Review> findByGameId(Integer gameId);
}
