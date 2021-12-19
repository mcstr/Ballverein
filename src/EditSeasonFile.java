import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class EditSeasonFile {

    public static void createFile(String fileName) {
        File folder = new File("Season");
        folder.mkdir();
        new File(folder + fileName);
    }

    public static void saveFile(String fileName) {
        try {
            File fl = new File("Season/" + fileName);
            FileOutputStream fos = new FileOutputStream(fl);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oss = new ObjectOutputStream(bos);
            oss.close();

        } catch (IOException e) {

            System.out.print(e.getMessage());
        }
    }

    public static void saveTeams(ArrayList<Team> objects, String fileName) {
        try {
            File fl = new File("Season/" + fileName);
            FileOutputStream fos = new FileOutputStream(fl);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oss = new ObjectOutputStream(bos);

            for (Team object : objects) {
                oss.writeObject(object);
            }
            oss.close();

        } catch (IOException e) {

            System.out.print(e.getMessage());
        }
    }

    public static ArrayList<Team> readTeams(int length, String fileName) {
        ArrayList<Team> teamsList = new ArrayList<>();

        try {
            final File folder = new File("Season");
            File fl = new File(folder + "/" + fileName);
            System.out.println(fl.getName());
            FileInputStream fis = new FileInputStream(fl);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            for (int i = 0; i < length; i++) {
                teamsList.add((Team) ois.readObject());
            }
            boolean isNotEndOfFile = true;
            while(isNotEndOfFile) {
                try{
                    teamsList.add((Team) ois.readObject());
                } catch (EOFException e) {
                    // end of file reached
                isNotEndOfFile = false;

               };
            }
            ois.close();
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return teamsList;
    }


    public static ArrayList<Team> readTeamsFirstTime(String fileName) {
        ArrayList<Team> teamsList = new ArrayList<>();

        try {
            final File folder = new File("Season");
            File fl = new File(folder + "/" + fileName);
            System.out.println(fl.getName());
            FileInputStream fis = new FileInputStream(fl);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            boolean isNotEndOfFile = true;
            while(isNotEndOfFile) {
                try{
                    teamsList.add((Team) ois.readObject());
                } catch (EOFException e) {
                    // end of file reached
                isNotEndOfFile = false;

               };
            }
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return teamsList;
    }
}
