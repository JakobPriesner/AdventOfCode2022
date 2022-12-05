package main.day2;

import main.utils.FileHelper;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DayTwo {

    private static String[] enemy = new String[]{"A", "B", "C"};
    private static String[] self = new String[]{"X", "Y", "Z"};

    // A = Schere, B = Stein, C = Papier
    // X = Schere, Y = Stein, Z = Papier

    // Points:
    // A | X = 1    // loose = 0
    // B | Y = 2    // draw  = 3
    // C | Z = 3    // win   = 6

    public static void main(String[] args) throws IOException {
        System.out.println(calculateScore(buildMoveToPointsMap()));
        System.out.println(calculateScore(BuildMoveToPointsMapForTaskTwo()));
    }

    public static int calculateScore(final Map<String, Integer> moveToPoints) throws IOException {
        return FileHelper.getAllLines(Objects.requireNonNull(DayTwo.class.getResource("./input.txt")).getPath())
                .stream()
                .mapToInt(moveToPoints::get)
                .sum();
    }

    private static HashMap<String, Integer> buildMoveToPointsMap(){
        HashMap<String, Integer> moveToPoints = new HashMap<>();
        moveToPoints.put("A X", 4);
        moveToPoints.put("A Y", 8);
        moveToPoints.put("A Z", 3);
        moveToPoints.put("B X", 1);
        moveToPoints.put("B Y", 5);
        moveToPoints.put("B Z", 9);
        moveToPoints.put("C X", 7);
        moveToPoints.put("C Y", 2);
        moveToPoints.put("C Z", 6);
        return moveToPoints;
    }

    private static HashMap<String, Integer> BuildMoveToPointsMapForTaskTwo(){
        HashMap<String, Integer> moveToPoints = new HashMap<>();
        moveToPoints.put("A X", 3);
        moveToPoints.put("A Y", 4);
        moveToPoints.put("A Z", 8);
        moveToPoints.put("B X", 1);
        moveToPoints.put("B Y", 5);
        moveToPoints.put("B Z", 9);
        moveToPoints.put("C X", 2);
        moveToPoints.put("C Y", 6);
        moveToPoints.put("C Z", 7);
        return moveToPoints;
    }

}
