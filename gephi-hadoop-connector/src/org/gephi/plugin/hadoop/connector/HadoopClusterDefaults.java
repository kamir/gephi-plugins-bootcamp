/**
 * 
 * This class handles all configurable variables for the Gephi-Hadoop-Connector.
 * 
 * By default we work with a Cloudera Quickstart-VM.
 * 
 * You can download the latest version here:
 * 
 * http://www.cloudera.com/content/cloudera/en/downloads/quickstart_vms/cdh-5-3-x.html
 * 
 **/

package org.gephi.plugin.hadoop.connector;

/**
 *
 * This cluster
 * 
 * @author kamir
 */
public class HadoopClusterDefaults {
    
//    public static String HIVE_SERVER2_IP = "127.0.0.1";
//    public static String HIVE_SERVER_IP = "127.0.0.1";
//    public static String MySQL_SERVER_IP = "127.0.0.1";
//
//    public static int HIVE_SERVER2_PORT = 0;
//    public static int HIVE_SERVER_PORT = 0;
//    public static int MySQL_SERVER_PORT = 0;

    public static String IMPALA_DEAMON_IP = "172.16.14.224";
    public static int IMPALA_DEAMON_PORT = 21050;

    public static String ETOSHA_DB = "/opendata/wiki";
    public static String ETOSHA_HOST = "www.semanpix.de";

    static void notifyChangeListeners() {
        System.out.println( ">>> changed the connector core settings ...");
    }

    public static String getEtoshaMetastoreURI() {
        return "http://" + ETOSHA_HOST + ETOSHA_DB;
    }

    
}
