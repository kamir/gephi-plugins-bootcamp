/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.etosha.g2h;

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
public class SimpleGraphExporterTest {
    
    public SimpleGraphExporterTest() {
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
     * Test of main method, of class SimpleGraphExporter.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        SimpleGraphExporter.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of centerOnScreen method, of class SimpleGraphExporter.
     */
    @Test
    public void testCenterOnScreen() {
        System.out.println("centerOnScreen");
        SimpleGraphExporter instance = new SimpleGraphExporter();
        instance.centerOnScreen();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
