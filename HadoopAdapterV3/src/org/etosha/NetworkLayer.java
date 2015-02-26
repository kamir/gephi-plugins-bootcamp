/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.etosha;

import static org.etosha.hdgs.impala.ImpalaDynamicImportConnector.currentPartitionId;

/**
 *
 * @author kamir
 */
public class NetworkLayer {
    
    public String tableName = "edgelist_es_de_amoklauf_von_erfurt";

    public String nodelistQ = "SELECT " + tableName + ".source AS id, " + tableName + ".source AS label FROM " + tableName;

    public String edgelistQ = "SELECT " + tableName + ".source AS source, " + tableName + ".target AS target " + tableName + ".q0 AS weight FROM " + tableName;
    
    public String partition_selector = "AND part=";

    // correlation networks are not directed
    // static and dependency networks are directed
    public boolean directed = false;   
    
    
}
