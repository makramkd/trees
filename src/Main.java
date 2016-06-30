/**
 * Created by Makram on 6/30/16.
 */
public class Main {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(1);
        bst.add(2);
        bst.add(-1);
        bst.add(-3);
        bst.inorderWalk();
    }
}
