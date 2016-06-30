/**
 * Created by Makram on 6/30/16.
 */
public class BinarySearchTree {

    /*
    The root of the binary search tree.
     */
    private TreeNode root;

    /*
    Construct an empty binary search tree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /*
    Walk the binary search tree in order: meaning that the elements
    are printed out in sorted order.
     */
    public void inorderWalk() {
        inorderWalk(root);
    }

    private void inorderWalk(TreeNode node) {
        if (node != null) {
            inorderWalk(node.left);
            System.out.println(node.key);
            inorderWalk(node.right);
        }
    }

    /*
    Search the binary search tree for a given key.
     */
    public TreeNode search(int key) {
        return search(root, key);
    }

    private TreeNode search(TreeNode node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    /*
    Return the node in the binary search tree with the minimum key.
     */
    public TreeNode minimumNode() {
        return minimumNode(root);
    }

    private TreeNode minimumNode(TreeNode node) {
        if (node == null){
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /*
    Return the node in the binary search tree with the maximum key.
     */
    public TreeNode maximumNode() {
        return maximumNode(root);
    }

    private TreeNode maximumNode(TreeNode node) {
        if (node == null) {
            return node;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
}
