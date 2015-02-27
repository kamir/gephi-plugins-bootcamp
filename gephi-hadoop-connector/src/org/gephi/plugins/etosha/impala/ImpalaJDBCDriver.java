package org.gephi.plugins.etosha.impala;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.gephi.io.database.drivers.SQLDriver;
import org.gephi.plugin.hadoop.connector.HadoopClusterDefaults;
import org.openide.util.Exceptions;

/**
 *
 * @author Mirko Kämpf
 */
public class ImpalaJDBCDriver implements SQLDriver {
    
    // jdbc:hive2://<host>:<port>/<dbName>;<sessionConfs>?<hiveConfs>#<hiveVars>
    
    String IMPALAD_HOST = HadoopClusterDefaults.IMPALA_DEAMON_IP;
    String IMPALAD_JDBC_PORT = "21050";
    String CONNECTION_URL = "jdbc:hive2://" + IMPALAD_HOST + ':' + IMPALAD_JDBC_PORT + "/;auth=noSasl";
    String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
 
    static Connection con = null;
            
    public ImpalaJDBCDriver() {
      System.out.println("> Load the Impala-JDBC Driver ... ");
      try {
          
            Class.forName(JDBC_DRIVER_NAME);

            // refresh the URL ...
            CONNECTION_URL = "jdbc:hive2://" + IMPALAD_HOST + ':' + IMPALAD_JDBC_PORT + "/;auth=noSasl";
 
            con = DriverManager.getConnection(CONNECTION_URL);
            
            System.out.println("> Done.");
            
      } 
      catch (ClassNotFoundException e) {
            e.printStackTrace();
      } 
      catch (SQLException ex) {  
            Exceptions.printStackTrace(ex);
      }  
    }
    
    public Connection getConnection(String connectionUrl, String username, String passwd) throws SQLException {
        System.out.println("> Impala-JDBC connection requested ... (ignore parameters) !!!");
        System.out.println("> " + CONNECTION_URL );
        return con;
    }
    
    public Connection getConnection() throws SQLException {
        return con;
    }

    /**
     * Based on the prefix we compare multiple instances of the object.
     * @return 
     */
    @Override
    public String getPrefix() {
        return "hive2";
    }

    @Override
    public String toString() {
        return "Impala-Hive:JDBC";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ImpalaJDBCDriver) {
            return ((ImpalaJDBCDriver) obj).getPrefix().equals(getPrefix());
        } 
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrefix().hashCode();
    }

   
    public boolean canUpdateFields() {
        return false;
    }

    boolean ping() {
        boolean b = false;
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            
            ResultSet r = stmt.executeQuery("Select * from edges");

            int i = 0;
            while( r.next() ) {
                i++;
            }
            
            javax.swing.JOptionPane.showMessageDialog(null, "[Edges] " + i);

            b = true;
        }
        catch(Exception ex){
            b = false;
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "[PROBLEM] " + ex.getMessage() );

        }
        return b;
    }
    
}
