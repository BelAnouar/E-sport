package org.esport.service;

import org.esport.Model.Equipe;
import org.esport.Repository.TeamRepository;

import org.esport.Service.impl.TeamServiceImpl;
import org.junit.Before;
import org.junit.Test;



import org.esport.Model.Equipe;
import org.esport.Repository.TeamRepository;
import org.esport.Service.impl.TeamServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class TeamServiceTest {

    @InjectMocks
    private TeamServiceImpl teamService;  // Injects the mock repository into the service

    @Mock
    private TeamRepository teamRepository;  // Mocks the TeamRepository

    @Before
    public void setUp() {
        // Initializes the mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTeam() {
        // Arrange: Create a team object to be saved
        Equipe equipe = new Equipe();
        equipe.setId(1);
        equipe.setName("Test");

        // Act: Call the method under test
        teamService.save(equipe);

        // Assert: Verify that the repository's save method was called with the correct argument
        verify(teamRepository, times(1)).save(equipe);
    }
}
