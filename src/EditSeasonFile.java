import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class EditSeasonFile {

    public static void createFile(String fileName) {
        File folder = new File("Season");
        folder.mkdir();
        new File(folder + fileName);
    }


    public static void saveSeasonFile(ArrayList<Team> objects, String fileName) {
        try {
            File fl = new File("Season/" + fileName);
            FileOutputStream fos = new FileOutputStream(fl);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oss = new ObjectOutputStream(bos);
            if (objects.size() != 0) {
                for (Team object : objects) {
                    oss.writeObject(object);
                }
                oss.close();
            }
        } catch (IOException e) {

            System.out.print(e.getMessage());
        }
    }

    public static ArrayList<Team> readTeamsFirstTime(String fileName) {
        ArrayList<Team> teamsList = new ArrayList<>();

        try {
            final File folder = new File("Season");
            File fl = new File(folder + "/" + fileName);
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
            System.out.println("here");
            System.out.println(e.getMessage());
        }
        return teamsList;
    }
}
