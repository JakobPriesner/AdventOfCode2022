package main.day2;

import main.utils.FileHelper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DayTwo {

    // A = Schere, B = Stein, C = Papier
    // X = Schere, Y = Stein, Z = Papier

    // Points:
    // A | X = 1    // loose = 0
    // B | Y = 2    // draw  = 3
    // C | Z = 3    // win   = 6

    private int score = 0;
    private final Map<String, Integer> moveToPoints = BuildMoveToPointsMapForTaskTwo();

    public static void main(String[] args) {
        DayTwo dayTwo = new DayTwo();
        InputStream fileAsStream = DayTwo.class.getResourceAsStream("./input.txt");
        FileHelper.loadFileWithCallbackForEachLine(fileAsStream, dayTwo.calculateScore());
        System.out.println(dayTwo.score);
    }

    private HashMap<String, Integer> buildMoveToPointsMap(){
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

    private HashMap<String, Integer> BuildMoveToPointsMapForTaskTwo(){
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

    public Function<String, Void> calculateScore(){
        return s -> {
            score += moveToPoints.get(s);
            return null;
        };
    }

}
