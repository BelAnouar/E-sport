package org.esport.Repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.Setter;
import org.esport.Model.Equipe;
import org.esport.Repository.TeamRepository;
import org.esport.Repository.base.BaseRepository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.Optional;


@Setter
public class TeamRepositoryImpl extends BaseRepository<Equipe> implements TeamRepository {

    @PersistenceContext
    private EntityManager em;

    public TeamRepositoryImpl() {
        super(Equipe.class);
    }

    public Optional<Equipe> findEquipeWithPlayers(int id) {
        String jpql = "SELECT e FROM Equipe e LEFT JOIN FETCH e.joueurs WHERE e.id = :id";
        TypedQuery<Equipe> query = entityManager.createQuery(jpql, Equipe.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findFirst();
    }

}
