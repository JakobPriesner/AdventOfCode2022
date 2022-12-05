package main.utils;

import java.util.function.Function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileHelper {

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

}
