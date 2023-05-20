package fer.infsus.VGR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="korisnik")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_korisnik")
    private Integer id;

    @Column(name = "naziv_korisnik")
    private String username;

    @Column(name = "ime")
    private String firstName;

    @Column(name = "lozinka")
    @JsonIgnore
    private String password;

    @Column(name = "email")
    private String email;
}
