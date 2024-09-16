package com.microsoft.generalConvert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListAndArray {
    /**
     * 包装类数组转化为基本数据类型数组
     * @param integers 包装列数组
     * @return 基本数组
     */
    public int[] IntArrTointArr(Integer[] integers) {
        int[] ints = Arrays.stream(integers).mapToInt(Integer::intValue).toArray();
        return ints;
    }

    /**
     * 基本数据类型数组转换为包装类数组
     * @param ints 基本数据类型数组
     * @return 包装类数组
     */
    public Integer[] intArrToIntArr(int[] ints) {
        Integer[] integers = Arrays.stream(ints).boxed().toArray(Integer[]::new);
        return integers;
    }

    /**
     * 包装类数组转换为list (Stream流)
     * @param integers 包装类数组
     * @return list
     */
    public List<Integer> IntArrToList1(Integer[] integers) {
        List<Integer> list = Arrays.stream(integers).collect(Collectors.toList());
        // java9 : list<Integer> list2 = new List<>(List.of(integers));
        return list;
    }

    /**
     * 包装类数组转换为list (Arrays.asList())
     * @param integers 包装类数组
     * @return list
     */
    public List<Integer> IntArrToList2(Integer[] integers) {
        // 注意 当单独使用Arrays.aslist() 此时创建出来的list不可扩容 只能修改
        List<Integer> list = new ArrayList<>(Arrays.asList(integers));
        return list;
    }

    /**
     * list转化为包装类数组 (Stream流)
     * @param list list
     * @return 包装类数组
     */
    public Integer[] listToIntArr1(List<Integer> list) {
        Integer[] integers = list.stream().toArray(Integer[]::new);
        return integers;
    }

    /**
     * list转换为包装类数组 (Collection.toArray(new Integer[0]))
     * @param list list
     * @return 包装类数组
     */
    public Integer[] listToIntArr2(List<Integer> list) {
        // T[] toArray(T[] a) 避免强制类型转换
        Integer[] integers = list.toArray(new Integer[0]);
        return integers;
    }

}
