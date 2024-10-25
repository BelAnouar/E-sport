package org.esport.Service.impl;

import lombok.Setter;
import org.esport.Model.Jeu;
import org.esport.Model.Joueur;
import org.esport.Repository.GameRepository;
import org.esport.Repository.base.GenericRepository;
import org.esport.Repository.impl.GameRepositoryImpl;
import org.esport.Repository.impl.TournamentRepositoryImpl;
import org.esport.Service.GameService;
import org.esport.Service.PlayerService;
import org.esport.Service.base.BaseService;

import java.util.List;
import java.util.Optional;

@Setter
public class GameServiceImpl extends BaseService<Jeu> implements GameService {

    private  GameRepository gameRepository ;
    protected GameServiceImpl(GameRepository gameRepository) {
        super(gameRepository);
        this.gameRepository = gameRepository;
    }




    @Override
    public void createGame(Jeu Game) {
        save(Game);
    }

    @Override
    public Optional<Jeu> getGameById(Integer id) {
        return gameRepository.findById(id);
    }

    @Override
    public List<Jeu> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Jeu updateGame(Jeu Game) {
       return gameRepository.save(Game);
    }

    @Override
    public void deleteGame(Integer id) {
      delete(id);
    }



}
