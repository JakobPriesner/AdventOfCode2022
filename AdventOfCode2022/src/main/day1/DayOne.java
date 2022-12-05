package main.day1;

import main.utils.FileHelper;

import java.io.*;
import java.util.*;

public class DayOne {
    public static void main(String[] args) throws IOException {
        System.out.println("Maximum snacks of one elve: " + getMaxValue());
        System.out.println("Snacks of first three elves: " +getSumOfFirstThreeValues());
    }

    private static int getMaxValue() throws IOException {
        return getElveList()
                .stream()
                .max(Comparator.comparingInt(o -> o))
                .orElse(-1);
    }

    private static int getSumOfFirstThreeValues() throws IOException {
        return getElveList()
                .stream()
                .sorted(Comparator.comparingInt(o -> -o))
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static List<Integer> getElveList() throws IOException {
        List<Integer> elfeList = new ArrayList<>(Collections.singletonList(0));
        for (String s : FileHelper.getAllLines(Objects.requireNonNull(DayOne.class.getResource("./input.txt")).getPath())) {
            if (s.isEmpty()) elfeList.add(0);
            else elfeList.set(elfeList.size() - 1, elfeList.get(elfeList.size() - 1) + Integer.parseInt(s));
        }
        return elfeList;
    }
}
