import java.io.*;
import java.util.Locale;

public class Team implements Serializable {
    private String name;
    private String geschosseneTore;
    private String kassierteTore;
    private int punkte;
    private int spiele;



    public Team (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public String getGeschosseneTore() {
        return geschosseneTore;
    }

    public void setGeschosseneTore(String geschosseneTore) {
        this.geschosseneTore = geschosseneTore;
    }

    public String getKassierteTore() {
        return kassierteTore;
    }

    public void setKassierteTore(String kassierteTore) {
        this.kassierteTore = kassierteTore;
    }

    public int getSpiele() {
        return spiele;
    }

    public String toString() {
        String reString = String.format(Locale.GERMANY, "%-15s%-15s%-15s%-15s%-15s", this.getName(),
                this.getPunkte(), this.getSpiele(), this.getGeschosseneTore(), this.getKassierteTore());
        return reString;
    }
}

