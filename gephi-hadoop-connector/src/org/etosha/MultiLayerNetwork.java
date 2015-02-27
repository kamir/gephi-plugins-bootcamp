/**
 *  MultiLayerNetworks consist of multiple layers, as the name says.
 *  For now we use only the "default-layer", but we work already on
 *  merging strategies.
 */

package org.etosha;

import java.util.Hashtable;
import org.gephi.io.importer.plugin.database.EdgeListDatabaseImpl;
import org.gephi.plugins.etosha.impala.EdgeListImpalaDatabaseImpl;

/**
 *
 * @author kamir
 */
public class MultiLayerNetwork {

    static Hashtable<String,NetworkLayer> layers = null;
    
    static NetworkLayer defaultLayer = null;
    
    public static void initNetworks() {
        defaultLayer = new NetworkLayer();
        layers = new Hashtable<String,NetworkLayer>();
    }

    
    public static void setDefaultLayer( NetworkLayer nl ) {
        defaultLayer = nl;
    };
    
    /**
     * The current version works only with a defaut layer.
     * @return 
     */
    public static NetworkLayer getSelected() {
        if ( defaultLayer == null ) initNetworks();
        return defaultLayer;
    }

    public static void askUserForImportSettings(EdgeListImpalaDatabaseImpl db) {

        // Here we show the Impala-JDBC-Connection-Dialog ...
        
        
    }

  
    
}
