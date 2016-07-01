/**
 * Created by Makram on 6/30/16.
 */
public class Main {

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.add(1);
        tree.add(-1);
        tree.add(2);
        tree.add(-3);
        tree.inorderWalk();
    }
}
