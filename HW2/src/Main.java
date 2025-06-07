import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    private static final String PATH = "D:/TEMP/Game/savegames";

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(100, 1, 5, 1.25);
        GameProgress game2 = new GameProgress(110, 1, 15, 3.33);
        GameProgress game3 = new GameProgress(90, 2, 23, 5.67);
        List filesList = Arrays.asList(PATH + "/save1.dat",
                PATH + "/save2.dat",
                PATH + "/save3.dat");

        saveGame(filesList.get(0).toString(), game1);
        saveGame(filesList.get(1).toString(), game2);
        saveGame(filesList.get(2).toString(), game3);

        zipFiles(PATH + "/savegames.zip", filesList);

        clearFiles(PATH);
    }

    private static void saveGame(String path, GameProgress gp) {
        try (FileOutputStream fileOutStream = new FileOutputStream(path)) {
            ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
            objOutStream.writeObject(gp);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> filePaths) {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(path))) {
            for (String filePath : filePaths) {
                File file = new File(filePath);
                try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                    ZipEntry entry = new ZipEntry(file.getName());
                    zipOut.putNextEntry(entry);
                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    zipOut.write(buffer);
                    zipOut.closeEntry();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void clearFiles(String path) {
        File dir = new File(path);
        for (File item : dir.listFiles()) {
            if (!item.getName().endsWith("zip")){
              item.delete();
            }
        }
    }
}
