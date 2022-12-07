package main.day7;

import java.io.IOException;

public class DaySeven {

    public static void main(String[] args) throws IOException {
        Tree tree = new Tree();
        System.out.println(tree.getSumOfAllFolderSizesLowerThan(100000+1));
        System.out.println(tree.getFolderSizeOfFolderToDelete(30000000-1));
    }

}
