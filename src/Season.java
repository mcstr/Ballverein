import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.io.*;
import java.util.Scanner;

public class Season {
    public ArrayList<Team> teamsList = new ArrayList<>();
    public ArrayList<Fixture> fixturesList = new ArrayList<>();
    public Scanner scanner;
    public String teamFileName;
    final String fixtureFileName = "fixture.txt";
    public String name;
    public int numberOfTeams;

    public Season() {
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public void start() {
        boolean run = true;
        this.configuration();
        while (run) {
            try {
                int selected = this.wellcome();
                switch (selected) {
                    case 1:
                        this.addTeam();
                        break;
                    case 2:
                        this.generateFixture();
                        break;
                    case 3:
                        this.showTable();
                        break;
                    case 4:
                        this.registerGameResults();
                        break;
                    case 5:
                        run = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void configuration() {
        Console.printTournamentName();
        this.name = this.scanner.nextLine();
        this.teamFileName = this.name + ".txt";
        if (!new File("Season", this.teamFileName).exists()) {
            Console.printCreatingNewSeason();
            EditSeasonFile.createFile(this.teamFileName);
            EditSeasonFile.createFile(this.fixtureFileName);
            EditSeasonFile.saveFile(this.teamFileName);
            EditSeasonFile.saveFile(this.fixtureFileName);
        } else {
            Console.printLoadingSeason(this.name);
            this.teamsList = EditSeasonFile.readTeamsFirstTime(this.teamFileName);
            this.numberOfTeams = this.teamsList.size();
            this.fixturesList = EditFixtureFile.readFixturesFirstTime(this.fixtureFileName);
            System.out.println("the number of teams is: " + this.teamsList.size());
        }

    }

    public int wellcome() {
        Console.printWellcomeMessage(this.name);
        int selected = this.scanner.nextInt();
        return selected;
    }

    public void addTeam() {
        Console.printaddTeam();
        String name = this.scanner.next();
        Team team = new Team(name);
        this.numberOfTeams++;
        this.teamsList.add(team);
        EditSeasonFile.saveTeams(this.teamsList, this.teamFileName);
    }

    public void generateFixture() {
        
        this.fixturesList.clear();
        int n = this.teamsList.size() - 1;

        for (int i = 1; i < (n + 1); i++) {
            int counter = 0;
            ArrayList<Game> games = new ArrayList<>();
            Fixture fixture = new Fixture(i);
            if (i % 2 == 0) {
                counter ++;
                games.add(new Game(this.teamsList.get(i - 1), this.teamsList.get(n), fixture.id, counter));
            } else {
                counter ++;
                games.add(new Game(this.teamsList.get(n), this.teamsList.get(i - 1), fixture.id, counter));
            }

            for (int k = 1; k < (n + 1) / 2; k++) {
                int tmp = (i + k) % n;
                int teamA = tmp == 0 ? n : tmp;
                tmp = ((i - k % n) + n) % n;
                int teamB = tmp == 0 ? n : tmp;
                if (k % 2 != 0) {
                    counter ++;
                    games.add(new Game(this.teamsList.get(teamA - 1), this.teamsList.get(teamB - 1), fixture.id, counter));
                } else {
                    counter ++;
                    games.add(new Game(this.teamsList.get(teamB - 1), this.teamsList.get(teamA - 1), fixture.id, counter));
                }
            }
            fixture.games = games;
            this.fixturesList.add(fixture);
        }
        EditFixtureFile.deleteFile(this.fixtureFileName);
        EditFixtureFile.saveFixtures(this.fixturesList, this.fixtureFileName);
        Console.printFixturesOnTerminal(this.fixturesList);   
        

    }

    public void registerGameResults() {
        if (this.fixturesList.size() > 0) {
            Console.printFixturesOnTerminal(this.fixturesList);
            Console.printSelectFixture();
            int fixtureId = this.scanner.nextInt();
            Console.printSelectGame();
            int gameId = this.scanner.nextInt();
            Game game = findGame(fixtureId, gameId);
            if (game.played == true) {
                System.out.println("This game has been already played. Do you want to update it? Y/N:");
                char option = this.scanner.next().charAt(0);

                if (option == 'y' || option == 'Y') {
                    game.played = false;
                    this.resetStatisticsInTeams(game);
                }

                if (option == 'n' || option == 'N') {
                    return;
                }
            }
            Team firstTeam = game.team1;
            Team secondTeam = game.team2;
            Console.printAddGoalsTeam(firstTeam.getName());
            int goalsFirstTeam = this.scanner.nextInt();
            Console.printAddGoalsTeam(secondTeam.getName());
            int goalsSecondTeam = this.scanner.nextInt();
            game = this.updateGame(goalsFirstTeam, goalsSecondTeam, true, gameId, fixtureId);
            this.fixturesList.get(fixtureId - 1).games.set(gameId - 1, game);
            this.updateTeams(firstTeam, secondTeam, goalsFirstTeam, goalsSecondTeam);
            EditFixtureFile.saveFixtures(this.fixturesList, this.fixtureFileName);
            EditSeasonFile.saveTeams(this.teamsList, this.teamFileName);
        }else {
            System.out.println("Please generate fixtures first");
        }
        
    }

    public void showTable() {
        this.teamsList.sort(Comparator.comparing(Team::getPoints).thenComparing(Team::getScoredGoals).thenComparing(Team::getRecievedGoals).reversed());
        Console.printScoreboard(this.name);
        Console.printTeamsOnTerminal(this.teamsList);
    }

    public Game findGame(int fixtureId, int gameId) {
        return this.fixturesList.get(fixtureId - 1).games.get(gameId - 1);
    }

    public Game updateGame(int goalsTeam1, int goalsTeam2, boolean played, int gameId, int fixtureId) {
        Game game = this.fixturesList.get(fixtureId - 1).games.get(gameId -1);
        game.goalsTeam1 = goalsTeam1;
        game.goalsTeam2 = goalsTeam2;
        game.played = played;
        return game;
    }

    public void updateTeams (Team team1, Team team2, int goalsTeam1, int goalsTeam2) {

        if (goalsTeam1 > goalsTeam2) {
            
            team1.setPoints(team1.getPoints() + 3);
        } 

        if(goalsTeam2 > goalsTeam1) {
            team2.setPoints(team2.getPoints() + 3);
        }

        if(goalsTeam1 == goalsTeam2) {
            team1.setPoints(team1.getPoints() + 1);
            team1.setPoints(team2.getPoints() + 1);
        }

        team1.setGamesPlayed();
        team2.setGamesPlayed();
        team1.setScoredGoals(team1.getScoredGoals() + goalsTeam1);
        team1.setrecievedGoals(team1.getRecievedGoals() + goalsTeam2);

        team2.setScoredGoals(team2.getScoredGoals() + goalsTeam2);
        team2.setrecievedGoals(team2.getRecievedGoals() + goalsTeam1);

        Optional<Team> match = this.teamsList.stream().filter(team -> team.getName().equals(team1.getName())).
        findFirst();

        Optional<Team> match2 = this.teamsList.stream().filter(team -> team.getName().equals(team2.getName())).
        findFirst();

        Team firstTeam = match.get();
        Team secondTeam = match2.get();
        System.out.println("indice del betis es:");
        this.teamsList.set(this.teamsList.indexOf(firstTeam), team1);
        this.teamsList.set(this.teamsList.indexOf(secondTeam), team2);
    }

    public void resetStatisticsInTeams(Game game) {
        Team team1 = game.team1;
        Team team2 = game.team2;

        if (game.goalsTeam1 > game.goalsTeam2) {
            
            team1.setPoints(- 3);
        } 

        if(game.goalsTeam2 > game.goalsTeam1) {
            team2.setPoints(- 3);
        }

        if(game.goalsTeam1 == game.goalsTeam2) {
            team1.setPoints(- 1);
            team1.setPoints(- 1);
        }

        team1.unsetGamesPlayed();
        team2.unsetGamesPlayed();

        team1.setScoredGoals(-game.goalsTeam1);
        team1.setrecievedGoals(-game.goalsTeam2);
        team2.setScoredGoals(-game.goalsTeam2);
        team2.setrecievedGoals(-game.goalsTeam1);

        Optional<Team> match = this.teamsList.stream().filter(team -> team.getName().equals(team1.getName())).
        findFirst();

        Optional<Team> match2 = this.teamsList.stream().filter(team -> team.getName().equals(team2.getName())).
        findFirst();

        Team firstTeam = match.get();
        Team secondTeam = match2.get();
        this.teamsList.set(this.teamsList.indexOf(firstTeam), team1);
        this.teamsList.set(this.teamsList.indexOf(secondTeam), team2);
        this.teamsList.sort(Comparator.comparing(Team::getPoints).reversed());

        
    }

}
