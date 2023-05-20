package fer.infsus.VGR.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@Entity
@Table(name="igrica")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_igrica")
    private Integer id;

    @Column(name = "naziv_igrica")
    private String name;

    @Column(name = "datum")
    private Date publishedDate;

    @Column(name = "studio")
    private String creatorStudio;

    @Column(name = "sadrzaj")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "igricaplatforma",
            joinColumns = @JoinColumn(name = "id_igrica"),
            inverseJoinColumns = @JoinColumn(name = "sifra_platforma")
            )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Platform> platforms;

    @ManyToMany
    @JoinTable(
            name = "igricazanr",
            joinColumns = @JoinColumn(name = "id_igrica"),
            inverseJoinColumns = @JoinColumn(name = "sifra_zanr")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Genre> genres;

}
