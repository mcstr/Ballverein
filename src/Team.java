import java.io.*;
import java.util.Locale;

public class Team implements Serializable {
    private String name;
    private int scoredGoals = 0;
    private int recievedGoals = 0;
    private int points;
    private int gamesPlayed;



    public Team (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals += scoredGoals;
    }

    public int getRecievedGoals() {
        return recievedGoals;
    }

    public void setrecievedGoals(int recievedGoals) {
        this.recievedGoals += recievedGoals;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed() {
        this.gamesPlayed ++;
    }

    public void unsetGamesPlayed() {
        this.gamesPlayed --;
    }

    public String toString() {
        String reString = String.format(Locale.GERMANY, "| %-15s | %15s | %15s | %15s | %15s |", this.getName(),
                this.getPoints(), this.getGamesPlayed(), Integer.toString(this.getScoredGoals()), Integer.toString(this.getRecievedGoals()));
        return reString;
    }

    public void resetTeam() {
        this.scoredGoals = 0;
        this.recievedGoals = 0;
        this.points = 0;
        this.gamesPlayed = 0;
    }
}