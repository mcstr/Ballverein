import java.io.Serializable;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class Game implements Serializable{
    int id;
    Team team1;
    Team team2;
    int goalsTeam1;
    int goalsTeam2;
    int fixtureId;

    public Game (Team team1, Team team2, int fixtureId, int id) {
        this.fixtureId = fixtureId;
        this.team1 = team1;
        this.team2 = team2;
        this.id = id;
    }

    public void updateResult (Team team1, Team team2, int goalsTeam1, int goalsTeam2) {

        if (goalsTeam1 > goalsTeam2) {
            
            team1.setPunkte(team1.getPunkte() + 3);
        } 

        if(goalsTeam1 > goalsTeam2) {
            team2.setPunkte(team2.getPunkte() + 3);
        }

        if(goalsTeam1 == goalsTeam2) {
            team1.setPunkte(team1.getPunkte() + 1);
            team1.setPunkte(team2.getPunkte() + 1);
        }

        team1.setGeschosseneTore(team1.getGeschosseneTore() + goalsTeam1);
        team1.setKassierteTore(team1.getKassierteTore() + goalsTeam2);

        team2.setGeschosseneTore(team2.getGeschosseneTore() + goalsTeam2);
        team2.setKassierteTore(team2.getKassierteTore() + goalsTeam1);
    }

    @Override

    public String toString() {
        String string = String.format(Locale.GERMANY, "%s.%s-%s", this.id,
            this.team1.getName(), this.team2.getName());
            return string;
    }

}
