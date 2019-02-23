/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mortgagecalculator;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nicke
 */
public class mortgageCalculatorTest {
    
    Parser p = new Parser();
    ArrayList<String> list1 = new ArrayList();
    ArrayList<String> list2 = new ArrayList();
    ArrayList<String> list3 = new ArrayList();
    ArrayList<String> list4 = new ArrayList();
    ArrayList<String> list5 = new ArrayList();
    
    public mortgageCalculatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        String s1 = "10";
        String s2 = "10";
        String s3 = "10";
        list1.add(s1);
        list1.add(s2);
        list1.add(s3);
        
        s1 = "1";
        s2 = "1";
        s3 = "1";
        list2.add(s1);
        list2.add(s2);
        list2.add(s3);
        
        s1 = "9876543210";
        s2 = "57";
        s3 = "9";
        list3.add(s1);
        list3.add(s2);
        list3.add(s3);
        
        s1 = "-1";
        s2 = "57";
        s3 = "9";
        list4.add(s1);
        list4.add(s2);
        list4.add(s3);
        
        s1 = "2000";
        s2 = "100.01";
        s3 = "3";
        list5.add(s1);
        list5.add(s2);
        list5.add(s3);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void mathTestOk() {
        ArrayList<String> result = p.calculate(list1);
        assertEquals("0.13", (result.get(2)));
        
        result = p.calculate(list2);
        assertEquals("0.08", (result.get(2)));
        
        result = p.calculate(list3);
        assertEquals("472280395.9", (result.get(2)));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void mathTestFail() {
        ArrayList<String> result = p.calculate(list4);
        
    }
    @Test(expected = IllegalArgumentException.class)
    public void mathTestFail2() {
        ArrayList<String> result = p.calculate(list5);
    }
}
