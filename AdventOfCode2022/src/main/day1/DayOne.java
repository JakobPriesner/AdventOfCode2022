package main.day1;

import main.utils.FileHelper;

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class DayOne {
    private final List<Integer> elveList;

    public DayOne() {
        this.elveList = new LinkedList<>(Collections.singletonList(0));
    }

    public static void main(String[] args) {
        DayOne dayOne = new DayOne();
        InputStream fileAsStream = DayOne.class.getResourceAsStream("./input.txt");
        FileHelper.loadFileWithCallbackForEachLine(fileAsStream, dayOne.processSingleLine());
        System.out.println("Maximum snacks of one elve: " + dayOne.getMaxValue().orElse(-1));
        System.out.println("Snacks of first three elves: " + dayOne.getSumOfFirstThreeValues());
    }

    private Optional<Integer> getMaxValue(){
        return this.elveList.stream().max(Comparator.comparingInt(o -> o));
    }

    private int getSumOfFirstThreeValues(){
        return this.elveList.stream().sorted(Comparator.comparingInt(o -> -o)).limit(3).mapToInt(Integer::intValue).sum();
    }

    public Function<String, Void> processSingleLine(){
        return s -> {
            if (s.isEmpty()){
                elveList.add(0);
                return null;
            }
            final int indexOfLastItem = elveList.size()-1;
            final int newAmount = elveList.get(indexOfLastItem) + Integer.parseInt(s);
            elveList.set(indexOfLastItem, newAmount);
            return null;
        };
    }
}
