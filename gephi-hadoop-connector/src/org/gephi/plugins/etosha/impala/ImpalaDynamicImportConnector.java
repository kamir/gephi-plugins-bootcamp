package org.gephi.plugins.etosha.impala;
 
import org.gephi.plugin.hadoop.connector.HadoopSQLImporterToolAction;
import org.etosha.MultiLayerNetwork;
import org.gephi.data.attributes.api.AttributeController;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.io.database.drivers.SQLUtils;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDefault;
import org.gephi.io.importer.api.ImportController;
 
import org.gephi.io.importer.plugin.database.ImporterEdgeList;
import org.gephi.io.processor.plugin.DynamicProcessor;
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
public class ImpalaDynamicImportConnector {

    public static int currentPartitionId = 0;
    
    public static void main(String[] args) {
        ImpalaDynamicImportConnector tool = new ImpalaDynamicImportConnector();
        tool.script();
    }

    public void script() {
   
        //Init a project - and therefore a workspace
        ProjectController pc = HadoopSQLImporterToolAction.pc;
        Project pro = pc.getCurrentProject();
        
        // we allwas load a new Workspace in an existing project ...
        Workspace workspace = pc.getCurrentWorkspace();
        
        //Get controllers and models
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getModel();
        AttributeModel attributeModel = Lookup.getDefault().lookup(AttributeController.class).getModel();

        //Import database
        EdgeListImpalaDatabaseImpl db = new EdgeListImpalaDatabaseImpl();
        
        db.setDBName(";auth=noSasl"); 
        db.setHost( HadoopClusterDefaults.IMPALA_DEAMON_IP );
        db.setPort(21050);  
        
        db.setUsername("cloudera");
        db.setPasswd("cloudera");
        
        db.setSQLDriver(new ImpalaJDBCDriver());
        
        String tab = MultiLayerNetwork.getSelected().tableName;
        String nQ = MultiLayerNetwork.getSelected().nodelistQ;
        String eQ = MultiLayerNetwork.getSelected().edgelistQ + " " + MultiLayerNetwork.getSelected().partition_selector + " " + currentPartitionId; ;
        
        db.setNodeQuery( nQ );
        db.setEdgeQuery( eQ );
        
        System.out.println( "EDGES\n" + eQ );
        System.out.println( "NODES\n" + nQ );
        
//        db.setNodeQuery("SELECT nodes.id AS id, nodes.label AS label, nodes.url FROM nodes");
//        db.setEdgeQuery("SELECT edges.source AS source, edges.target AS target, edges.label AS label, edges.weight AS weight FROM edges");
        
        int part = currentPartitionId;
        
        ImporterEdgeList edgeListImporter = new ImporterEdgeList();
        
        Container container = null;
        
        String url = SQLUtils.getUrl(db.getSQLDriver(), db.getHost(), db.getPort(), db.getDBName());
        
        System.out.println( url );
        
        javax.swing.JOptionPane.showMessageDialog(null,"Go ... Impala! \n> " + url );

        container = importController.importDatabase(db, edgeListImporter);

        container.setAllowAutoNode( true );      //false = Don't create missing nodes
        
//        if ( MultiLayerNetwork.getSelected().directed ) container.getLoader().setEdgeDefault(EdgeDefault.DIRECTED);   //Force DIRECTED
//        else container.getLoader().setEdgeDefault(EdgeDefault.UNDIRECTED);   //Force UNDIRECTED

        //Append imported data to GraphAPI
        //importController.process(container, new DefaultProcessor(), workspace);
 
        //Initialize the DynamicProcessor - which will append the container to the workspace
        DynamicProcessor dynamicProcessor = new DynamicProcessor();
        dynamicProcessor.setDateMode(false);    //Set 'true' if you set real dates (ex: yyyy-mm-dd), it's double otherwise
        dynamicProcessor.setLabelmatching(true);   //Set 'true' if node matching is done on labels instead of ids

        //Set date for this file
        dynamicProcessor.setDate("" + part);

        //Process the container using the DynamicProcessor
        importController.process(container, dynamicProcessor, workspace);

    }
}
