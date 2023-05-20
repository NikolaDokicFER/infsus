package fer.infsus.VGR.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor

@Entity
@Table(name="recenzija")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recenzija")
    private Integer id;

    @Column(name = "opis")
    private String review;

    @Column(name = "ocjena")
    private Integer rating;

    @Column(name = "id_igrica")
    private Integer gameId;

    @ManyToOne
    @JoinColumn(name = "id_korisnik")
    private User user;
}
