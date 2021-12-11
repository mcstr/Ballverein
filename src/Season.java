import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;


public class Season {
    public Console console;
    public ArrayList<Verein> teamsList = new ArrayList<>();
    public ArrayList<Game> gameList = new ArrayList<>();
    public Scanner scanner;
    public String filename;
    public String name;
    public int numberOfTeams;


    public Season() {
        this.console = new Console();
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public boolean seasonStarted() {
        File[] fileList = this.checkIfFileExists();
        boolean fileExist = false;


        if (fileList.length > 0) {
            
            this.filename = fileList[0].getName();
            this.name = filename.substring(0, filename.lastIndexOf('.'));
            this.teamsList = EditTeam.readTeams(this.numberOfTeams, this.filename);  
            fileExist = true;

        } else {
            fileExist = false;
        }
        return fileExist;
    }
    
    public File[] checkIfFileExists(){
        final File folder = new File("Season");
        return folder.listFiles();
    }

    public int wellcome() {
        if (seasonStarted()) {
            this.console.printWellcomeMessage(this.name);
            int selected = this.scanner.nextInt();
            return selected;
        } else {
            startConfigSeason();
            return 0;
        }
    }

    public void startConfigSeason() {

        this.console.printTournamentName();
        this.name = this.scanner.nextLine();
        this.filename = this.name + ".txt";
        
        System.out.println("Bitte geben Sie die Anzahl der Teams ein:");
        this.numberOfTeams = this.scanner.nextInt();
        EditTeam.createFile(this.filename);
        this.addTeams();
        this.createFixture(this.teamsList);
    }

    public void addTeams() {

        while (this.numberOfTeams != this.teamsList.size()) {
            this.addTeam();
        }
        EditTeam.save(this.teamsList, this.filename);
        this.wellcome();
    }

    public void showTable() {
        this.console.printTabelle(this.name);
        EditTeam.printTeamOnTerminal(this.numberOfTeams, this.filename);
    }

    public void addGame() {
        try {
            this.console.printSelectTeam();
            ArrayList<Verein> list = EditTeam.readTeams(this.numberOfTeams, this.filename);
    
            for (Verein verein : list) {
                System.out.println("**" + verein.getName());
            }
            this.console.printMakeSelection();
            String selectedFirstTeam = this.scanner.next();
            // Spiel spiel = new Spiel()

            // VereinBearbeiten.update(selected, this.filename);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTeam() {
        this.console.printaddTeam();
        String name = this.scanner.next();
        Verein team = new Verein(name);
        this.teamsList.add(team);
    }
    
    public void createFixture (ArrayList<Verein> teamsList) {
        
    }

    public void start() { 
        boolean run = true;
        while (run) {
            try {
                int selected = this.wellcome();

                switch (selected) {
                    case 1:
                        this.showTable();
                        break;
                    case 2:
                        this.addGame();
                        break;
                    case 3:
                        run = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }


}
