package main.day7;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {
    private final String name;
    private final Node parent;
    private final List<Node> childs;
    private long size;

    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
        this.childs = new LinkedList<>();
        this.size = 0;
    }

    public void addChild(Node n){
        childs.add(n);
    }

    public String getName() {
        return name;
    }

    public Node getParent() {
        return parent;
    }

    public Node getChildByName(String name){
        return this.childs.stream()
                .filter(node -> node.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Node> getAllNodesWithSizeLowerThan(long amount){
        List<Node> result = childs.stream().map(child -> child.getAllNodesWithSizeLowerThan(amount)).flatMap(List::stream).collect(Collectors.toList());
        if (getCalculatedSize() < amount){
            result.add(this);
        }
        return result;
    }

    public List<Node> getAllNodesWithSizeBiggerThan(long amount){
        List<Node> result = childs.stream().map(child -> child.getAllNodesWithSizeBiggerThan(amount)).flatMap(List::stream).collect(Collectors.toList());
        if (getCalculatedSize() > amount){
            result.add(this);
        }
        return result;
    }

    public long getCalculatedSize() {
        return size + childs.stream().mapToLong(Node::getCalculatedSize).sum();
    }

    public void addToSize(int amount){
        this.size += amount;
    }
}
