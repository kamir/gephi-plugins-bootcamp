/**
 * 
 * We define a NetworkLayer as a METADATA entity which is aware of two
 * queries, one for an edge list and one for a node list.
 * 
 */

package org.etosha;

import static org.gephi.plugins.etosha.impala.ImpalaDynamicImportConnector.currentPartitionId;

/**
 *
 * @author kamir
 */
public class NetworkLayer {
    
    /**
     * In case of working with a METADATA repository, we can also access a 
     * public descriptor.
     * 
     */
    public String descriptiorURI = null;
    
    public String description = "This is an example, based on our research started in 2009.";
    
    public String tableName = "edgelist_es_de_amoklauf_von_erfurt";

    public String nodelistQ = "SELECT " + tableName + ".source AS id, " + tableName + ".source AS label FROM " + tableName;

    public String edgelistQ = "SELECT " + tableName + ".source AS source, " + tableName + ".target AS target " + tableName + ".q0 AS weight FROM " + tableName;
    
    public String partition_selector = "AND part=";

    // correlation networks are not directed
    // static and dependency networks are directed
    public boolean directed = false;   
    
    
}
