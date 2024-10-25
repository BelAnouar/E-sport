package org.esport.Service.impl;

import lombok.Setter;
import org.esport.Model.Tournoi;
import org.esport.Repository.TournamentRepository;
import org.esport.Service.TournamentService;
import org.esport.Service.base.BaseService;

import java.util.List;
import java.util.Optional;

@Setter
public class TournamentServiceImpl extends BaseService<Tournoi> implements TournamentService {
    private TournamentRepository tournamentRepository;
    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        super(tournamentRepository);
        this.tournamentRepository = tournamentRepository;
    }


    @Override
    public void createTounament(Tournoi Tounament) {
        tournamentRepository.save(Tounament);
    }

    @Override
    public Optional<Tournoi> getTounamentById(Integer id) {
        return tournamentRepository.findById(id);
    }

    @Override
    public List<Tournoi> getAllTounaments() {
        return tournamentRepository.findAll();
    }

    @Override
    public Tournoi updateTounament(Tournoi Tounament) {
        return tournamentRepository.update(Tounament);
    }

    @Override
    public void deleteTounament(Integer id) {
           tournamentRepository.deleteById(id);
    }
}
