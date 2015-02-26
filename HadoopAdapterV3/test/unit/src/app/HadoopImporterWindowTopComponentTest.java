/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

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
public class HadoopImporterWindowTopComponentTest {
    
    public HadoopImporterWindowTopComponentTest() {
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
    
    @Test
    public void getSomeDateFromSMW() {
        
         // String[] q = app.HadoopImporterWindowTopComponent.loadQueriesForNetworkLayer( "Amoklauf von Erfurt", "Ave layer1", null );
         String[] q = app.HadoopImporterWindowTopComponent.loadQueriesForNetworkLayer( "Lehman Brothers", "Lehman layer1", null );
         
         System.out.println( q[0] );
         System.out.println( q[1] );
         
         q = app.HadoopImporterWindowTopComponent.loadQueriesForNetworkLayer( "Lehman Brothers", "Lehman layer3", null );
         
         System.out.println( q[0] );
         System.out.println( q[1] );
         
         String q2 = app.HadoopImporterWindowTopComponent.loadNetworkLayersForTMN("Lehman Brothers", null );
         System.out.println( q2 );
         
         String q3 = app.HadoopImporterWindowTopComponent.loadTMNListeFromMetastore( null );
         System.out.println( q3 );
         
    }
    
}
