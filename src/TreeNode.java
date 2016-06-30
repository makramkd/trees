/**
 * A node in a binary tree of any kind.
 */
public class TreeNode {
    int key;
    TreeNode left, right, parent;

    public TreeNode(int data) {
        this.key = data;
        this.left = this.right = this.parent = null;
    }
}
