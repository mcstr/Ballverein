import java.util.Locale;
import java.util.Scanner;

public class Console {
    Scanner scanner;

    public void printWellcomeMessage(String competitionName) {
        String message = String.format(Locale.GERMANY, "\n%s\r\n%s\r\n%s\r\n%s\r\n ", competitionName,
                "(1) Tabelle", "(2) Spiel einfügen", "(3) beenden");
        System.out.print(message);
        System.out.println("Bitte Eingabe tätigen: ");
    }

    public void printTabelle(String competitionName) {
        System.out.println(String.format(Locale.GERMANY, "\r\n%-15s\r\n%-15s%-15s%-15s%-15s%-15s\r\n", competitionName,
        "Team", "Punkte", "Spiele", "Gesch. Tore", "Kass. Tore"));
    }
    public void printSelectTeam() {
        System.out.print("Manschaft auswählen:\n");
    }
    public void printMakeSelection() {
        System.out.println("Bitte Eingabe tätigen: ");

    }
    // public void printStartConfiguration() {
    //     String message = String.format(Locale.GERMANY, "\n%s\r\n%s\r\n%s\r\n", "Bitte wählen Sie eine der folgenden Optionen:",
    //     "(1) Eine Fußballmannschaft hinzufügen", "(2) beenden");
    //     System.out.println(message);
    // }

    public void printaddTeam() {
        System.out.println("Bitte geben Sie den Teamnamen ein: ");
    }

    public void printTournamentName() {
        System.out.println("Bitte geben Sie den Namen des Turniers ein:");
    }
}

