import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class EditTeam {



    public static void createFile(String competitionName) {
        new File("Season/" + competitionName);
    }

    public static void save(ArrayList<Verein> vereine, String competitionName) {
        try {
            File fl = new File("Season/" + competitionName);
            FileOutputStream fos = new FileOutputStream(fl);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oss = new ObjectOutputStream(bos);

            for(int i = 0; i < vereine.size(); i++) {
                oss.writeObject(vereine.get(i));
            }
            
            // oss.writeObject(vereine);
            oss.close();
            printTeamOnTerminal(vereine.size(), competitionName);

        } catch (IOException e) {
            
            System.out.print(e.getMessage());
        }
    }

    public static void printTeamOnTerminal(int numberOfTeams, String competitionName) {
        try {
            ArrayList<Verein> vereine = readTeams(numberOfTeams, competitionName);
            for (int i = 0; i < numberOfTeams; i++) {
                System.out.println(vereine.get(i).toString());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void update(String result, String competitionName) {
        try {
            System.out.println(result);
            // String resultUpdated = result.replace(" - ", " ");
            // String str[] = result.split(" ");
            // System.out.println(resultUpdated);
            // ArrayList<Verein> list = readVereine(competitionName);
            // for (Verein vereine : list) {
            //     if (vereine.getName().equals(verein)) {
            //         vereine.setKassierteTore("3");
            //     }
            // }
            // speichern(list, competitionName);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

    public static ArrayList<Verein> readTeams(int numberOfTeams, String competitionName) {
        ArrayList<Verein> vereineList = new ArrayList<>();

        try {
            final File folder = new File("Season");
            File fl = new File(folder + "/" + competitionName);
            FileInputStream fis = new FileInputStream(fl);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
           
            for(int i = 0; i < numberOfTeams; i++) {
                vereineList.add((Verein) ois.readObject());
            }
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return vereineList;
    }

}
