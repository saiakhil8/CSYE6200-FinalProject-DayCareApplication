package edu.neu.csye6200.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author SaiAkhil
 */
public class FileUtils {
    public static List<String> readLinesFromCSV(String filePath) throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (String line; (line = reader.readLine()) != null; ) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("File is not valid");
        }
        return list;
    }

    public static void readTxtFile(String filePath, Consumer<List<String>> consumer) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> list = new ArrayList<>();
            for (String line; (line = reader.readLine()) != null; ) {
                list.add(line);
            }
            consumer.accept(list);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("File is not valid");
        }
    }

    public static void readTxtFileLines(String filePath, Consumer<String> consumer, Consumer<Boolean> result) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (String line; (line = reader.readLine()) != null; ) {
                consumer.accept(line);
            }
            result.accept(true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("File is not valid");
        }
    }

}
