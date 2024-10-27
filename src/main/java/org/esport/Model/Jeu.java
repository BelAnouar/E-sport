package org.esport.Model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Jeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private int deficulte;
    @Column(name = "dureeMoyenne" )
    private LocalTime duree_moyenne_match;
    @OneToMany( mappedBy = "jeu", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Tournoi> tournoi;
}
