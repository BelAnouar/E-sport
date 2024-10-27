package org.esport.Repository;


import org.esport.Model.Equipe;
import org.esport.Repository.base.GenericRepository;

import java.util.Optional;


public interface  TeamRepository extends GenericRepository<Equipe,Integer> {

    Optional<Equipe> findEquipeWithPlayers(int id);

}
