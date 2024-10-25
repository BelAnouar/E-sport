package org.esport.Service;

import org.esport.Model.Joueur;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    void createPlayer(Joueur player);
    Optional<Joueur> getPlayerById(Integer id);
    List<Joueur> getAllPlayers();
    void updatePlayer(Joueur player);
    void deletePlayer(Integer id);
    List<Joueur> findPlayersByTeam(Integer teamId);
}
