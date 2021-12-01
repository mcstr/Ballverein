import java.io.*;

public class Verein implements Serializable {
    private String name;
    private String geschosseneTore;
    private String kassierteTore;
    private double Punkte;
    private Integer Spiele;



    public Verein (String name) {
        this.name = name;
    }

    public double getPunkte() {
        return Punkte;
    }

    public void setPunkte(double punkte) {
        Punkte = punkte;
    }

    public
}
