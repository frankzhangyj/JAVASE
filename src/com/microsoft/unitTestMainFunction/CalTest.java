package com.microsoft.unitTestMainFunction;

// 需要引入junit 和 hamcrest-core 的jar包
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class CalTest {
    @Test//使用这条注释来调用函数
    public void sum(){
        int sum = Cal.sum(1,2);
        System.out.println("sum = " + sum);
        // 调用Junit中的Assert类进行测试 脱离print
        Assert.assertEquals(1 + 2, sum);
    }

    // 合理编写随机测试，完善代码案例
    @Test
    public void sub() {
        int num1 = ThreadLocalRandom.current().nextInt();
        int num2 = ThreadLocalRandom.current().nextInt();

        int sub = Cal.sub(num1, num2);

        Assert.assertEquals(num1 - num2, sub);
    }
}
