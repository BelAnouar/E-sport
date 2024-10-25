package org.esport.Model;



import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString(exclude = "equipe")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Joueurs")
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;
}
