package main.day4;

import main.utils.FileHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class DayFour {

    public static void main(String[] args) throws IOException {
        System.out.println("Task 1: " + getCountOfFullyContainedElves());
        System.out.println("Task 2: " + getCountOfOverlappingPairs());
    }

    private static long getCountOfFullyContainedElves() throws IOException {
        return FileHelper.getAllLines(Objects.requireNonNull(DayFour.class.getResource("./input.txt")).getPath())
                .stream()
                .map(s -> s.split("[-,]"))
                .map(line -> Arrays.stream(line).mapToInt(Integer::parseInt).toArray())
                .filter(val -> (val[0] <= val[2] && val[1] >= val[3]) || (val[2] <= val[0] && val[3] >= val[1]))
                .count();
    }

    private static long getCountOfOverlappingPairs() throws IOException {
        return FileHelper.getAllLines(Objects.requireNonNull(DayFour.class.getResource("./input.txt")).getPath())
                .stream()
                .map(s -> s.split("[-,]"))
                .map(line -> Arrays.stream(line).mapToInt(Integer::parseInt).toArray())
                .filter(val -> !(val[1] < val[2] || val[3] < val[0]))
                .count();
    }

}
