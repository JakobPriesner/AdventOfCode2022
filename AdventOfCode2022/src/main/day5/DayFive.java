package main.day5;

import main.utils.FileHelper;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class DayFive {

    public static void main(String[] args) throws IOException {
        System.out.println(getTopCratesAfterSingleItemRearrangement());
    }

    private static String getTopCratesAfterSingleItemRearrangement() throws IOException {
        List<String> lines = FileHelper.getAllLines(Objects.requireNonNull(DayFive.class.getResource("./input.txt")).getPath());
        int columns = (lines.get(0).length() + 1) / 4;
        List<Stack<String>> stacks = getStacks(lines, columns);
        lines.stream()
             .filter(line -> line.contains("move"))
             .forEach(command -> singleBlockMove(stacks, command));

        return stacks.stream().map(Stack::pop).collect(Collectors.joining());
    }

    private static void singleItemMove(List<Stack<String>> stacks, String command) {
        String[] actions = command.split(" ");
        int amount = Integer.parseInt(actions[1]);
        int from = Integer.parseInt(actions[3])-1;
        int to = Integer.parseInt(actions[5])-1;
        for(int i = 0; i < amount; i++){
            stacks.get(to).add(stacks.get(from).pop());
        }
    }

    private static void singleBlockMove(List<Stack<String>> stacks, String command){
        String[] actions = command.split(" ");
        int amount = Integer.parseInt(actions[1]);
        int from = Integer.parseInt(actions[3])-1;
        int to = Integer.parseInt(actions[5])-1;
        List<String> blockList = new ArrayList<>(amount);
        for(int i = 0; i < amount; i++){
            blockList.add(stacks.get(from).pop());
        }
        Collections.reverse(blockList);
        stacks.get(to).addAll(blockList);
    }

    private static List<Stack<String>> getStacks(List<String> lines, int columns) {
        List<Stack<String>> stacks = new ArrayList<>(columns);
        for(int i = 0; i < columns; i++){
            stacks.add(new Stack<>());
        }
        int lineIndex = 0;
        String currentLine = lines.get(lineIndex);
        do {
            for (int i = 0; i < columns; i++){
                String currentLetter = currentLine.substring(1+i*4, 2+i*4);
                if (!currentLetter.equals(" ")) stacks.get(i).add(currentLetter);
            }
            currentLine = lines.get(++lineIndex);
        } while (!currentLine.contains("1"));

        stacks.forEach(Collections::reverse);
        return stacks;
    }

}
