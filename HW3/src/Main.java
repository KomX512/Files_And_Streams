
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    private static final String PATH = "D:/TEMP/Game/savegames";

    public static void main(String[] args) {
        openZip(PATH + "/savegames.zip", PATH);

        GameProgress game1 = openProgress(PATH + "/save1.dat");
        GameProgress game2 = openProgress(PATH + "/save2.dat");
        GameProgress game3 = openProgress(PATH + "/save3.dat");

        System.out.println(game1);
        System.out.println(game2);
        System.out.println(game3);
    }

    private static void openZip(String filePath, String unzipPath) {
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(filePath))) {
            ZipEntry entry;
            String name;
            while ((entry = zipIn.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fileOut = new FileOutputStream(unzipPath + "/" + name);
                for (int c = zipIn.read(); c != -1; c = zipIn.read()) {
                    fileOut.write(c);
                }
                fileOut.flush();
                zipIn.closeEntry();
                fileOut.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static GameProgress openProgress(String savePath) {
        GameProgress gp = null;

        try (FileInputStream  fis = new FileInputStream(savePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            gp = (GameProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return gp;
    }
}