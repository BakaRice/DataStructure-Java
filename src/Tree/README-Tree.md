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