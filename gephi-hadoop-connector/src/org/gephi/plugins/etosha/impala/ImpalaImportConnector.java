package org.gephi.plugins.etosha.impala;
 
import org.gephi.plugin.hadoop.connector.HadoopSQLImporterToolAction;
import org.etosha.MultiLayerNetwork;
import org.gephi.data.attributes.api.AttributeController;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.io.database.drivers.SQLUtils;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDefault;
import org.gephi.io.importer.api.ImportController;
 
import org.gephi.io.importer.plugin.database.ImporterEdgeList;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.plugin.hadoop.connector.HadoopClusterDefaults;
import org.gephi.project.api.Project;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

/**
 * This Connectoe loads edgelist and nodelist from hadoop clusters.
 * 
 * TODO: add GUI component to select / input the database and tablenames.
 * 
 * @author Mirko Kämpf
 * 
 */
public class ImpalaImportConnector {
    
    static public String defaultNQ = "SELECT nodes.id AS id, nodes.label AS label, nodes.url FROM nodes";
    static public String defaultEQ = "SELECT edges.source AS source, edges.target AS target, edges.label AS label, edges.weight AS weight FROM edges";

    public static ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
   
    public static void main(String[] args) {
        ImpalaImportConnector tool = new ImpalaImportConnector();
        tool.script();
    }

    /**
     * We still have hard coded:
     * 
     *       DB, username, credential
     *       node-query
     *       edge-query   
     * 
     * @return 
     */
    public static boolean ping() {
 
        boolean b = false;
        
        EdgeListImpalaDatabaseImpl db = new EdgeListImpalaDatabaseImpl();
        
        int impalaPort = HadoopClusterDefaults.IMPALA_DEAMON_PORT;
        
        db.setDBName(";auth=noSasl"); 
        
        db.setHost( HadoopClusterDefaults.IMPALA_DEAMON_IP );
        db.setPort( impalaPort );  
        
        db.setUsername("cloudera");
        db.setPasswd("cloudera");
        
        ImpalaJDBCDriver driver = new ImpalaJDBCDriver();
        
        db.setSQLDriver( driver );
        
        String url = SQLUtils.getUrl(db.getSQLDriver(), db.getHost(), db.getPort(), db.getDBName());
        
        javax.swing.JOptionPane.showMessageDialog(null, "[Request URL]\n" + url);
        
        System.out.println( ">>> Request URL  : " + url );

        b = driver.ping();
        
        return b;
        
    
    }

    /**
     * 
     * This method imports data into a new Project.
     * The test requires an Impala cluster with an edges and nodes
     * table.
     * 
     */
    public String script() {
        
        StringBuffer errorLog = new StringBuffer();
        errorLog.append("OK");
   
        if ( pc == null ) pc = Lookup.getDefault().lookup(ProjectController.class);
       
        //Init a project - and therefore a workspace
        Project pro = pc.getCurrentProject();
        
        if ( pro == null ) {
            pc.newProject();
            pro = pc.getCurrentProject();
        }
        
        // we allways use a new Workspace in an existing project ...
        Workspace workspace = pc.newWorkspace(pro);
        
        
        //Get controllers and models
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getModel();
        AttributeModel attributeModel = Lookup.getDefault().lookup(AttributeController.class).getModel();

        //Import database
        EdgeListImpalaDatabaseImpl db = new EdgeListImpalaDatabaseImpl();
        
        int impalaPort = HadoopClusterDefaults.IMPALA_DEAMON_PORT;
        
        db.setDBName(";auth=noSasl"); 
        
        db.setHost( HadoopClusterDefaults.IMPALA_DEAMON_IP );
        db.setPort( impalaPort );  
        
        db.setUsername("cloudera");
        db.setPasswd("cloudera");
        
        db.setSQLDriver(new ImpalaJDBCDriver());
        
        String url = SQLUtils.getUrl(db.getSQLDriver(), db.getHost(), db.getPort(), db.getDBName());
        
        javax.swing.JOptionPane.showMessageDialog(null, "[Request URL]\n" + url);

        System.out.println( ">>> Request URL  : " + url );
//        
//        MultiLayerNetwork.askUserForImportSettings( db );
//
//        String tab = MultiLayerNetwork.getSelected().tableName;
//        String nQ = MultiLayerNetwork.getSelected().nodelistQ;
//        String eQ = MultiLayerNetwork.getSelected().edgelistQ;
//
//        System.out.println();
//        System.out.println( ">>> Table name   : " + tab );
//        System.out.println( ">>> Nodes query  : " + nQ );
//        System.out.println( ">>> Edeges query : " + eQ );
//
//        db.setNodeQuery( nQ );
//        db.setEdgeQuery( eQ );
//        
        
        db.setNodeQuery( defaultNQ );
        db.setEdgeQuery( defaultEQ );
        
        ImporterEdgeList edgeListImporter = new ImporterEdgeList();
        
        Container container = null;
        
        try {
            
            container = importController.importDatabase(db, edgeListImporter);

            container.setAllowAutoNode( true );      //false = Don't create missing nodes
        
            if ( MultiLayerNetwork.getSelected().directed ) container.getLoader().setEdgeDefault(EdgeDefault.DIRECTED);   //Force DIRECTED
            else container.getLoader().setEdgeDefault(EdgeDefault.UNDIRECTED);   //Force UNDIRECTED

            //Append imported data to GraphAPI
            importController.process(container, new DefaultProcessor(), workspace);
            
            javax.swing.JOptionPane.showMessageDialog(null, "> Import finished sucessfully.");

            
        }
        catch (Exception ex ) {

            ex.printStackTrace();
            
            javax.swing.JOptionPane.showMessageDialog(null, "> Import finished with problems !");

            errorLog.append( ex.getMessage() );
            errorLog.append( ex.getCause() );
            
        }
        finally {
            if ( container != null ) {
                System.out.println( container.getReport().getText() );
                errorLog.append( container.getReport().getText() );
            }
        }
        
        
        //See if graph is well imported
        DirectedGraph graph = graphModel.getDirectedGraph();
        System.out.println("[Nodes:] " + graph.getNodeCount());
        System.out.println("[Edges:] " + graph.getEdgeCount());

        //Layout - 100 Yifan Hu passes
        System.out.println( "> Run the YifanHuLayout layout for 100 loops."  );

        YifanHuLayout layout = new YifanHuLayout(null, new StepDisplacement(1f));
        layout.setGraphModel(graphModel);
        layout.resetPropertiesValues();
        layout.initAlgo();
        for (int i = 0; i < 100 && layout.canAlgo(); i++) {
            layout.goAlgo();
        }
        layout.endAlgo();

        System.out.println( "> Done."  );

        return errorLog.toString();

    }
}
