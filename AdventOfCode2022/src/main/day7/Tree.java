package main.day7;

import main.utils.FileHelper;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Tree {
    private Node root;

    public Tree() throws IOException {
        fillTreeFromInput();
    }

    private void fillTreeFromInput() throws IOException {
        List<String> lines = FileHelper.getAllLines(Objects.requireNonNull(DaySeven.class.getResource("./input.txt")).getPath());
        Node currentNode = root;
        for(String line : lines){
            currentNode = processSingleLine(line, currentNode);
        }
//        root.calculateSize();
    }

    private Node processSingleLine(String command, Node currentNode){
        String[] parts = command.split(" ");
        if (parts[0].equals("$")) {
            if (parts[1].equals("cd")) {
                if (parts[2].equals("/")) {
                    return root;
                } else if (parts[2].equals("..")) {
                    return currentNode.getParent();
                } else {
                    return currentNode.getChildByName(parts[2]);
                }
            }
            // ignore '$ ls'
        } else {
            if (parts[0].equals("dir")) {
                if (currentNode == null) {
                    root = new Node(parts[1], null);
                    currentNode = root;
                }
                currentNode.addChild(new Node(parts[1], currentNode));
            } else {
                currentNode.addToSize(Integer.parseInt(parts[0]));
            }
        }
        return currentNode;
    }

    public long getSumOfAllFolderSizesLowerThan(long amount){
        return root.getAllNodesWithSizeLowerThan(amount).stream().mapToLong(Node::getCalculatedSize).sum();
    }

    public long getFolderSizeOfFolderToDelete(long necessaryAmount){
        return getSumOfLowestFolderSizeLowerThan(necessaryAmount - (70000000 - root.getCalculatedSize()));
    }

    private long getSumOfLowestFolderSizeLowerThan(long amount){
        return root.getAllNodesWithSizeBiggerThan(amount).stream().sorted((o1, o2) -> (int) (o1.getCalculatedSize() - o2.getCalculatedSize())).mapToLong(Node::getCalculatedSize).findFirst().orElse(-1);
    }
}