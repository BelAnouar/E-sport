package org.esport.Repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Setter;
import org.esport.Model.Equipe;
import org.esport.Repository.TeamRepository;
import org.esport.Repository.base.BaseRepository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


@Setter
public class TeamRepositoryImpl extends BaseRepository<Equipe> implements TeamRepository {

    @PersistenceContext
    private EntityManager em;

    public TeamRepositoryImpl() {
        super(Equipe.class);
    }

}
