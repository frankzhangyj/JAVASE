package com.microsoft.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream 是对数据的运算 与cpu打交道
 * Collection 是数据的存储 与内存打交道
 *
 * Stream 自己不会存储元素 不会更改源对象 延迟执行(需要结果时再执行)
 *
 * 一旦产生结果 之后不会再使用
 * 类似于sql进行查询时的各种条件 用于处理java中的数据
 */
public class StreamAPI {

    /**
     * 实例化
     */
    @Test
    public void test1() {

        // 通过集合创建 Collection接口中提供了两个Stream默认方法
        Employee2Data employee2Data = new Employee2Data();
        List<Employee2> list = employee2Data.getList();
        // 创建一个顺序流
        Stream<Employee2> stream = list.stream();
        // 创建一个并行流
        Stream<Employee2> employee2Stream = list.parallelStream();


        // 通过数据创建 Arrays中的Stream方法得到数组的Stream实例
        int[] ints = new int[]{1, 2, 3};

        IntStream stream1 = Arrays.stream(ints);


        // 通过Stream中的of() 相当于先创建一个集合 然后再得到stream
        Stream<Integer> integerStream = Stream.of(1, 2, 3);


        // 创建无限流
        // Stream中静态方法iterate 迭代
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);
        // generate
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    /**
     * 中间操作（筛序与切片）
     * filter() skip() limit() distinct()
     */
    @Test
    public void test2() {
        Employee2Data employee2Data = new Employee2Data();
        List<Employee2> list = employee2Data.getList();

        Stream<Employee2> stream = list.stream();
        //  filter(Predict p)用于筛序符合条件的数据
        stream.filter(e -> e.getSalary() > 3000).forEach(System.out::println);
        // limit 用于截断数据 (注意Stream得到结果后就无效了 需要重新创建一个Stream)
        list.stream().limit(3).forEach(e -> System.out.println(e));
        // skip 用于跳过数据 如果跳过的个数大于数据源中数据个数 那么就返回一个空Stream
        list.stream().skip(3).forEach(System.out::println);
        // distinct 利用hashCode 和 equals 来去除重复数据
        list.stream().distinct().forEach(System.out::println);
    }

    /**
     * 中间操作（map映射）
     * map(Function f)
     * flatMap(Function f)
     *
     * map()方法用于将一个Stream的每个元素映射成另一个元素并转换成一个新的Stream
     * 1.将流中的元素转换为另一种形式
     * 2.将流中的元素应用特定的逻辑转换
     */
    @Test
    public void test3() {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        // aa -> AA bb -> BB cc -> CC
        // 原始流中全是小写字符串 通过map 将所有字符串转换为大写后得到一个新流


        // 原来流中都是Employee2实例对象 通过map 得到这些实例对象的名字 作为一个新流
        List<Employee2> employee2s = Employee2Data.getList();
        Stream<String> nameStream = employee2s.stream().map(e -> e.getName());
        // 然后通过对新流利用filter进行过滤 得到名字大于3的姓名
        nameStream.filter(name -> name.length() > 3).forEach(System.out::println);

        // 利用 map() 将String类型字符串转换为Character进行操作 Stream<Character>(key) -> str(value)
        Stream<Stream<Character>> streamStream = list.stream().map(str -> fromStringToStream(str));
        // 有点类似于集合中套集合 需要两层遍历才能取到值
        streamStream.forEach(e -> {
            e.forEach(System.out::println);
        });

        // 利用 flatMap() 直接将str全部打散为Charactor 然后一次遍历即可得到数值
        Stream<Character> characterStream = list.stream().flatMap(str -> fromStringToStream(str));
        // 有点类似集合中的addAll方法 不会将整个集合加到另一个集合中 而是取出元素挨个加入到集合中
        characterStream.forEach(System.out::println);
    }

    // 将String字符串打散为 Character 以Stream返回
    public Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> characters = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            characters.add(c);
        }

        return characters.stream();
    }

    /**
     * 中间操作（排序）
     * sorted()
     */
    @Test
    public void test4() {
        // 自然排序 其内部已经实现了Comparable接口的compareTo抽象函数
        List<Integer> list = Arrays.asList(3, 4, 1, 5, 7);
        list.stream().sorted().forEach(System.out::println);

        // 定制排序 使用comparator实现
        List<Employee2> list1 = Employee2Data.getList();
        list1.stream().sorted((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary())).forEach(System.out::println);
    }

    /**
     * 终止操作（匹配与查找）
     */
    public void test5() {
        List<Employee2> list = Employee2Data.getList();
        // 判断是否全部满足要求
        boolean b = list.stream().allMatch(e -> e.getSalary() > 30000);
        // 判断是否任意一个满足要求
        boolean b1 = list.stream().anyMatch(e -> e.getSalary() > 30000);
        // 判断是否没有一个满足要求
        boolean b2 = list.stream().noneMatch(e -> e.getSalary() > 300000);
        // 得到stream中数据的个数
        long count = list.stream().count();
        // 找到Stream任意一个数据 并将数据保存在Optional集合中
        Optional<Employee2> any = list.stream().findAny();
        // 找到Stream第一个数据
        Optional<Employee2> first = list.stream().findFirst();
        // 得到Stream中salary最大的一个
        Optional<Employee2> max = list.stream().max((e1, e2) -> e1.getSalary() - e2.getSalary());
        // 得到salary最小的一个
        Optional<Employee2> min = list.stream().min((e1, e2) -> e1.getSalary() - e2.getSalary());
        // 内部迭代
        list.stream().forEach(System.out::println);
        list.forEach(System.out::println);
        // 外部迭代 通过迭代器迭代
        Iterator<Employee2> iterator = list.iterator();
        while (iterator.hasNext()) {
            Employee2 next = iterator.next();
        }
    }

    /**
     * 终止操作（规约）
     * reduce()
     */
    @Test
    public void test6() {
        List<Employee2> list = Employee2Data.getList();
        // reduce(default, biFunction) 将Stream中数据全部按照biFunction合并起来 并且有默认值
        int reduce = list.stream().map(Employee2::getSalary).reduce(0, Integer::sum);
        // 直接全部相加 结果保存到Optional集合中
        Optional<Integer> reduce1 = list.stream().map(Employee2::getSalary).reduce(Integer::sum);
    }

    /**
     * 终止操作（收集）
     * Collect()
     */
    @Test
    public void test7() {
        List<Employee2> list = Employee2Data.getList();
        // 将stream中的数据转换为list
//        List<Employee2> collect = list.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        // 将stream中的数据转换为Set
//        Set<Employee2> collect1 = list.stream().filter(e -> e.getSalary() > 4999).collect(Collectors.toSet());

        Integer[] arr = new Integer[]{1, 2, 3, 4};

        List<Integer> list2 = Arrays.asList(arr);

        int[] array = list2.stream().mapToInt(Integer::valueOf).toArray();
        for (int i : array) {
            System.out.println(i);
        }

    }

}
