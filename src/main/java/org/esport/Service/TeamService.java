package org.esport.Service;

import org.esport.Model.Equipe;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    void createTeam(Equipe team);
    Optional<Equipe> getTeamById(Integer id);
    List<Equipe> getAllTeams();
    Equipe updateTeam(Equipe team);
    void deleteTeam(Integer id);

}
