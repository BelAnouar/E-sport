package org.esport.Repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Setter;
import org.esport.Model.Tournoi;
import org.esport.Repository.TournamentRepository;
import org.esport.Repository.base.BaseRepository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Setter
public class TournamentRepositoryImpl extends BaseRepository<Tournoi> implements TournamentRepository {
    @PersistenceContext
    private EntityManager em;

    public TournamentRepositoryImpl() {
        super(Tournoi.class);
    }

    public void setEntityManagerFactory(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
    }
}
