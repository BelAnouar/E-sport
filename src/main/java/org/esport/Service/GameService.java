package org.esport.Service;



import org.esport.Model.Jeu;

import java.util.List;
import java.util.Optional;

public interface GameService {


    void createGame(Jeu Game);
    Optional<Jeu> getGameById(Integer id);
    List<Jeu> getAllGames();
    Jeu updateGame(Jeu Game);
    void deleteGame(Integer id);

}
