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
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ConsoleMenu {



        private final TeamService teamService;
        private final TournamentService tournamentService;
private final  GameService gameService;
        private final PlayerService playerService;

        private final Scanner scanner;
        private final DateTimeFormatter dateFormatter;

        public ConsoleMenu( GameService gameService, PlayerService playerService,
                           TeamService teamService ,TournamentService tournamentService) {
this.gameService=gameService;
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
                            handleGameMenu();
                            break;
                        case 2:

                            handlePlayerMenu();

                            break;
                        case 3:
                            handleTeamMenu();
                            break;
                        case 4:
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
            System.out.println("1. Game Management");
            System.out.println("2. Player Management");
            System.out.println("3. Team Management");
            System.out.println("4. Tournament Management");
            System.out.println("0. Exit");
        }



        private void handleTeamMenu() {
            while (true) {
                System.out.println("\n=== Team Management ===");
                System.out.println("1. Create New Team");
                System.out.println("2. List All Teams");
                System.out.println("3. View Team Details");
                System.out.println("4. Update Team");
                System.out.println("5. Delete Team");
                System.out.println("0. Back to Main Menu");

                int choice = readIntInput("Enter your choice: ");

                switch (choice) {
                    case 1:
                        createNewTeam();
                        break;
                    case 2:
                        ListAllTeams();
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }


    private void handleGameMenu() {
        while (true) {
            System.out.println("\n=== Team Management ===");
            System.out.println("1. Create New Game");
            System.out.println("2. List All Games");
            System.out.println("3. View Game Details");
            System.out.println("4. Update Game");
            System.out.println("5. Delete Game");
            System.out.println("0. Back to Main Menu");

            int choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    createNewGame();
                    break;
                case 2:
                    ListAllGames();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

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
            System.out.println("\n=== Team Management ===");
            System.out.println("1. Create New Player");
            System.out.println("2. List All Players");
            System.out.println("3. View Player Details");
            System.out.println("4. Update Player");
            System.out.println("5. Delete Player");
            System.out.println("0. Back to Main Menu");

            int choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    createNewPlayer();
                    break;
                case 2:
                    ListAllPlayers();
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
            System.out.println("1. Create New Tournament");
            System.out.println("2. List All Tournaments");
            System.out.println("3. View Tournament Details");
            System.out.println("4. Update Tournament");
            System.out.println("5. Delete Tournament");
            System.out.println("0. Back to Main Menu");

            int choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    createNewTournament();
                    break;
                case 2:
                    ListAllTournaments();
                    break;
                case 3:
                    getTournament();
                    break;
                case 4:
                    updateTournament();

                    break;
                case 5:
                    deleteTournament();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private  void  updateTournament(){
            Tournoi tournoi=tournamentService.getTounamentById(1).get();
            tournoi.setTitre("jfrjlf");
            tournamentService.updateTounament(tournoi);
    }

    private void deleteTournament(){
            tournamentService.deleteTounament(1);
    }
    private void getTournament(){
            System.out.println(tournamentService.getTounamentById(1));
    }
    private  void  createNewTournament() {
        System.out.println("\n=== Create New Tournament ===");
        Tournoi tournoi=new Tournoi();
        tournoi.setTitre("Nouveau Tournoi");
        tournoi.setDateDebut(LocalDate.now().plusDays(30));
        tournoi.setDateFin(LocalDate.now().plusDays(32));
        tournoi.setNombre_de_spectateurs(1000);

        tournoi.setTempsPause(LocalTime.of(0, 30));
        tournoi.setTempsCeremonie(LocalTime.of(1, 0));
        tournoi.setDureeEstimee(LocalDate.now().plusDays(2));
        tournoi.setStatut(Statut.EN_COURS);
       Jeu jeu= gameService.getGameById(1).get();
       tournoi.setJeu(jeu);
        Set<Equipe> equipes = new HashSet<>();
        Equipe equipe = teamService.getTeamById(1).get();
        equipes.add(equipe);
        tournoi.setEquipes(equipes);
        tournamentService.createTounament(tournoi);

    }
    private void createNewGame() {
        System.out.println("\n=== Create New Game ===");
        Jeu game=new Jeu();
        game.setNom("sport");
        game.setDuree_moyenne_match(LocalTime.now());
        game.setDeficulte("moins");
        gameService.createGame(game);
    }
    private void createNewPlayer() {
        System.out.println("\n=== Create New Player ===");
        Joueur player = new Joueur();
        player.setAge(34);
        Equipe equipe= teamService.getTeamById(1).get();
        player.setEquipe(equipe);
        playerService.createPlayer(player);

    }
    private void ListAllGames(){
        System.out.println("\n=== List All Games ===");
        System.out.println(gameService.getAllGames().size());

        gameService.getAllGames().forEach(System.out::println);
    }
    private void ListAllPlayers(){
        System.out.println("\n=== List All Playerd ===");
        System.out.println(playerService.getAllPlayers().size());

        playerService.getAllPlayers().forEach(System.out::println);
    }

    private void getPlayer(){
            Joueur player= playerService.getPlayerById(1).get();
            System.out.println(player);
    }
    private void updatePlayer(){
        Joueur player= playerService.getPlayerById(1).get();
        player.setAge(14);
        playerService.updatePlayer(player);

    }
    private void deletePlayer(){
            playerService.deletePlayer(1);
    }
    private void ListAllTournaments(){
        System.out.println("\n=== List All Teams ===");
        System.out.println(tournamentService.getAllTounaments().size());

        tournamentService.getAllTounaments().forEach(System.out::println);
    }

        private void createNewTeam() {
            System.out.println("\n=== Create New Team ===");
            String name = readStringInput("Enter team name: ");

            Equipe team = new Equipe();
            team.setName(name);
            team.setClassement("kjfklj");
            teamService.createTeam(team);
            System.out.println("Team created successfully!");
        }

        private void  ListAllTeams() {
            System.out.println("\n=== List All Teams ===");
            System.out.println(teamService.getAllTeams().size());

            teamService.getAllTeams().forEach(System.out::println);
        }



        // Utility Methods
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



}
