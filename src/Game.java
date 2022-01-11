import java.io.Serializable;
import java.util.Locale;

public class Game implements Serializable{
    int id;
    Team team1;
    Team team2;
    int goalsTeam1;
    int goalsTeam2;
    int fixtureId;
    boolean played = false;

    public Game (Team team1, Team team2, int fixtureId, int id) {
        this.fixtureId = fixtureId;
        this.team1 = team1;
        this.team2 = team2;
        this.id = id;
    }

    public boolean getPlayed() {
        return this.played;
    }

    @Override

    public String toString() {
        String finalString = "";
        if (!this.played) {
            String string = String.format(Locale.GERMANY, "%s. %s - %s", this.id,
            this.team1.getName(), this.team2.getName());
            finalString = string; 
        }
        if (this.played) {
            String string = String.format(Locale.GERMANY, "%s. %s (%s) - %s (%s)", this.id,
            this.team1.getName(), this.goalsTeam1, this.team2.getName(), this.goalsTeam2);
            finalString = string; 
        }
        return finalString;
    }
}
