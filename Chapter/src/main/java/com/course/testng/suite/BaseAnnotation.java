package com.course.testng.suite;

import org.testng.annotations.*;

public class BaseAnnotation {

//    @BeforeSuite
//    public void beforeSuite(){
//        System.out.println("beforeSuite---->run");
//    }
//
//    @AfterSuite
//    public void afterSuite(){
//        System.out.println("afterSuite---->run");
//    }
//
//    @BeforeClass
//    public void beforeClass(){
//        System.out.println("beforeClass--->run");
//    }
//
//    @AfterClass
//    public void afterClass(){
//        System.out.println("afterClass--->run");
//    }
//
//    @BeforeMethod
//    public void beforeMethod(){
//        System.out.println("beforeMethod-->run");
//    }
//
//    @AfterMethod
//    public void afterMethod(){
//        System.out.println("afterMethod-->run");
//    }

    @BeforeGroups(value = "t1")
    public void beforeGroups(){
        System.out.println("t1组中的beforeGroups->run");
    }

    @AfterGroups(value = "t1")
    public void afterGroups(){
        System.out.println("t1组中的afterGroups->run");
    }

    @Test(groups = "t1")
    public void test01(){
        System.out.println("t1组中的test01->run");
    }

    @Test(groups = "t1")
    public void test02(){
        System.out.println("t1组中的test02->run");
    }

    @Test(groups = "t2")
    public void test03(){
        System.out.println("t2组中的test03->run");
    }

    @Test(groups = "t2")
    public void test04(){
        System.out.println("t2组中的test04->run");
    }
}
