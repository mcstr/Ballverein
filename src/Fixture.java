import java.io.Serializable;
import java.util.ArrayList;

public class Fixture implements Serializable{
    ArrayList<Game> games;
    int id;

    public Fixture (int id) {
        this.id = id;
    }

    public void setGame (Game game) {
        this.games.add(game);
    }
}
