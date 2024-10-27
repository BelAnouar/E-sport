package org.esport.service;

import org.esport.Model.Joueur;
import org.esport.Repository.PlayerRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.esport.Service.impl.PlayerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PlayerServiceTest {

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testAddPlayer() {
        Joueur player = new Joueur();
        player.setId(1);
        player.setAge(19);

        playerService.save(player);

        verify(playerRepository, times(1)).save(player);
    }

    @Test
    public void testFindById() {
        Integer playerId = 1;
        Joueur player = new Joueur();
        player.setId(playerId);
        player.setAge(30);


        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

        Optional<Joueur> foundPlayer = playerService.findById(playerId);


        assertTrue(foundPlayer.isPresent());
        assertEquals(player.getAge(), foundPlayer.get().getAge());

        verify(playerRepository, times(1)).findById(playerId);
    }

    @Test
    public void testFindAll() {
        Joueur player1 = new Joueur();
        player1.setId(1);
        player1.setAge(90);

        Joueur player2 = new Joueur();
        player2.setId(2);
        player2.setAge(19);

        List<Joueur> players = Arrays.asList(player1, player2);


        when(playerRepository.findAll()).thenReturn(players);

        List<Joueur> allPlayers = playerService.findAll();


        assertEquals(2, allPlayers.size());
        assertEquals(90, allPlayers.get(0).getAge());
        assertEquals(19, allPlayers.get(1).getAge());

        verify(playerRepository, times(1)).findAll();
    }

    @Test
    public void testDelete() {
        Integer playerId = 1;


        when(playerRepository.existsById(playerId)).thenReturn(true);

        playerService.delete(playerId);


        verify(playerRepository, times(1)).deleteById(playerId);
    }

    @Test
    public void testUpdate() {
        Joueur player = new Joueur();
        player.setId(1);
        player.setAge(13);


        when(playerRepository.existsById(player.getId())).thenReturn(true);

        playerService.update(player);


        verify(playerRepository, times(1)).update(player);
    }
}