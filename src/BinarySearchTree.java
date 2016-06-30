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
    Add a key to this binary search tree, putting it in the right position.
     */
    public void add(int key) {
        TreeNode node = new TreeNode(key), trailingNode = null, temp = root;
        while (temp != null) {
            trailingNode = temp;
            if (node.key < temp.key) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        node.parent = trailingNode;
        if (trailingNode == null) {
            root = node;
        } else if (node.key < trailingNode.key) {
            trailingNode.left = node;
        } else {
            trailingNode.right = node;
        }
    }

    /*
    Delete a node from the tree with the given key.
     */
    public void delete(int key) {
        TreeNode toDelete = search(key);
        if (toDelete == null) {
            return;
        }
        if (toDelete.left == null) {
            displace(toDelete, toDelete.right);
        } else if (toDelete.right == null) {
            displace(toDelete, toDelete.left);
        } else {
            TreeNode minimum = minimumNode(toDelete.right);
            if (minimum.parent != toDelete) {
                displace(minimum, minimum.right);
                minimum.right = toDelete.right;
                minimum.right.parent = minimum;
            }
            displace(toDelete, minimum);
            minimum.left = toDelete.left;
            minimum.left.parent = minimum;
        }
    }

    /*
    This helper replaces one subtree as a child of its parent with
    another subtree. When the helper replaces the subtree rooted at node
    u with the subtree rooted at node v, node u's parent becomes node v's
    parent, and u's parent ends up having v as its appropriate child.
     */
    private void displace(TreeNode u, TreeNode v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
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
            return null;
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
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /*
    The successor of a node N is the node with the smallest key greater than N.key.
     */
    public TreeNode successor(TreeNode node) {
        if (node.right != null) {
            return minimumNode(node.right);
        }
        TreeNode succ = node.parent;
        while (succ != null && node == succ.right) {
            node = succ;
            succ = succ.parent;
        }

        return succ;
    }
}
