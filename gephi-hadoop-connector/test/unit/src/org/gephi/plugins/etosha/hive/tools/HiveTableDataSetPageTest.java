/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gephi.plugins.etosha.hive.tools;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kamir
 */
public class HiveTableDataSetPageTest {
    
    public HiveTableDataSetPageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPageContent method, of class HiveTableDataSetPage.
     */
    @Test
    public void testGetPageContent() {
        System.out.println("getPageContent");
        HiveTableDataSetPage instance = new HiveTableDataSetPage();
        String expResult = "";
        String result = instance.getPageContent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPageName method, of class HiveTableDataSetPage.
     */
    @Test
    public void testGetPageName() {
        System.out.println("getPageName");
        HiveTableDataSetPage instance = new HiveTableDataSetPage();
        String expResult = "";
        String result = instance.getPageName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
