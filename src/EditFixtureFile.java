import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class EditFixtureFile {

    public static void createFile(String fileName) {
        new File("Season/" + fileName);
    }

    public static void saveFixtures(ArrayList<Fixture> objects, String fileName) {
        try {

            File fl = new File("Season/" + fileName);
            FileOutputStream fos = new FileOutputStream(fl);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oss = new ObjectOutputStream(bos);

            for (Fixture object : objects) {
                oss.writeObject(object);
            }
            oss.close();

        } catch (IOException e) {

            System.out.print(e.getMessage());
        }
    }

    public static ArrayList<Fixture> readFixtures(int length, String fileName) {
        ArrayList<Fixture> fixturesList = new ArrayList<>();

        try {
            final File folder = new File("Season");
            File fl = new File(folder + "/" + fileName);
            FileInputStream fis = new FileInputStream(fl);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            for (int i = 0; i < length; i++) {
                fixturesList.add((Fixture) ois.readObject());
            }
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fixturesList;
    }

    public static ArrayList<Fixture> readFixturesFirstTime(String fileName) {
        ArrayList<Fixture> fixtureList = new ArrayList<>();

        try {
            final File folder = new File("Season");
            File fl = new File(folder + "/" + fileName);
            System.out.println(fl.getName());
            FileInputStream fis = new FileInputStream(fl);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            boolean isNotEndOfFile = true;
            while (isNotEndOfFile) {
                try {
                    fixtureList.add((Fixture) ois.readObject());
                } catch (EOFException e) {
                    // end of file reached
                    isNotEndOfFile = false;

                }
                ;
            }
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fixtureList;
    }

    public static void deleteFile(String fileName) {

            File fl = new File("Season/" + fileName);

            if (fl.exists()) {
                fl.delete();
            }
            
    }
}
