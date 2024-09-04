package com.microsoft.oop;

import java.util.Objects;

/**
 * 一个完美的equals写法
 */
public class EqualsTest {
    private int id;
    private String name;

    /**
     * 调用对象不能为null 但是参数对象可以为null
     * @param otherObj 参数对象
     * @return true or false
     */
    @Override
    public boolean equals(Object otherObj) {
        // 首先判断是否引用相同的地址
        if (this == otherObj) {
            return true;
        }
        // 如果other为null直接判false
        if (otherObj == null) {
            return false;
        }
        // 如果子类中分别定义了不同的equals方法 那么就调用getClass方法 避免出现不同类成员之间的判断
        if (!(this.getClass() == otherObj.getClass())) {
            return false;
        }
        // 如果子类共用父类中的equals方法 那么就将父类的equals方法定义为final 并且用instanceof判断
        if (!(otherObj instanceof EqualsTest)) {
            return false;
        }
        // 强制类型转换
        EqualsTest other = (EqualsTest) otherObj;
        // 具体的实例字段的判断 如果字段可以为空那么建议使用Objects的equals
        return this.id == other.id && Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
