/**
 * A node in a binary tree of any kind.
 */
public class TreeNode {
    public int key;
    public TreeNode left, right, parent;

    public TreeNode(int data) {
        this.key = data;
        this.left = this.right = this.parent = null;
    }
}
