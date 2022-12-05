package main.day3;

import main.utils.FileHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class DayThree {

    public static void main(String[] args) throws IOException {
        System.out.println("Result of task 1: " + calculatePriorityOfItemsInBothComartments());
    }

    public static int calculatePriorityOfItemsInBothComartments() throws IOException {
        return FileHelper.getAllLines(Objects.requireNonNull(DayThree.class.getResource("./input.txt")).getPath())
                .stream()
                .map(DayThree::calculatePriorityForSingleLine)
                .mapToInt(value -> value)
                .sum();
    }

    private static int calculatePriorityForSingleLine(String line) {
        String firstHalf = line.substring(0, line.length() / 2);
        String secondHalf = line.substring(line.length() / 2);
        return Arrays.stream(firstHalf.split(""))
                .filter(secondHalf::contains)
                .mapToInt(i -> calculatePriorityOfChar(i.charAt(0)))
                .findFirst()
                .orElse(0);
    }

    public static int calculatePriorityOfChar(int asciiValueOfLetter){
        if (asciiValueOfLetter >= 97 && asciiValueOfLetter <= 172){
            return asciiValueOfLetter - 96;
        } else if (asciiValueOfLetter >= 65 && asciiValueOfLetter <= 90){
            return asciiValueOfLetter - 38;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
