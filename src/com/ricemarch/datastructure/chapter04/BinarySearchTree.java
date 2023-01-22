package com.ricemarch.datastructure.chapter04;

import java.util.Comparator;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    public BinarySearchTree(Comparator<? super AnyType> cmp) {
        this.cmp = cmp;
    }

    private static class BinaryNode<AnyType> {
        public BinaryNode(AnyType element) {
            this(element, null, null);
        }

        public BinaryNode(AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        AnyType element; // the data in the node
        BinaryNode<AnyType> left; // left child
        BinaryNode<AnyType> right; // right child
    }

    private BinaryNode<AnyType> root;
    private Comparator<? super AnyType> cmp;

    public BinarySearchTree() {
        this(null);
    }

    private int myCompare(AnyType lhs, AnyType rhs) {
        if (cmp != null) {
            return cmp.compare(lhs, rhs);
        } else {
            return ((Comparable) lhs).compareTo(rhs);
        }
    }


    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    public AnyType findMin() throws UnderFlowException {
        if (isEmpty()) throw new UnderFlowException();
        return findMin(root).element;
    }

    public AnyType findMax() throws UnderFlowException {
        if (isEmpty()) throw new UnderFlowException();
        return findMax(root).element;
    }


    public void insert(AnyType x) {
        root = insert(x, root);
    }

    /**
     * 删除可能是最困难的操作，如果删除的次数不多，通常使用的策略是惰性删除
     * 删除需要考虑的几种可能性
     * 1. 如果节点是叶子结点，它可以被立即删除
     * 2. 如果节点有一个儿子，则该节点可以在其父节点调整自己的链以绕过该节点后被删除
     * 3. 如果节点有两个儿子，一般的删除策略是用其右子树的最小的数据代替该节点的数据并递归地删除那个节点
     *
     * @param x
     */
    public void remove(AnyType x) {
        root = remove(x, root);
    }


    /**
     * internal method to remove from a subtree
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree
     * @return the new root of the subtree
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return t; // item not found;do nothing
        }
        int compareResult = myCompare(x, t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            // two children
            // 如果节点有两个儿子，一般的删除策略是用其右子树的最小的数据代替该节点的数据并递归地删除那个节点
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            // 如果左右儿子都为空 也一样
            // 如果节点有一个儿子，则该节点可以在其父节点调整自己的链以绕过该节点后被删除
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    /**
     * internal method to insert into a subtree.
     *
     * @param x the item to insert
     * @param t the node that roots the subtree
     * @return the new root of the subtree
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }
        int compareResult = myCompare(x, t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            //duplicate; do nothing
        }
        return t;
    }

    /**
     * internal method to find the largest item in a subtree.
     * 非递归形式的最大值查询
     *
     * @param t the node that roots the subtree
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    /**
     * internal method to find the smallest item in a subtree.
     * 递归形式的最小值查询
     *
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    /**
     * internal method to find an item in a subtree
     *
     * @param x x is item to seaech for
     * @param t r the node that roots the subtree
     * @return true if the item is found; false otherwise
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return false;
        }
        int compareResult = myCompare(x, t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            //match the result
            return true;
        }
    }


    /**
     * print the tree contents in sorted order.
     */
    public void printTree() {

        if (isEmpty()) {
            System.out.println("Empty Tree");
        } else {
            printTree(root);
        }
    }

    /**
     * internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }


    /**
     * internal method to compute height of a subtree
     *
     * @param t the node that roots the subtree
     */
    private int height(BinaryNode<AnyType> t) {
        if (t == null) {
            return -1;
        } else {
            return 1 + Math.max(height(t.left), height(t.right));
        }
    }

}
