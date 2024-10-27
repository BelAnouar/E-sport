package org.esport.service;

import org.esport.Model.Equipe;
import org.esport.Repository.TeamRepository;

import org.esport.Service.impl.TeamServiceImpl;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TeamServiceTest {

    @InjectMocks
    private TeamServiceImpl teamService;

    @Mock
    private TeamRepository teamRepository;

    @Before
    public void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTeam() {
        Equipe equipe = new Equipe();
        equipe.setId(1);
        equipe.setName("Test");
        teamService.save(equipe);
        verify(teamRepository, times(1)).save(equipe);
    }




    @Test
    public void testUpdateTeam() {
        Equipe equipe = new Equipe();
        equipe.setId(1);
        equipe.setName("Updated Team");

        when(teamRepository.save(equipe)).thenReturn(equipe);

        Equipe updatedTeam = teamService.save(equipe);


        assertNotNull("The updated team should not be null", updatedTeam);
        assertEquals("Updated Team", updatedTeam.getName());
        verify(teamRepository, times(1)).save(equipe);
    }

    @Test
    public void testFindTeamById() {
        int teamId = 1;
        Equipe equipe = new Equipe();
        equipe.setId(teamId);
        equipe.setName("Test Team");
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(equipe));

        Optional<Equipe> foundTeam = teamService.findById(teamId);

        assertTrue(foundTeam.isPresent());
        assertEquals("Test Team", foundTeam.get().getName());
        verify(teamRepository, times(1)).findById(teamId);
    }

    @Test
    public void testFindAllTeams() {
        Equipe equipe1 = new Equipe();
        equipe1.setId(1);
        equipe1.setName("Team A");

        Equipe equipe2 = new Equipe();
        equipe2.setId(2);
        equipe2.setName("Team B");

        List<Equipe> teamList = Arrays.asList(equipe1, equipe2);
        when(teamRepository.findAll()).thenReturn(teamList);

        List<Equipe> allTeams = teamService.findAll();

        assertEquals(2, allTeams.size());
        assertEquals("Team A", allTeams.get(0).getName());
        assertEquals("Team B", allTeams.get(1).getName());
        verify(teamRepository, times(1)).findAll();
    }


    @Test
    public void testDeleteTeam() {
        Integer teamId = 1;


        when(teamRepository.existsById(teamId)).thenReturn(true);


        teamService.delete(teamId);


        verify(teamRepository, times(1)).deleteById(teamId);
    }
}


