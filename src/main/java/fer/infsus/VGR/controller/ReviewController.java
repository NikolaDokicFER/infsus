package fer.infsus.VGR.controller;

import fer.infsus.VGR.model.Review;
import fer.infsus.VGR.repository.ReviewRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/review/")
public class ReviewController {
    private final ReviewRepo reviewRepo;

    @GetMapping
    public ResponseEntity<List<Review>> fetchReviews() {
        List<Review> reviews = reviewRepo.findAll();

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("{reviewId}")
    public ResponseEntity<Review> fetchReviewById(@PathVariable("reviewId") Integer reviewId) {
        if (reviewRepo.existsById(reviewId)) {
            Optional<Review> review = reviewRepo.findById(reviewId);
            return new ResponseEntity<Review>(review.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        reviewRepo.save(review);
        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") Integer reviewId) {
        if (reviewRepo.existsById(reviewId)) {
            reviewRepo.deleteById(reviewId);
            return new ResponseEntity<String>("Review has been deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Review does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Review> updateGame(@RequestBody Review review) {
        if (reviewRepo.existsById(review.getId())) {
            Review oldReview = reviewRepo.getById(review.getId());
            oldReview.setReview(review.getReview());
            oldReview.setRating(review.getRating());
            oldReview.setUser(review.getUser());
            oldReview.setGameId(review.getGameId());
            reviewRepo.save(oldReview);
        } else {
            reviewRepo.save(review);
        }
        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }
}
