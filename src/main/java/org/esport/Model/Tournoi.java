package org.esport.Model;



import jakarta.persistence.*;
import lombok.*;
import org.esport.Model.enums.Statut;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"jeu","equipes"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tournois")
public class Tournoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String titre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Column(name = "NombreDespec")
    private int Nombre_de_spectateurs;
    private LocalDate dureeEstimee;
    private LocalTime TempsPause;
    private LocalTime TempsCeremonie;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @ManyToOne
    @JoinColumn(name = "jeu_id")
    private  Jeu jeu;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "equipe_tournoi",
            joinColumns = @JoinColumn(name = "tournoi_id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id"))
    private Set<Equipe> equipes;

}
