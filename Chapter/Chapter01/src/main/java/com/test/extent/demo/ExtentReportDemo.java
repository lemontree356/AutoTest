package com.test.extent.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtentReportDemo {

    @Test
    public void test01(){
        Assert.assertEquals(1, 2);
    }

    @Test
    public void test02(){
        Assert.assertEquals("aaa", "bbb");
    }

    @Test
    public void test03(){
        Assert.assertEquals(1, 1);
    }

    @Test
    public void logDemo(){
        System.out.println("这是我的日志记录");
        throw new RuntimeException("这是我的运行时异常");
    }
}
