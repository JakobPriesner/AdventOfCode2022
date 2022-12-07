package main.day6;

import main.utils.FileHelper;

import java.io.IOException;
import java.util.*;

public class DaySix {

    public static void main(String[] args) throws IOException {
        System.out.println(getIndexOfFirstMarker(4));
        System.out.println(getIndexOfFirstMarker(14));
    }

    private static int getIndexOfFirstMarker(int messageLength) throws IOException {
        String input = FileHelper.getAllLines(Objects.requireNonNull(DaySix.class.getResource("./input.txt")).getPath()).stream().findFirst().get();
        for (int i = 0; i < input.length()-messageLength; i++){
            if (areAllCharsDifferent(input.substring(i, i+messageLength), messageLength)) return i+messageLength;
        }
        return -1;
    }

    private static boolean areAllCharsDifferent(String subString, int messageLength){
        return new HashSet<>(Arrays.asList(subString.split(""))).size() == messageLength;
    }

}
