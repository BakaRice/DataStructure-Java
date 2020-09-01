# 树

## 二叉树
是一棵树，其中每个节点都不能多于两个的儿子，一棵二叉树的平均深度为o(sqrt(N))，
对于二叉搜索树，其深度的平均值为O(logN);
- 实现
```java
class BinaryNode 
{
        Object element;
        BinaryNode left;
        BinaryNode right;
}
```
- 二叉树的遍历以及应用
    - 先序遍历
    - 中序遍历
    - 后续遍历
    - 层次遍历
    
- 二叉查找树
    - 定义：对于树中的每个节点X，它的左子树中的所有项的值都比X小，右子树所有项的值都比X大。（左小右大）