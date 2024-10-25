package org.esport.Service.impl;


import lombok.Setter;
import org.esport.Model.Joueur;

import org.esport.Repository.PlayerRepository;

import org.esport.Service.PlayerService;
import org.esport.Service.base.BaseService;


import java.util.List;
import java.util.Optional;


@Setter
public class PlayerServiceImpl  extends BaseService<Joueur> implements PlayerService{

    private  PlayerRepository playerRepository ;
    protected PlayerServiceImpl(PlayerRepository playerRepository) {
        super(playerRepository);
        this.playerRepository = playerRepository;
    }


    @Override
    public void createPlayer(Joueur player) {
         playerRepository.save(player);
    }

    @Override
    public Optional<Joueur> getPlayerById(Integer id) {
        return playerRepository.findById(id);
    }

    @Override
    public List<Joueur> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public void updatePlayer(Joueur player) {
         playerRepository.update(player);

    }

    @Override
    public void deletePlayer(Integer id) {

        playerRepository.deleteById(id);
    }

    @Override
    public List<Joueur> findPlayersByTeam(Integer teamId) {

        return findPlayersByTeam(teamId);
    }


}