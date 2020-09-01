package Tree;

/**
 * 二叉查找树
 *
 * @param <AnyType>
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    // 二叉查找树的节点的定义
    private static class BinaryNode<AnyType> {

        //叶子节点的构造方法
        public BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        //包含左右子树的构造方法
        public BinaryNode(AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }

    //根节点
    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        root = null;
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

    public AnyType findMin() throws Exception {
        if (isEmpty()) throw new NullPointerException(); //Underflow
        return findMin(root).element;
    }

    public AnyType findMax() {
        if (isEmpty()) throw new NullPointerException();
        return findMax(root).element;
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }


    public void remove(AnyType x) {
        root = remove(x, root);
    }


    /**
     * @param x is item to search for 被查询的对象
     * @param t the node that roots the subtree 当前被查询的节点
     * @return
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) return false;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    /**
     * 通过递归的方式寻找最小值的
     * 对于二叉搜索树而言，最左侧的节点即为最小的节点，所以只需要
     * 一直递归寻找到最小的节点
     *
     * @param t
     * @return 最小值
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        // 递归实现
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        } else {
            return findMin(t.left);
        }
    }


    /**
     * 通过非递归的方式实现寻找最大值
     *
     * @param t
     * @return
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
     * 插入元素到二叉查找树的过程，
     * 对于一个二叉查找树，插入一个新的元素，新元素总是位于叶子节点
     * 这也就意味着如果进行多次的insert操作，树的高度不可避免的会增高，所以会引入AVL树，也就是平衡二叉树
     *
     * @param x
     * @param t
     * @return
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null);
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ;// Duplicate; do nothing
        return t;
    }


    /**
     * 二叉查找树的删除
     * 如果一个节点是叶子节点，可以被立即删除
     * 如果有一个儿子，可以在其父节点调整自己的链以绕过该节点后被删除。
     * 复杂的情况是处理有两个儿子的节点，一般的删除策略是用其右子树的的最小的数据替换该节点的数据并递归的删除那个节点。
     *
     * @param x
     * @param t
     * @return
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return t;
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }
}
