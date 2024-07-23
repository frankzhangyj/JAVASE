package com.microsoft.collectionsFrameWork;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListDemo {
    /**
     * 测试ArraryList存储对象和普通数组的区别
     * ArraryList默认大小是16 扩容时大约1.5倍 newCapacity = (oldCapacity >> 1) + oldCapacity
     * String默认大小10 扩容为原来大约2倍 newCapacity = (oldCapacity << 1) + 2
     */
    @Test
    public void ArrayListTest() {
        // 动态修改的数组，与普通数组的区别就是它是没有固定大小的限制，我们可以添加或删除元素。
        ArrayList arrayList = new ArrayList();
        arrayList.add("fuck");
        arrayList.add(1);
        arrayList.add(1.1);
        System.out.println(arrayList);
        // 进行泛型限定
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("string");

        // 传统创建Student对象数组
        Student[] students = new Student[3];
        students[0] = new Student("a", 1);
        students[1] = new Student("b", 2);
        students[2] = new Student("c", 3);
        // 传统遍历数组得到所有信息
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].toString());
        }

        // 使用ArraryList
        ArrayList<Student> studentArrayList = new ArrayList<>();
        studentArrayList.add(new Student("a", 1));
        studentArrayList.add(new Student("b", 2));
        studentArrayList.add(new Student("c", 3));
        // 直接将集合中的所有对象以集合的形式按顺序打印 不需要写for循环
        // 实质上println先是调用String的valueOf 然后valueOf调用toString
        System.out.println(studentArrayList);
        // 等价 System.out.println(studentArrayList.toString());
    }

    /**
     * 测试ArraryList的各种方法
     */
    @Test
    public void ArraryListMethod() {
        ArrayList<Integer> integers_1 = new ArrayList<Integer>();
        ArrayList<Integer> integers_2 = new ArrayList<Integer>();

        integers_1.add(1);
        integers_1.add(2);
        integers_1.add(3);
        // 在下标2的位置添加一个4
        integers_1.add(2, 4);
        // 将下标为2的值修改为5
        integers_1.set(2, 5);

        integers_2.add(5);
        integers_2.add(6);
        integers_2.add(7);
        // 将集合2所有元素合并到集合1中 实质上就是将2拷贝一份到1中
        integers_1.addAll(integers_2);

        // 将集合1转换为普通Object类型数组
        Object[] objects = integers_1.toArray();
        for (int i = 0; i < objects.length; i++) {
            System.out.print(objects[i] + " ");
        }

        System.out.println("\n" + integers_1);
        // 清空集合中的所有数据 直接将每一项置为null 让gc自动进行垃圾回收
        integers_2.clear();
        System.out.println(integers_2);
        // 判断是否包含元素
        if (integers_1.contains(3)) System.out.println(true);
        // 数组用length 集合用size()
        for (int i = 0; i < integers_1.size(); i++) {
            // 利用下标访问集合中的每一个元素
            System.out.print(integers_1.get(i));
        }

        System.out.println();

        for (Integer value : integers_1) {
            System.out.print(value + 1);
        }

        // 排序（默认从小到大）
        Collections.sort(integers_1);
        System.out.println(integers_1);
        // 逆序
        Collections.reverse(integers_1);
        System.out.println(integers_1);

        // 截取集合 左闭右开
        System.out.println(integers_1.subList(1,4));
    }

    /**
     * 测试各种方法2
     */
    @Test
    public void ArraryListMethod_2() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("Tom");
        stringArrayList.add("jane");
        stringArrayList.add("jack");
        stringArrayList.add("Tom");
        stringArrayList.add("frank");
        // 返回首个为Tom的下标
        System.out.println(stringArrayList.indexOf("Tom"));
        // 返回最后一个是Tom的下标
        System.out.println(stringArrayList.lastIndexOf("Tom"));

        if (!stringArrayList.isEmpty()) System.out.println("不为空");

        // 删除元素 遍历集合 将所有“Tom” 删除（底层将值为Tom的下标之后的所有元素通过copy整体向前移动一个下标 最后一个元素置为null）
        stringArrayList.remove("Tom");
        // 将下标为1 的元素删除 同理上面
        stringArrayList.remove(1);
        // 将集合与参数集合中匹配的元素移除(参数是本身就相当于clear 只不过效率比clear低)
        stringArrayList.removeAll(stringArrayList);

        stringArrayList.add("Tom");
        stringArrayList.add("jane");
        stringArrayList.add("jack");
        stringArrayList.add("Tom");
        stringArrayList.add("frank");

        // 使用lambda表达式将所有元素转化大小写
        stringArrayList.replaceAll(e -> e.toLowerCase());
        System.out.println(stringArrayList);
        stringArrayList.replaceAll(e -> e.toUpperCase());
        System.out.println(stringArrayList);

        ArrayList<String> stringArrayList_1 = new ArrayList<>();
        stringArrayList_1.add("TOM");
        // retainAll取两个集合的交集（将相同的元素保留 将不同的元素删去）
        stringArrayList.retainAll(stringArrayList_1);
        System.out.println(stringArrayList);
    }
}

class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


