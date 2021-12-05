import java.util.Locale;
import java.util.Scanner;

public class Console {
    Scanner scanner;

    public void wellcomeMessage(String competitionName) {
        String message = String.format(Locale.GERMANY, "\n%s\r\n%s\r\n%s\r\n%s\r\n ", competitionName,
                "(1) Tabelle", "(2) Spiel einfügen", "(3) beenden");
        System.out.print(message);
        System.out.println("Bitte Eingabe tätigen: ");
    }

    public void tabelle(String competitionName) {
        System.out.println(String.format(Locale.GERMANY, "\r\n%-15s\r\n%-15s%-15s%-15s%-15s%-15s\r\n", competitionName,
        "Team", "Punkte", "Spiele", "Gesch. Tore", "Kass. Tore"));
    }
    public void selectTeam() {
        System.out.print("Manschaft auswählen:\n");
    }
    public void makeSelection() {
        System.out.println("Bitte Eingabe tätigen: ");

    }
    public void startConfiguration() {
        String message = String.format(Locale.GERMANY, "\n%s\r\n%s\r\n%s\r\n", "Bitte wählen Sie eine der folgenden Optionen:",
        "(1) Eine Fußballmannschaft hinzufügen", "(2) beenden");
        System.out.println(message);
    }

    public void addTeamText() {
        System.out.println("Bitte geben Sie den Teamnamen ein: ");
    }
}

