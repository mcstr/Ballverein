import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class VereinBearbeiten {



    public static void createFile(String competitionName) {
        new File("Season/" + competitionName);
    }

    public static void speichern(ArrayList<Verein> vereine, String competitionName) {
        try {
            File fl = new File("Season/" + competitionName);
            FileOutputStream fos = new FileOutputStream(fl);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oss = new ObjectOutputStream(bos);

            oss.writeObject(vereine);
            oss.close();

        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public static void ausgeben(String competitionName) {
        try {
            ArrayList<Verein> vereine = readVereine(competitionName);
            for (int i = 0; i < vereine.size(); i++) {
                System.out.println(vereine.get(i).toString());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void update(String verein, String competitionName) {
        try {
            ArrayList<Verein> list = readVereine("Season/" + competitionName);
            for (Verein vereine : list) {
                if (vereine.getName().equals(verein)) {
                    vereine.setKassierteTore("3");
                }
            }
            speichern(list, competitionName);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

    public static ArrayList<Verein> readVereine(String competitionName) {
        ArrayList<Verein> vereineList = new ArrayList<>();

        try {
            File fl = new File("Season/" + competitionName);
            FileInputStream fis = new FileInputStream(fl);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            ArrayList<Verein> vereine = new ArrayList<>();
            vereine = (ArrayList<Verein>) ois.readObject();
            vereineList = vereine;
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return vereineList;
    }

}
