package org.esport.Repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.Setter;
import org.esport.Model.Jeu;
import org.esport.Model.Tournoi;
import org.esport.Repository.TournamentRepository;
import org.esport.Repository.base.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.Optional;

@Setter
public class TournamentRepositoryImpl extends BaseRepository<Tournoi> implements TournamentRepository {
    @PersistenceContext
    private EntityManager em;

    private static final Logger LOGGER = LoggerFactory.getLogger(TournamentRepositoryImpl.class);


    public TournamentRepositoryImpl() {
        super(Tournoi.class);
    }

    @Override
    public long calculerdureeEstimeeTournoi(int tournoiId) {
        LOGGER.debug("Calcul avancé de la durée estimée pour le tournoi ID: {}", tournoiId);


        Tournoi tournoi = Optional.ofNullable(entityManager.find(Tournoi.class, tournoiId))
                .orElseThrow(() ->{
                    LOGGER.error("Tournoi non trouvé avec l'ID: " + tournoiId);
                    return new EntityNotFoundException("Tournoi non trouvé avec l'ID: " + tournoiId);
                });

        int nombreEquipes = tournoi.getEquipes().size();
        Jeu jeu = tournoi.getJeu();
        long dureeMatchEnMinutes = jeu.getDuree_moyenne_match().toSecondOfDay() / 60;

        long dureeEstimee = (nombreEquipes * dureeMatchEnMinutes * jeu.getDeficulte()) +
                tournoi.getTempsCeremonie().toSecondOfDay() +
                tournoi.getTempsCeremonie().toSecondOfDay();

        LOGGER.info("Durée estimée avancée calculée pour le tournoi {}: {} minutes", tournoiId, dureeEstimee);
        return dureeEstimee;
    }
}
