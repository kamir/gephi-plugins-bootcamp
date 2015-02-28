/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.etosha.hdgs.mln;

import java.util.Properties;
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
public class TimedependentMultiLayerNetworkManagerTest {
    
    public TimedependentMultiLayerNetworkManagerTest() {
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
     * Test of componentOpened method, of class TimedependentMultiLayerNetworkManager.
     */
    @Test
    public void testComponentOpened() {
        System.out.println("componentOpened");
        TimedependentMultiLayerNetworkManager instance = new TimedependentMultiLayerNetworkManager();
        instance.componentOpened();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of componentClosed method, of class TimedependentMultiLayerNetworkManager.
     */
    @Test
    public void testComponentClosed() {
        System.out.println("componentClosed");
        TimedependentMultiLayerNetworkManager instance = new TimedependentMultiLayerNetworkManager();
        instance.componentClosed();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeProperties method, of class TimedependentMultiLayerNetworkManager.
     */
    @Test
    public void testWriteProperties() {
        System.out.println("writeProperties");
        Properties p = null;
        TimedependentMultiLayerNetworkManager instance = new TimedependentMultiLayerNetworkManager();
        instance.writeProperties(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readProperties method, of class TimedependentMultiLayerNetworkManager.
     */
    @Test
    public void testReadProperties() {
        System.out.println("readProperties");
        Properties p = null;
        TimedependentMultiLayerNetworkManager instance = new TimedependentMultiLayerNetworkManager();
        instance.readProperties(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of centerOnScreen method, of class TimedependentMultiLayerNetworkManager.
     */
    @Test
    public void testCenterOnScreen() {
        System.out.println("centerOnScreen");
        TimedependentMultiLayerNetworkManager instance = new TimedependentMultiLayerNetworkManager();
        instance.centerOnScreen();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadNetworkLayersForTMN method, of class TimedependentMultiLayerNetworkManager.
     */
    @Test
    public void testLoadNetworkLayersForTMN() {
        System.out.println("loadNetworkLayersForTMN");
        String networkName = "";
        TimedependentMultiLayerNetworkManager comp = null;
        String expResult = "";
        String result = TimedependentMultiLayerNetworkManager.loadNetworkLayersForTMN(networkName, comp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadQueriesForNetworkLayer method, of class TimedependentMultiLayerNetworkManager.
     */
    @Test
    public void testLoadQueriesForNetworkLayer() {
        System.out.println("loadQueriesForNetworkLayer");
        String networkName = "";
        String layerName = "";
        TimedependentMultiLayerNetworkManager comp = null;
        String[] expResult = null;
        String[] result = TimedependentMultiLayerNetworkManager.loadQueriesForNetworkLayer(networkName, layerName, comp);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadTMNListeFromMetastore method, of class TimedependentMultiLayerNetworkManager.
     */
    @Test
    public void testLoadTMNListeFromMetastore() {
        System.out.println("loadTMNListeFromMetastore");
        TimedependentMultiLayerNetworkManager comp = null;
        String expResult = "";
        String result = TimedependentMultiLayerNetworkManager.loadTMNListeFromMetastore(comp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
