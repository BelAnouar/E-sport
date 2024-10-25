package org.esport.Repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.esport.Model.Jeu;
import org.esport.Repository.GameRepository;
import org.esport.Repository.base.BaseRepository;


public class GameRepositoryImpl extends BaseRepository<Jeu>  implements GameRepository {
    @PersistenceContext
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public GameRepositoryImpl() {
        super(Jeu.class);
    }


}
