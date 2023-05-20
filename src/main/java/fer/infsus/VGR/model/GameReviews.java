package fer.infsus.VGR.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class GameReviews {

    private Integer id;
    private String name;
    private Date publishedDate;
    private String creatorStudio;
    private String description;
    private List<Platform> platforms;
    private List<Genre> genres;
    private List<Review> reviews;

    public GameReviews(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.publishedDate = game.getPublishedDate();
        this.creatorStudio = game.getCreatorStudio();
        this.description = game.getDescription();
        this.platforms = game.getPlatforms();
        this.genres = game.getGenres();
    }
}
