package ListStackQueue;

import java.util.Iterator;
import java.util.List;

public class MyList {
    /**
     * 在尾部add一个新的元素
     * 对于ArrayList以及LinkedList的构造时间复杂度都为O(N)
     *
     * @param lst 待插入List
     * @param N   待插入元素
     */
    public static void makeList1(List<Integer> lst, int N) {
        lst.clear();
        for (int i = 0; i < N; i++) {
            lst.add(i);
        }
    }

    /**
     * 在头部add一个新的元素
     * ArrayList的构造时间复杂度都为O(N^2),其在头部添加一个O(N）操作
     * LinkedList的构造时间复杂度都为O(N)
     *
     * @param lst
     * @param N
     */
    public static void makeList2(List<Integer> lst, int N) {
        lst.clear();
        for (int i = 0; i < N; i++) {
            lst.add(0, i);
        }
    }

    /**
     * 计算和
     * ArrayList的运行时间为O(N）
     * LinkedList的运行时间为O(N^2),对于每一个的get的调用是O(N）
     *
     * @param lst
     * @return
     */
    public static int sum(List<Integer> lst) {
        int tatal = 0;
        for (int i = 0; i < lst.size(); i++) {
            tatal += lst.get(i);
        }
        return tatal;
    }

    /**
     * 对于删除时，比较成功的想法是借助迭代器
     * 对于LinkedList 整个程序的时间就是线性的，
     * 对于ArrayList，因为数组的项必须要移动，所以花费仍然是二次时间
     * @param lst
     */
    public static void removeEven(List<Integer> lst) {
        Iterator<Integer> itr = lst.iterator();

        while (itr.hasNext()) {
            if (itr.next() % 2 == 0) {
                itr.remove();
            }
        }
    }

}
