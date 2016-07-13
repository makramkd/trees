/**
 * Created by Makram on 6/30/16.
 */
public class RedBlackTree {
    /*
    A sentinel node to use instead of regular null, that is always colored
    black.
     */
    private final RedBlackNode nil;

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
        nil.parent = nil;
        nil.color = RedBlackNode.BLACK;
        this.root = nil;
        this.size = 0;
    }

    /*
    Return the number of nodes in the search tree.
     */
    public int size() {
        return size;
    }

    /*
    Precondition: x.right is not nil and that the root's parent
    is nil.
     */
    private void leftRotate(RedBlackNode x) {
        RedBlackNode y = x.right;
        x.right = y.left;
        if (y.left != nil) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    /*
    Precondition: y.left is not nil and that the root's parent is nil.
     */
    private void rightRotate(RedBlackNode y) {
        RedBlackNode x = y.left;
        y.left = x.right;
        if (x.right != nil) {
             x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == nil) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
    }

    public void add(int key) {
        RedBlackNode z = new RedBlackNode(key);
        RedBlackNode y = nil, x = root;
        while (x != nil) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == nil) {
            root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = nil;
        z.right = nil;
        z.color = RedBlackNode.RED;
        addFixup(z);
        ++size;
    }

    private void addFixup(RedBlackNode z) {
        while (z.parent.color == RedBlackNode.RED) {
            if (z.parent == z.parent.parent.left) {
                RedBlackNode y = z.parent.parent.right;
                if (y.color == RedBlackNode.RED) {
                    z.parent.color = RedBlackNode.BLACK;
                    y.color = RedBlackNode.BLACK;
                    z.parent.parent.color = RedBlackNode.RED;
                    z = z.parent.parent;
                } else if (z == z.parent.right) {
                    z = z.parent;
                    leftRotate(z);
                    z.parent.color = RedBlackNode.BLACK;
                    z.parent.parent.color = RedBlackNode.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                RedBlackNode y = z.parent.parent.left;
                if (y.color == RedBlackNode.RED) {
                    z.parent.color = RedBlackNode.BLACK;
                    y.color = RedBlackNode.BLACK;
                    z.parent.parent.color = RedBlackNode.RED;
                    z = z.parent.parent;
                } else if (z == z.parent.right) {
                    z = z.parent;
                    leftRotate(z);
                    z.parent.color = RedBlackNode.BLACK;
                    z.parent.parent.color = RedBlackNode.RED;
                    rightRotate(z.parent.parent);
                }
            }
        }
        root.color = RedBlackNode.BLACK;
    }

    public void delete(int key) {
        RedBlackNode z = search(key);
        if (z == nil) {
            return;
        }
        int yOriginalColor = z.color;
        if (z.left == nil) {
            RedBlackNode x = z.right;
            displace(z, z.right);
        } else if (z.right == nil) {
            RedBlackNode x = z.left;
            displace(z, z.left);
        } else {
            RedBlackNode y = minimumNode(z.right);
            yOriginalColor = y.color;
            RedBlackNode x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                displace(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            displace(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;

            if (yOriginalColor == RedBlackNode.BLACK) {
                deleteFixup(x);
            }
        }
    }

    private void deleteFixup(RedBlackNode x) {
        while (x != root && x.color == RedBlackNode.BLACK) {
            if (x == x.parent.left) {
                RedBlackNode w = x.parent.right;
                if (w.color == RedBlackNode.RED) {
                    w.color = RedBlackNode.BLACK;
                    x.parent.color = RedBlackNode.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == RedBlackNode.BLACK && w.right.color == RedBlackNode.BLACK) {
                    w.color = RedBlackNode.RED;
                    x = x.parent;
                } else if (w.right.color == RedBlackNode.BLACK) {
                    w.left.color = RedBlackNode.BLACK;
                    w.color = RedBlackNode.RED;
                    rightRotate(w);
                    w = x.parent.right;
                }
                w.color = x.parent.color;
                x.parent.color = RedBlackNode.BLACK;
                w.right.color = RedBlackNode.BLACK;
                leftRotate(x.parent);
                x = root;
            } else {
                RedBlackNode w = x.parent.left;
                if (w.color == RedBlackNode.RED) {
                    w.color = RedBlackNode.BLACK;
                    x.parent.color = RedBlackNode.RED;
                    leftRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.left.color == RedBlackNode.BLACK && w.right.color == RedBlackNode.BLACK) {
                    w.color = RedBlackNode.RED;
                    x = x.parent;
                } else if (w.left.color == RedBlackNode.BLACK) {
                    w.left.color = RedBlackNode.BLACK;
                    w.color = RedBlackNode.RED;
                    rightRotate(w);
                    w = x.parent.left;
                }
                w.color = x.parent.color;
                x.parent.color = RedBlackNode.BLACK;
                w.left.color = RedBlackNode.BLACK;
                leftRotate(x.parent);
                x = root;
            }
        }
        x.color = RedBlackNode.BLACK;
    }

    /*
    Identical to BinarySearchTree.displace, except instead of nulls we are using nils.
     */
    private void displace(RedBlackNode u, RedBlackNode v) {
        if (u.parent == nil) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    /*
    Walk the binary search tree in order: meaning that the elements
    are printed out in sorted order.
     */
    public void inorderWalk() {
        inorderWalk(root);
    }

    private void inorderWalk(RedBlackNode node) {
        if (node != nil) {
            inorderWalk(node.left);
            System.out.println(node.key);
            inorderWalk(node.right);
        }
    }

    /*
    Search the binary search tree for a given key.
     */
    public RedBlackNode search(int key) {
        return search(root, key);
    }

    private RedBlackNode search(RedBlackNode node, int key) {
        if (node == nil || node.key == key) {
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
    public RedBlackNode minimumNode() {
        return minimumNode(root);
    }

    private RedBlackNode minimumNode(RedBlackNode node) {
        if (node == nil){
            return nil;
        }
        while (node.left != nil) {
            node = node.left;
        }
        return node;
    }

    /*
    Return the node in the binary search tree with the maximum key.
     */
    public RedBlackNode maximumNode() {
        return maximumNode(root);
    }

    private RedBlackNode maximumNode(RedBlackNode node) {
        if (node == nil) {
            return nil;
        }
        while (node.right != nil) {
            node = node.right;
        }
        return node;
    }

    /*
    The successor of a node N is the node with the smallest key greater than N.key.
     */
    public RedBlackNode successor(RedBlackNode node) {
        if (node.right != nil) {
            return minimumNode(node.right);
        }
        RedBlackNode succ = node.parent;
        while (succ != nil && node == succ.right) {
            node = succ;
            succ = succ.parent;
        }

        return succ;
    }
}
