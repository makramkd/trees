/**
 * Created by Makram on 6/30/16.
 */
public class RedBlackTree {
    /*
    A sentinel node to use instead of regular null, that is always colored
    black.
     */
    private RedBlackNode nil;

    /*
    The root of the red black tree.
     */
    private RedBlackNode root;

    /*
    The size of the tree; i.e how many nodes are in it.
     */
    private int size;

    public RedBlackTree() {
        this.nil = new RedBlackNode(Integer.MIN_VALUE);
        nil.color = RedBlackNode.BLACK;
        this.root = null;
        this.size = 0;
    }
}
