package main.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileHelper {

    public static List<String> getAllLines(String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }

    public static void loadFileWithCallbackForEachLine(InputStream inputStream, Function<String, Void> callBack){
        try(InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null){
                callBack.apply(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadFileWithCallbackForEachLine(InputStream inputStream, Function<ArrayList<String>, Void> callBack, int lineSize){
        try(InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr)) {
            ArrayList<String> lineArray = new ArrayList<>(lineSize);
            String line;
            while ((line = br.readLine()) != null){
                lineArray.add(line);
                if (lineArray.size() == lineSize){
                    callBack.apply(lineArray);
                    lineArray.clear();
                }
            }
            callBack.apply(lineArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
