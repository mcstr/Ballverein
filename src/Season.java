import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;


public class Season {
    public Console console;
    public ArrayList<Verein> vereinListe = new ArrayList<>();
    public Scanner scanner;
    public String filename;
    public String name;
    public int numberOfTeams;


    public Season() {
        this.console = new Console();
        this.scanner = new Scanner(System.in);
    }

    public boolean seasonStarted() {
        File[] fileList = this.checkIfFileExists();
        boolean fileExist = false;


        if (fileList.length > 0) {
            this.filename = fileList[0].getName();
            this.name = filename.substring(0, filename.lastIndexOf('.'));
            this.vereinListe = VereinBearbeiten.readVereine(this.filename);  
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
            this.console.wellcomeMessage(this.name);
            int selected = this.scanner.nextInt();
            return selected;
        } else {
            startConfigSeason();
            return 0;
        }
    }

    public void startConfigSeason() {
        System.out.println("Bitte geben Sie den Namen des Turniers ein:");
        this.name = this.scanner.nextLine();
        this.filename = this.name + ".txt";
        
        System.out.println("Bitte geben Sie die Anzahl der Teams ein:");
        this.numberOfTeams = this.scanner.nextInt();
        VereinBearbeiten.createFile(this.filename);
        this.addTeams();
    }

    public void addTeams() {

        while (this.numberOfTeams != this.vereinListe.size()) {
            this.addTeam();
        }
        VereinBearbeiten.speichern(this.vereinListe, this.filename);
        this.wellcome();
    }

    public void showTable() {
        this.console.tabelle(this.name);
        VereinBearbeiten.ausgeben(this.filename);
    }

    public void addSpiel() {
        this.console.selectTeam();
        ArrayList<Verein> list = VereinBearbeiten.readVereine(this.filename);

        for (Verein verein : list) {
            System.out.println(verein.getName());
        }
        this.console.makeSelection();
        String selected = this.scanner.nextLine();
        VereinBearbeiten.update(selected, this.filename);
    }
    public void addTeam() {
        this.console.addTeamText();
        String name = this.scanner.next();
        Verein team = new Verein(name);
        this.vereinListe.add(team);
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
                        this.addSpiel();
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
