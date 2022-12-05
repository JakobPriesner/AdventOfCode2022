package main.day1;

import java.io.*;
import java.util.*;

public class DayOne {
    private final List<Integer> elveList;

    public DayOne() {
        this.elveList = new LinkedList<>(Collections.singletonList(0));
    }

    public static void main(String[] args) {
        DayOne dayOne = new DayOne();
        InputStream fileAsStream = DayOne.class.getResourceAsStream("./input.txt");
        dayOne.fillListWithValuesFromFile(fileAsStream);
        System.out.println("Maximum snacks of one elve: " + dayOne.getMaxValue().orElse(-1));
        System.out.println("Snacks of first three elves: " + dayOne.getSumOfFirstThreeValues());
    }

    private Optional<Integer> getMaxValue(){
        return this.elveList.stream().max(Comparator.comparingInt(o -> o));
    }

    private int getSumOfFirstThreeValues(){
        return this.elveList.stream().sorted(Comparator.comparingInt(o -> -o)).limit(3).mapToInt(Integer::intValue).sum();
    }

    private void fillListWithValuesFromFile(InputStream inputStream){
        try(InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null){
                processSingleLine(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSingleLine(String line){
        if (line.isEmpty()){
            elveList.add(0);
            return;
        }
        final int indexOfLastItem = elveList.size()-1;
        final int newAmount = elveList.get(indexOfLastItem) + Integer.parseInt(line);
        elveList.set(indexOfLastItem, newAmount);
    }
}
