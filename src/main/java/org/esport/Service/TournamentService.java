package org.esport.Service;


import org.esport.Model.Tournoi;

import java.util.List;
import java.util.Optional;

public interface TournamentService {


    void createTounament(Tournoi Tounament);
    Optional<Tournoi> getTounamentById(Integer id);
    List<Tournoi> getAllTounaments();
    Tournoi updateTounament(Tournoi Tounament);
    void deleteTounament(Integer id);
    long calculerdureeEstimeeTournoi(int tournoiId);
}
