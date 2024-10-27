package org.esport.presentation;

import org.esport.Model.Equipe;
import org.esport.Model.Jeu;
import org.esport.Model.Joueur;
import org.esport.Model.Tournoi;
import org.esport.Model.enums.Statut;
import org.esport.Service.GameService;
import org.esport.Service.PlayerService;
import org.esport.Service.TeamService;
import org.esport.Service.TournamentService;
import org.hibernate.service.spi.ServiceException;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ConsoleMenu {


    private final TeamService teamService;
    private final TournamentService tournamentService;
    private final GameService gameService;
    private final PlayerService playerService;

    private final Scanner scanner;
    private final DateTimeFormatter dateFormatter;

    public ConsoleMenu(GameService gameService, PlayerService playerService,
                       TeamService teamService, TournamentService tournamentService) {
        this.gameService = gameService;
        this.teamService = teamService;
        this.tournamentService = tournamentService;

        this.playerService = playerService;
        this.scanner = new Scanner(System.in);
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }


    public void start() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = readIntInput("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        handlePlayerMenu();
                        break;
                    case 2:
                        handleTeamMenu();
                        break;
                    case 3:
                        handleTournamentMenu();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (ServiceException | ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n=== E-Sports Management System ===");
        System.out.println("1. Player Management");
        System.out.println("3. Team Management");
        System.out.println("4. Tournament Management");
        System.out.println("0. Exit");
    }


    private void handleTeamMenu() {
        while (true) {
            System.out.println("\n--- Team Management ---");
            System.out.println("1. Create a team");
            System.out.println("2. Edit a team");
            System.out.println("3. Add a player to a team");
            System.out.println("4. Remove a player from a team");
            System.out.println("5. View a team");
            System.out.println("6. View all teams");
            System.out.println("0. Back to Main Menu");

            int choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    createNewTeam();
                    break;
                case 2:
                    editTeam();
                    break;
                case 3:
                    addPlayerToTeam();
                    break;
                case 4:
                    removePlayerFromTeam();
                    break;
                case 5:
                    viewTeam();
                    break;
                case 6:
                    viewAllTeams();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void handlePlayerMenu() {
        while (true) {
            System.out.println("\n--- Player Management ---");
            System.out.println("1. Register a player");
            System.out.println("2. View all players");
            System.out.println("3. View a player");
            System.out.println("4. Edit a player");
            System.out.println("5. Delete a player");
            System.out.println("0. Back to Main Menu");


            int choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    createNewPlayer();
                    break;
                case 2:
                    listAllPlayers();
                    break;
                case 3:
                    getPlayer();
                    break;
                case 4:
                    updatePlayer();
                    break;
                case 5:
                    deletePlayer();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void handleTournamentMenu() {
        while (true) {
            System.out.println("\n=== Tournament Management ===");
            System.out.println("1. Create a tournament");
            System.out.println("2. Edit a tournament");
            System.out.println("3. Add a team to a tournament");
            System.out.println("4. Remove a team from a tournament");
            System.out.println("5. View a tournament");
            System.out.println("6. View all tournaments");
            System.out.println("0. Back to Main Menu");

            int choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    createNewTournament();
                    break;
                case 2:
                    listAllTournaments();
                    break;
                case 3:
                    getTournament();
                    break;
                case 4:
                    updateTournament();

                    break;
                case 5:
                    addTeamToTournament();
                    break;
                case 6:
                    removeTeamFromTournament();
                    break;
                case 7:
                    calculerdureeEstimeeTournoi();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void updateTournament() {
        int tournamentId = readIntInput("Enter the ID of the tournament to update: ");
        Optional<Tournoi> optionalTournament = tournamentService.getTounamentById(tournamentId);

        if (optionalTournament.isPresent()) {
            Tournoi tournoi = optionalTournament.get();
            System.out.println("Current title: " + tournoi.getTitre());
            String newTitle = readStringInput("Enter new title (or press Enter to keep current): ");
            if (!newTitle.isEmpty()) {
                tournoi.setTitre(newTitle);
            }

            System.out.println("Current start date: " + tournoi.getDateDebut());
            String newStartDateStr = readStringInput("Enter new start date (yyyy-MM-dd) (or press Enter to keep current): ");
            if (!newStartDateStr.isEmpty()) {
                tournoi.setDateDebut(LocalDate.parse(newStartDateStr));
            }

            System.out.println("Current end date: " + tournoi.getDateFin());
            String newEndDateStr = readStringInput("Enter new end date (yyyy-MM-dd) (or press Enter to keep current): ");
            if (!newEndDateStr.isEmpty()) {
                tournoi.setDateFin(LocalDate.parse(newEndDateStr));
            }


            tournamentService.updateTounament(tournoi);
            System.out.println("Tournament updated successfully!");
        } else {
            System.out.println("Tournament with ID " + tournamentId + " not found.");
        }
    }

    private void getTournament() {
        int tournamentId = readIntInput("Enter the ID of the tournament to view: ");
        Optional<Tournoi> optionalTournament = tournamentService.getTounamentById(tournamentId);

        if (optionalTournament.isPresent()) {
            System.out.println(optionalTournament.get());
        } else {
            System.out.println("Tournament with ID " + tournamentId + " not found.");
        }
    }
    private void createNewTournament() {
        System.out.println("\n=== Create New Tournament ===");
        String title = readStringInput("Enter tournament title: ");
        LocalDate startDate = LocalDate.parse(readStringInput("Enter start date (yyyy-MM-dd): "));
        LocalDate endDate = LocalDate.parse(readStringInput("Enter end date (yyyy-MM-dd): "));
        int numberOfSpectators = readIntInput("Enter number of spectators: ");
        LocalTime pauseDuration = LocalTime.parse(readStringInput("Enter pause duration (HH:mm): "));
        LocalTime ceremonyDuration = LocalTime.parse(readStringInput("Enter ceremony duration (HH:mm): "));
        LocalDate estimatedDuration = LocalDate.parse(readStringInput("Enter estimated end date (yyyy-MM-dd): "));

        Tournoi tournoi = new Tournoi();
        tournoi.setTitre(title);
        tournoi.setDateDebut(startDate);
        tournoi.setDateFin(endDate);
        tournoi.setNombre_de_spectateurs(numberOfSpectators);
        tournoi.setTempsPause(pauseDuration);
        tournoi.setTempsCeremonie(ceremonyDuration);
        tournoi.setDureeEstimee(estimatedDuration);

        Jeu jeu = gameService.getGameById(readIntInput("Enter game ID: ")).orElse(null);
        if (jeu != null) {
            tournoi.setJeu(jeu);
        }

        tournamentService.createTounament(tournoi);
        System.out.println("Tournament created successfully!");
    }




    private void createNewPlayer() {
        System.out.println("\n=== Create New Player ===");
        String name = readStringInput("Enter player name: ");
        int age = readIntInput("Enter player age: ");

        Joueur player = new Joueur();
        player.setAge(age);

        playerService.createPlayer(player);
        System.out.println("Player created successfully!");
    }


    private void listAllPlayers() {
        System.out.println("\n=== List All Players ===");
        List<Joueur> players = playerService.getAllPlayers();
        System.out.println("Total players: " + players.size());
        players.forEach(System.out::println);
    }

    private void getPlayer() {
        int playerId = readIntInput("Enter the ID of the player to view: ");
        Optional<Joueur> optionalPlayer = playerService.getPlayerById(playerId);

        if (optionalPlayer.isPresent()) {
            System.out.println(optionalPlayer.get());
        } else {
            System.out.println("Player with ID " + playerId + " not found.");
        }
    }

    private void updatePlayer() {
        int playerId = readIntInput("Enter the ID of the player to update: ");
        Optional<Joueur> optionalPlayer = playerService.getPlayerById(playerId);

        if (optionalPlayer.isPresent()) {
            Joueur player = optionalPlayer.get();
            System.out.println("Current age: " + player.getAge());
            int newAge = readIntInput("Enter new age (or enter current age to keep): ");
            player.setAge(newAge);
            playerService.updatePlayer(player);
            System.out.println("Player updated successfully!");
        } else {
            System.out.println("Player with ID " + playerId + " not found.");
        }
    }

    private void deletePlayer() {
        int playerId = readIntInput("Enter the ID of the player to delete: ");
        Optional<Joueur> optionalPlayer = playerService.getPlayerById(playerId);

        if (optionalPlayer.isPresent()) {
            playerService.deletePlayer(playerId);
            System.out.println("Player deleted successfully!");
        } else {
            System.out.println("Player with ID " + playerId + " not found.");
        }
    }

    private void listAllTournaments() {
        System.out.println("\n=== List All Tournaments ===");
        List<Tournoi> tournaments = tournamentService.getAllTounaments();
        System.out.println("Total tournaments: " + tournaments.size());
        tournaments.forEach(System.out::println);
    }


    private void createNewTeam() {
        System.out.println("\n=== Create New Team ===");
        String name = readStringInput("Enter team name: ");
        String classement = readStringInput("Enter team ranking: ");

        Equipe team = new Equipe();
        team.setName(name);
        team.setClassement(classement);

        teamService.createTeam(team);
        System.out.println("Team created successfully!");
    }




    private void addTeamToTournament() {
        int tournamentId = readIntInput("Enter the ID of the tournament to add a team to: ");
        Optional<Tournoi> optionalTournament = tournamentService.getTounamentById(tournamentId);

        if (optionalTournament.isPresent()) {
            int teamId = readIntInput("Enter the ID of the team to add: ");
            Optional<Equipe> optionalTeam = teamService.getTeamById(teamId);

            if (optionalTeam.isPresent()) {
                Tournoi tournoi = optionalTournament.get();
                Equipe team = optionalTeam.get();


                int gameId = readIntInput("Enter the ID of the game: ");
                Optional<Jeu> optionalGame = gameService.getGameById(gameId);

                if (optionalGame.isPresent()) {

                    Jeu game = optionalGame.get();
                    tournoi.setJeu(game);
                    System.out.println("Game found and set to tournament.");
                } else {
                    System.out.println("Game with ID " + gameId + " not found. Creating a new game.");
                    Jeu newGame = addNewGame();
                    gameService.createGame(newGame);
                    tournoi.setJeu(newGame);
                    System.out.println("New game created and set to tournament.");
                }


                tournoi.getEquipes().add(team);
                tournamentService.updateTounament(tournoi);
                System.out.println("Team added to tournament successfully!");

            } else {
                System.out.println("Team with ID " + teamId + " not found.");
            }
        } else {
            System.out.println("Tournament with ID " + tournamentId + " not found.");
        }
    }

    private Jeu  addNewGame(){


        Jeu newGame = new Jeu();
        String gameName = readStringInput("Enter game name: ");
        LocalTime averageMatchDuration = readLocalTimeInput("Enter average match duration (HH:MM): ");
        int difficulty = readIntInput("Enter game difficulty: ");

        newGame.setNom(gameName);
        newGame.setDuree_moyenne_match(averageMatchDuration);
        newGame.setDeficulte(difficulty);

       return newGame;
    }

    private void removePlayerFromTeam() {
        int teamId = readIntInput("Enter the ID of the team to remove a player from: ");
        Optional<Equipe> optionalTeam = teamService.getTeamById(teamId);

        if (optionalTeam.isPresent()) {
            int playerId = readIntInput("Enter the ID of the player to remove: ");
            Optional<Joueur> optionalPlayer = playerService.getPlayerById(playerId);

            if (optionalPlayer.isPresent()) {
                Equipe team = optionalTeam.get();
                Joueur player = optionalPlayer.get();
                team.getJoueurs().remove(player);
                teamService.updateTeam(team);
                System.out.println("Player removed from team successfully!");
            } else {
                System.out.println("Player with ID " + playerId + " not found.");
            }
        } else {
            System.out.println("Team with ID " + teamId + " not found.");
        }
    }

    private void removeTeamFromTournament() {
        int tournamentId = readIntInput("Enter the ID of the tournament to remove a team from: ");
        Optional<Tournoi> optionalTournament = tournamentService.getTounamentById(tournamentId);

        if (optionalTournament.isPresent()) {
            int teamId = readIntInput("Enter the ID of the team to remove: ");
            Optional<Equipe> optionalTeam = teamService.getTeamById(teamId);

            if (optionalTeam.isPresent()) {
                Tournoi tournoi = optionalTournament.get();
                Equipe team = optionalTeam.get();
                tournoi.getEquipes().remove(team);
                tournamentService.updateTounament(tournoi);
                System.out.println("Team removed from tournament successfully!");
            } else {
                System.out.println("Team with ID " + teamId + " not found.");
            }
        } else {
            System.out.println("Tournament with ID " + tournamentId + " not found.");
        }
    }

    private void editTeam() {
        int teamId = readIntInput("Enter the ID of the team to edit: ");
        Optional<Equipe> optionalTeam = teamService.getTeamById(teamId);

        if (optionalTeam.isPresent()) {
            Equipe team = optionalTeam.get();
            String newClassement = readStringInput("Enter new ranking (classement) (or press Enter to keep current): ");
            if (!newClassement.isEmpty()) {
                team.setClassement(newClassement);
            }
            teamService.updateTeam(team);
            System.out.println("Team updated successfully!");
        } else {
            System.out.println("Team with ID " + teamId + " not found.");
        }
    }

    private void addPlayerToTeam() {
        int teamId = readIntInput("Enter the ID of the team to add a player to: ");
        Optional<Equipe> optionalTeam = teamService.getTeamById(teamId);

        if (optionalTeam.isPresent()) {
            int playerId = readIntInput("Enter the ID of the player to add: ");
            Optional<Joueur> optionalPlayer = playerService.getPlayerById(playerId);

            if (optionalPlayer.isPresent()) {
                Equipe team = optionalTeam.get();
                Joueur player = optionalPlayer.get();
                System.out.println(team);
                System.out.println(player);
                team.getJoueurs().add(player);
                System.out.println(team);
                teamService.updateTeam(team);
                System.out.println("Player added to tea successfully!");
            } else {
                System.out.println("Player with ID " + playerId + " not found.");
            }
        } else {
            System.out.println("Team with ID " + teamId + " not found.");
        }
    }

    private void viewTeam() {
        int teamId = readIntInput("Enter the ID of the team to view: ");
        Optional<Equipe> optionalTeam = teamService.findEquipeWithPlayers(teamId);

        if (optionalTeam.isPresent()) {
            System.out.println(optionalTeam.get().getJoueurs().size());
            System.out.println(optionalTeam.get());
        } else {
            System.out.println("Team with ID " + teamId + " not found.");
        }
    }

    private void   calculerdureeEstimeeTournoi(){
        int tournamentId = readIntInput("Enter the ID of the tournament to add a team to: ");

        long dureeEstimee = tournamentService.calculerdureeEstimeeTournoi(tournamentId);


        long heures = dureeEstimee / 60;
        long minutes = dureeEstimee % 60;

        System.out.printf("Durée estimée du tournoi : %d heures et %d minutes%n", heures, minutes);

    }
    private void viewAllTeams() {
        System.out.println("\n=== List All Teams ===");
        List<Equipe> teams = teamService.getAllTeams();
        System.out.println("Total teams: " + teams.size());
        teams.forEach(System.out::println);
    }


    private String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private long readLongInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private double readDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private LocalDateTime readDateTime() {
        while (true) {
            try {
                String dateStr = scanner.nextLine().trim();
                return LocalDateTime.parse(dateStr, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter date in format yyyy-MM-dd HH:mm");
            }
        }
    }
    private LocalTime readLocalTimeInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter time in HH:MM format.");
            }
        }
    }

}
