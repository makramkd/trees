/**
 * Created by Makram on 6/30/16.
 */
public class RedBlackNode {
    public static final int RED = 0, BLACK = 1, UNDEFINED = 2;
    public int key;
    public int color;
    public RedBlackNode left, right, parent;

    public RedBlackNode(int key) {
        this.key = key;
        this.left = this.right = this.parent = null;
        this.color = UNDEFINED;
    }
}
