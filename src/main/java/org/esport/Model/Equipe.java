package org.esport.Model;



import jakarta.persistence.*;
import lombok.*;


import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Equipes")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "equipe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Joueur> joueurs;
    @ManyToMany(mappedBy = "equipes" , cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Tournoi> tournois;
    private String classement;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Equipe(id=").append(id)
                .append(", name=").append(name)
                .append(", classement=").append(classement)
                .append(", joueurs=[");

        for (Joueur joueur : joueurs) {
            builder.append("\n  Joueur(id=").append(joueur.getId())
                    .append(", age=").append(joueur.getAge()).append(")");
        }

        builder.append("])");
        return builder.toString();
    }

    public void addJoueur(Joueur joueur) {
        joueurs.add(joueur);
        joueur.setEquipe(this);
    }

    public void removeJoueur(Joueur joueur) {
        joueurs.remove(joueur);
        joueur.setEquipe(null);
    }
}
