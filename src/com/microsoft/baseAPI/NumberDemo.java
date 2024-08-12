package com.microsoft.baseAPI;

public class NumberDemo {
    public static void main(String[] args) {
//        Integer x1 = new Integer(10);
//        Byte  x2 = new Byte((byte)0xFF);
//        Short x3 = new Short((short)10);
//        Float x4 = new Float(10f);
//        Double x5 = new Double(1.3d);
          // 可以调用字符串构造器
//        Double x7 = new Double("3.4");
//        Long x6 = new Long(44L);

        Integer num1 = 100; //自动装箱
//相当于编译器自动作以下的语法编译：
        Integer num3 = Integer.valueOf(100); // 对于正数在-128到127之间从缓存中返回包装类对象
                                            // 若数值不在区间内 则调用new 构造一个对象
        int num2 = num1;//自动拆箱
//相当于编译器自动作以下的语法编译：
        int num4 = num1.intValue();
// valueOf方法会先判断数值是否在-128 到 127之间 如果在会直接返回缓存中的对象 如果不在那么就直接new一个对象
        Integer x1 = Integer.valueOf(10);

        Byte x2 = Byte.valueOf((byte)0xFF);
        Short x3 = Short.valueOf((short)10);
        Float x4 = Float.valueOf(10f);
        Double x5 = Double.valueOf(1.3d);
        Long x6 = Long.valueOf(44L);
        String s7 = String.valueOf(777);
        // 先进行自动拆箱 然后进行自动装箱
        x1 += 3;
        x2 = (byte)(x2 - (byte)0xf);
        x3 = (short)(x3 + 3);
        x4 -= 4.0f;
        x5 += 4.00d;
        x6 += 6L;

        // 包装类的方法
        x2 = x1.byteValue();
        System.out.println("x2 = x1.byteValue() = " + x2);
        String s = "11";
        System.out.println("x1.toString() = " + x1.toString());
        x1 = Integer.parseInt(s);
        //String 和 包装类之间的转换
        Integer num = Integer.valueOf(10);
        String str = String.valueOf(num);
        num = Integer.parseInt(str);

        System.out.println("str = " + str);

        num = Integer.parseInt(str);
        System.out.println("str = " + str);
        // 对于整型或者char型 数值在-128到127之间 包装类对其进行缓存处理 存放的cache数组中 存储对应的包装类对象 提高效率

        Integer i1 = null; //因为Integer是类，所以值可以为null
        int j = i1; // 但是会报错NullPointerException 其相当于调用i.intValue()
    }
}
