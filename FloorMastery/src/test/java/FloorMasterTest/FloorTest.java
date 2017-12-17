///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package FloorMasterTest;
//
//import Data.OrderDao;
//import Data.OrderDaoImpl;
//import Data.OrderDaoInMemoryImpl;
//import Data.ProductDao;
//import Data.ProductDaoImpl;
//import Data.TaxDao;
//import Data.TaxDaoImpl;
//import Data.TaxDaoInMemoryImpl;
//import Model.Order;
//import Model.Products;
//import Model.Tax;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// *
// * @author apprentice
// */
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package FloorTest;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// *
// * @author apprentice
// */
//public class FloorTest {
//
//    OrderDao orderDao;
//    OrderDaoImpl daoImpl;
//    OrderDaoInMemoryImpl daoInMemory;
//    ProductDao productdao;
//    ProductDaoImpl productImpl;
//    TaxDao taxDao;
//    TaxDaoImpl taxImpl;
//    TaxDaoInMemoryImpl taxMemoryImpl;
//    Order dto;
//    Products product;
//    Tax tax;
//
//    public FloorTest() {
//
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        orderDao = ctx.getBean("OrderDao", OrderDao.class);
//
//    }
//
//    private static void clearData() {
//
//        File xx = new File("testFile.txt");
//        if (xx.exists()) {
//            xx.delete();
//            try {
//                xx.createNewFile();
//            } catch (IOException ex) {
//                Logger.getLogger(FloorTest.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    @BeforeClass
//    public static void setUpClass() { //This only happens before the test is run
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() { //This happens after the test is complete
//    }
//
//    @After
//    public void tearDown() { //this happens after every test has ran
//    }
//
//    @Test
//    public void getAll() {
//        int expected = 0;
//        //int actual = dao.list().size();
//
//  //      Assert.assertEquals(expected, actual);
//    }
//
//    public void add() {
//
//        int expectedSize = 1;
//        Integer expectedId = 1;  //Arrange
//        Order newOrder = new Order();
//
//        newOrder = orderDao.add(newOrder); // Act
//
//        Assert.assertEquals(newOrder, expectedId); //Assert
//        //Assert.assertEquals(expectedSize, orderDao.GetAll().size());
//
//    }
//
////    @Test
////    public void delete() { // This is broken
////
////        int expectedSum = 0;
////        //int actual = orderDao.OrderSum();
////
////        Assert.assertEquals(actual, expectedSum);
////
////    }
//
//}
