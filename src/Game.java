
public class Game {
    int id;
    Verein team1;
    Verein team2;
    int goalsTeam1;
    int goalsTeam2;

    public Game (Verein team1, Verein team2, int goalsTeam1, int goalsTeam2) {
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

}
