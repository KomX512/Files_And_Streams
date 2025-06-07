import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String PATH = "D:/TEMP/Game";

    private static final List<String> DIR_PATHS_LIST = Arrays.asList(
            PATH + "/src",
            PATH + "/res",
            PATH + "/savegames",
            PATH + "/temp",
            PATH + "/src/main",
            PATH + "/src/test",
            PATH + "/res/drawables",
            PATH + "/res/vectors",
            PATH + "/res/icons"
    );

    private static final List<String> FILE_PATHS_LIST = Arrays.asList(
            PATH + "/src/main/Main.java",
            PATH + "/src/main/Utils.java",
            PATH + "/temp/temp.txt"
    );
    private static final String LOG_FILE_PATH = PATH + "/temp/temp.txt";

    public static void main(String[] args) {
        String errorMsg;
        StringBuilder log = new StringBuilder();

        for (String dirPath : DIR_PATHS_LIST) {
            File dir = new File(dirPath);

            if (dir.mkdir()) {
                log.append("Создана дирректория: " + dirPath + "\n");
            }
        }

        for (String filePath : FILE_PATHS_LIST) {
            File file = new File(filePath);
            try {
                if (file.createNewFile()) {
                    log.append("Создан файл: " + filePath + "\n");
                }

            } catch (IOException ex) {
                errorMsg = "Ошибка..." + ex.getMessage() + "\n";
                System.out.println(errorMsg);
                log.append(errorMsg);
            }
        }

        try ( FileWriter fileWriter = new FileWriter(LOG_FILE_PATH)){
            fileWriter.write(log.toString());
        } catch (IOException ex) {
            System.out.println("Ошибка записи лога...");
            System.out.println(ex.getMessage());
        }

    }
}