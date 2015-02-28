/**
 *
 * The Impala-Connector is the core component which
 * loads data into Gephi.
 * 
 **/
package org.gephi.plugins.etosha.impala;

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
public class ImpalaImportConnectorTest {
    
    public ImpalaImportConnectorTest() {
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
     * Test of ping method, of class ImpalaImportConnector.
     */
    @Test
    public void testPing() {
        System.out.println( this.getClass() + " >>> ping");
        boolean expResult = true;
        
        boolean result = ImpalaImportConnector.ping();
        
        assertEquals(expResult, result);
        
    }

    /**
     * Test of script method, of class ImpalaImportConnector.
     */
    @Test
    public void testScript() {
        
        System.out.println( this.getClass() + " >>> script");
        
        ImpalaImportConnector instance = new ImpalaImportConnector();
        
        String el = instance.script();
        
        System.out.println( el.length() );
        
        //assertEquals( el.length(), 2 );
       
    }
    
}
