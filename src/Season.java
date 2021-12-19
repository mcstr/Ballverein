import java.util.ArrayList;
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
            // Add reading the fixtures;
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


            
        }else {
            System.out.println("Please generate fixtures first");
        }
        
    }

    public void showTable() {
        Console.printScoreboard(this.name);
        Console.printTeamsOnTerminal(this.teamsList);
    }

    public Game findGame(int fixtureId, int spielId) {
        return this.fixturesList.get(fixtureId - 1).games.get(spielId - 1);
    }

}
