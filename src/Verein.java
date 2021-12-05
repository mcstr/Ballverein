import java.io.*;
import java.util.Locale;

public class Verein implements Serializable {
    private String name;
    private String geschosseneTore;
    private String kassierteTore;
    private double Punkte;
    private Integer Spiele;



    public Verein (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPunkte() {
        return Punkte;
    }

    public void setPunkte(double punkte) {
        Punkte = punkte;
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

    public Integer getSpiele() {
        return Spiele;
    }

    public String toString() {
        String reString = String.format(Locale.GERMANY, "%-15s%-15s%-15s%-15s%-15s", this.getName(),
                this.getPunkte(), this.getSpiele(), this.getGeschosseneTore(), this.getKassierteTore());
        return reString;
    }
}

