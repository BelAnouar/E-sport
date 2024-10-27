package org.esport.Service.impl;

import lombok.Setter;
import org.esport.Model.Equipe;
import org.esport.Repository.TeamRepository;
import org.esport.Service.TeamService;
import org.esport.Service.base.BaseService;
import java.util.List;
import java.util.Optional;


@Setter
public class TeamServiceImpl extends BaseService<Equipe> implements TeamService {
    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        super(teamRepository);
        this.teamRepository = teamRepository;
    }
    @Override
    public void createTeam(Equipe team) {
        teamRepository.save(team);
    }

    @Override
    public Optional<Equipe> getTeamById(Integer id) {
        return teamRepository.findById(id);
    }
    @Override
    public List<Equipe> getAllTeams() {
        return teamRepository.findAll();
    }
    @Override
    public Equipe updateTeam(Equipe team) {
        return teamRepository.update(team);
    }

    @Override
    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
    }

    @Override
    public Optional<Equipe> findEquipeWithPlayers(int id) {
        return teamRepository.findEquipeWithPlayers(id);
    }
}
