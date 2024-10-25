package org.esport.Model;



import jakarta.persistence.*;
import lombok.*;


import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Equipes")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "equipe",fetch = FetchType.EAGER)
    private List<Joueur> joueurs;
    @ManyToMany(mappedBy = "equipes" ,fetch = FetchType.EAGER)
    private Set<Tournoi> tournois;
    private String classement;
}
