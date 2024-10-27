package org.esport.service;

import org.esport.Model.Tournoi;
import org.esport.Repository.TournamentRepository;
import org.esport.Service.impl.TournamentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class TournamentTest {

    @InjectMocks
    private TournamentServiceImpl tournamentService;
    @Mock
    private TournamentRepository tournamentRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTournoi() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setTitre("Spring Championship");

        tournamentService.save(tournoi);

        verify(tournamentRepository, times(1)).save(tournoi);
    }

    @Test
    public void testGetTournoi() {
        Integer tournoiId = 1;
        Tournoi tournoi = new Tournoi();
        tournoi.setId(tournoiId);
        tournoi.setTitre("Spring Championship");

        when(tournamentRepository.findById(tournoiId)).thenReturn(Optional.of(tournoi));

        Optional<Tournoi> foundTournoi = tournamentService.findById(tournoiId);

        assertTrue(foundTournoi.isPresent());
        assertEquals(tournoi.getTitre(), foundTournoi.get().getTitre());

        verify(tournamentRepository, times(1)).findById(tournoiId);
    }

    @Test
    public void testDeleteTournoi() {
        Integer tournoiId = 1;

        when(tournamentRepository.existsById(tournoiId)).thenReturn(true);

        tournamentService.delete(tournoiId);

        verify(tournamentRepository, times(1)).deleteById(tournoiId);
    }

    @Test
    public void testUpdateTournoi() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setTitre("Updated Championship");

        when(tournamentRepository.existsById(tournoi.getId())).thenReturn(true);

        tournamentService.update(tournoi);

        verify(tournamentRepository, times(1)).update(tournoi);
    }

    @Test
    public void testGetAllTournois() {
        Tournoi tournoi1 = new Tournoi();
        tournoi1.setId(1);
        tournoi1.setTitre("Spring Championship");

        Tournoi tournoi2 = new Tournoi();
        tournoi2.setId(2);
        tournoi2.setTitre("Summer Championship");

        List<Tournoi> tournois = Arrays.asList(tournoi1, tournoi2);

        when(tournamentRepository.findAll()).thenReturn(tournois);

        List<Tournoi> allTournois = tournamentService.findAll();

        assertEquals(2, allTournois.size());
        assertEquals("Spring Championship", allTournois.get(0).getTitre());
        assertEquals("Summer Championship", allTournois.get(1).getTitre());

        verify(tournamentRepository, times(1)).findAll();
    }


}
