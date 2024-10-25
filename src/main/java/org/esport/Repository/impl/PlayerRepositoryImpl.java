package org.esport.Repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.Setter;
import org.esport.Model.Joueur;
import org.esport.Repository.PlayerRepository;
import org.esport.Repository.base.BaseRepository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;


@Setter
public class PlayerRepositoryImpl extends BaseRepository<Joueur> implements PlayerRepository {


    // or setEm(EntityManagerFactory emf)
    @PersistenceContext
    private EntityManager em;  // or EntityManagerFactory

    public PlayerRepositoryImpl() {
        super(Joueur.class);
    }
}
