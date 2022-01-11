import java.util.ArrayList;
import java.util.Locale;

public class Console {

    public static void printWellcomeMessage(String competitionName) {
        String message = String.format(Locale.GERMANY, "\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n", competitionName,
                "(1) Register and store the clubs", "(2) Generate the fixtures", "(3) Display table", "(4) Register game results", "(5) Exit");
        System.out.print(message);
        System.out.println("Bitte Eingabe tätigen: ");
    }

    public static void printScoreboard(String competitionName) {
        System.out.println(" ");
        String string = String.format(Locale.GERMANY, "\r\n**%s**\r\n\r\n| %-15s | %15s | %15s | %15s | %15s |", competitionName,
        "Team", "Punkte", "Spiele", "Gesch. Tore", "Kass. Tore");
        int longitud = string.length();
        System.out.println(string);
        System.out.println(new String(new char[longitud - 14]).replace('\0', '-'));
    }

    public static void printSelectTeam() {
        System.out.print("Manschaft auswählen:\n");
    }

    public static void printMakeSelection() {
        System.out.println("Bitte Eingabe tätigen: ");
    }

    public static void printaddTeam() {
        System.out.println("Bitte geben Sie den Teamnamen ein: ");
    }

    public static void printTournamentName() {
        System.out.println("Bitte geben Sie den Namen des Turniers ein: ");
    }

    public static void printSelectNumberOfTeams(){
        System.out.println("Bitte geben Sie die Anzahl der Teams ein:");
    }

    public static void printTeamOnTerminal(ArrayList<Team> teams) {
        int number = teams.size();

        for (int i = 0; i < number; i++) {
                
            System.out.println(teams.get(i).toString());
        }
    }

    public static void printCreatingNewSeason() {
        System.out.println("Creating new Season....");
    }

    public static void printLoadingSeason(String seasonName) {
        System.out.println("Loading Season " + seasonName +"....");
    }

    public static void printTeamsOnTerminal(ArrayList<Team> teamsList) {

        for (int i = 0; i < teamsList.size(); i++) {

            System.out.println(teamsList.get(i).toString());
        }
    }

    public static void printFixturesOnTerminal(ArrayList<Fixture> fixturesList) {

        for (Fixture fixture : fixturesList) {
            System.out.println("---" + fixture.id + ".Matchday---");

            for (Game game : fixture.games) {
                System.out.println(game.toString());
            }
            System.out.println(" ");
        }
    }

    public static void printSelectGame() {
        System.out.println("Please select a game from the list:");
    }

    public static void printSelectFixture() {
        System.out.println("Please select a fixture from the list:");
    }

    public static void printAddGoalsTeam(String team) {
        System.out.println("How many goals has " + team + " scored?:");
    }

    public static void printRegisterTeams () {
        System.out.println("Please, register a few Teams first!");
    }

    public static void printGameAlreadyPlayed() {
        System.out.println("This game has been already played. Do you want to update it? Y/N:");
    }

    public static void printGenerateFixtures() {
        System.out.println("Please generate fixtures first");
    }

    public static void printNumberOfTeams(int size) {
        System.out.println("the number of teams is: " + size);
    }

    public static void printWariningMessage() {
        System.out.println("This will reset all the Season's progress, Do you want to proceed? [Y/N]");
    }
}

