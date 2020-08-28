# 基本概念引入

1. 数学知识（略写）
2. 递归简论
当一个函数用它自己来定义就成为`递归`
    
    `f(x)=2f(x-1)+x^2`
    ```java
    class tmp {
        //一个递归的方法
        public static int f(int x)
        {
            if( x ==0 )
                return 0;
            else 
                return 2* f(x-1)+x*x;
        }
    }
    ```
3. 使用泛型

    泛型机制:如果除去对象的基本类型外，实现方法是相同的，那么我们就可以使用**泛型机制**来描述这种基本功能
    
    假设我们要编写一个fineMax方法，
    ```java
    class tmp{
        public static <AnyType> findMax(AnyType [] arr){
            int maxIndex = 0;
            
            for(int i =1;i < arr.length;i++){
                if(arr[i].compareTo(arr[maxIndex]) > 0){
                    maxIndex = i;
                }
            }
    
            return arr[maxIndex];
        }
    }
    ```
    对于上述的例子，我们无法保证`CompareTo`的合法性，所以我们应该使用的类型限界，
    很容易想到的是，只有在AnyType是Comparable的情况下才能保证CompareTo存在。
    - `public static <AnyType extends Comparable>`
    - `public static <AnyType extends Comparable<AnyType>>`
    
    但在改写成上述的做法后，仍然不是完美的，假设`Shape类` 实现了`Comparable<Shape>`, `Square类`继承`Shape类`，此时，我们所知道的只有`Square`实现`Comparable<Shape>`。
    于是，`Square IS-A Comparable<Shape>`,但它`IS-NOT-A Comparable<Square>`
    应该说，`AnyType IS-A Comparable<T>`,其中，T是AnyType的父类。由于我们不需要知道具体的父类，所以可以使用通配符。
    - `public static <AnyType extends Comparable<? super AnyType>>`
    
    使用上述的方式，我们使得`T`实现了`Comparable<S>`接口，其中`T IS-A S`。

4. 算法分析
    - 时间复杂度
    - 空间复杂度