package org.etosha.hdgs.mln;

import java.awt.Color;
import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import org.etosha.MultiLayerNetwork;
import org.etosha.NetworkLayer;
import org.etosha.utils.TextAreaAsOutputStream;
import org.gephi.plugins.etosha.mysql.MySQLImportConnector;

import org.gephi.data.attributes.api.AttributeController;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.UndirectedGraph;
import org.gephi.plugin.hadoop.connector.HadoopClusterDefaults;
import org.gephi.plugins.etosha.impala.ImpalaDynamicImportConnector;
import org.gephi.plugins.etosha.impala.ImpalaImportConnector;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.netbeans.api.settings.ConvertAsProperties;

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//app//HadoopImporterWindow//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "TimedependentMultiLayerNetworkManager",
        iconBase = "org/etosha/hdgs/mln/icon_netzwerk.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "app.TimedependentMultiLayerNetworkManager")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_HadoopImporterWindowAction",
        preferredID = "TimedependentMultiLayerNetworkManager"
)
@Messages({
    "CTL_HadoopImporterWindowAction=HadoopImporterWindow",
    "CTL_TimedependentMultiLayerNetworkManager=HadoopImporter Window",
    "HINT_TimedependentMultiLayerNetworkManager=This is a HadoopImporter window"
})
public final class TimedependentMultiLayerNetworkManager extends TopComponent {
    
//    public static String HIVE_SERVER_IP = HadoopClusterDefaults.HIVE_SERVER_IP;
//    public static String MySQL_SERVER_IP = HadoopClusterDefaults.MySQL_SERVER_IP;
    
    // Variables declaration - do not modify                     
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlEdges;
    private javax.swing.JLabel jlNodes;
    private javax.swing.JRadioButton jrbDirected;
    private javax.swing.JRadioButton jrbHierarchical;
    private javax.swing.JRadioButton jrbMixed;
    private javax.swing.JRadioButton jrbUndirected;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JLabel ws_info_f1;
    // End of variables declaration                   
    

    boolean isStandalone = false;
    
    TextAreaAsOutputStream out = null;
    
    public TimedependentMultiLayerNetworkManager() {
        
        initComponents();
        
        out = new TextAreaAsOutputStream( jTa_LOG , "[LOGGING] ");
        
        System.setOut( new PrintStream( out ) );
        
        System.out.println( "> GO ...");
        
        setName(Bundle.CTL_TimedependentMultiLayerNetworkManager());
        setToolTipText(Bundle.HINT_TimedependentMultiLayerNetworkManager());
        
        this.jLabel7.setText( HadoopClusterDefaults.getEtoshaMetastoreURI() );
        
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);

        resetWorkspace();
                
    }

        /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        edgesStatic = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nodes = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaQuery = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaResponse = new javax.swing.JTextArea();
        jcbDIRECTED = new javax.swing.JCheckBox();
        jcbAppend = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaNTMNListe = new javax.swing.JTextArea();
        jButton8 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtaLAYERLISTE = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jSpinnerPARTITION = new javax.swing.JSpinner();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTa_LOG = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jPanel1.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton4, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jLabel5.text")); // NOI18N

        jTextField1.setForeground(new java.awt.Color(255, 153, 0));
        jTextField1.setText(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jTextField1.text")); // NOI18N

        jTextField2.setForeground(new java.awt.Color(255, 153, 0));
        jTextField2.setText(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jTextField2.text")); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton5, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton6, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jButton6.text")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jLabel4.text")); // NOI18N

        edgesStatic.setText(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.edgesStatic.text")); // NOI18N
        edgesStatic.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jLabel3.text")); // NOI18N

        nodes.setText(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.nodes.text")); // NOI18N
        nodes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        nodes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nodesActionPerformed(evt);
            }
        });

        jtaQuery.setColumns(20);
        jtaQuery.setRows(5);
        jtaQuery.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jtaQuery.border.title"))); // NOI18N
        jScrollPane1.setViewportView(jtaQuery);

        jtaResponse.setColumns(20);
        jtaResponse.setRows(5);
        jtaResponse.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jtaResponse.border.title"))); // NOI18N
        jScrollPane2.setViewportView(jtaResponse);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(edgesStatic, javax.swing.GroupLayout.PREFERRED_SIZE, 1268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nodes, javax.swing.GroupLayout.PREFERRED_SIZE, 1268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(449, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edgesStatic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jcbDIRECTED, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jcbDIRECTED.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbAppend, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jcbAppend.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton7, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jButton7.text")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jtaNTMNListe.setColumns(20);
        jtaNTMNListe.setRows(5);
        jScrollPane3.setViewportView(jtaNTMNListe);

        org.openide.awt.Mnemonics.setLocalizedText(jButton8, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jButton8.text")); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jtaLAYERLISTE.setColumns(20);
        jtaLAYERLISTE.setRows(5);
        jScrollPane4.setViewportView(jtaLAYERLISTE);

        jLabel7.setFont(new java.awt.Font("Seravek", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 204));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jLabel7.text")); // NOI18N

        jSpinnerPARTITION.setModel(new javax.swing.SpinnerNumberModel());

        jTextField3.setText(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jTextField3.text")); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jLabel8.text")); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton7))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jcbDIRECTED)
                        .addGap(18, 18, 18)
                        .addComponent(jcbAppend)
                        .addGap(18, 18, 18)
                        .addComponent(jSpinnerPARTITION, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel5)))
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbDIRECTED)
                    .addComponent(jcbAppend)
                    .addComponent(jSpinnerPARTITION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jButton7)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        jPanel6.setLayout(new java.awt.BorderLayout());

        jTa_LOG.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jTa_LOG.setColumns(20);
        jTa_LOG.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        jTa_LOG.setForeground(new java.awt.Color(0, 102, 102));
        jTa_LOG.setLineWrap(true);
        jTa_LOG.setRows(30);
        jTa_LOG.setWrapStyleWord(true);
        jTa_LOG.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jTa_LOG.border.title"))); // NOI18N
        jScrollPane5.setViewportView(jTa_LOG);

        jPanel6.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 1761, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 636, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 1761, Short.MAX_VALUE)
                .addComponent(jButton3))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 636, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1847, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(TimedependentMultiLayerNetworkManager.class, "TimedependentMultiLayerNetworkManager.jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        if ( isStandalone ) System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void jrbDirectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbDirectedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbDirectedActionPerformed

    private void copyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuItemActionPerformed
             
    }//GEN-LAST:event_copyMenuItemActionPerformed

    private void pasteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteMenuItemActionPerformed
        
    }//GEN-LAST:event_pasteMenuItemActionPerformed

    private void deleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuItemActionPerformed
        
    }//GEN-LAST:event_deleteMenuItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

//        setWaiting();
//        HiveImportConnector.main( null );
//        setDone();       
        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        setWaiting();
        MySQLImportConnector.main( null );
        setDone();  
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        String networkName = this.jTextField1.getText(); 
            
        System.out.println( "TMN_name        : " + networkName );
            
        String liste = loadNetworkLayersForTMN( networkName, this );
        
        this.jtaLAYERLISTE.setText( liste );
                
    }//GEN-LAST:event_jButton4ActionPerformed

    private void nodesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nodesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nodesActionPerformed

    
        
    public static boolean importDirected = true;
    public static boolean appendAsNewTimeRange = true;
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
 
//        this.jTextField1.setForeground(Color.orange);
//        
//        importDirected = this.jcbDIRECTED.isSelected();
//        appendAsNewTimeRange = this.jcbAppend.isSelected();
//        
//        NetworkLayer nl = new NetworkLayer();
//           
//        nl.edgelistQ = this.edgesStatic.getText();
//        nl.nodelistQ = this.nodes.getText();       
//        nl.directed = importDirected;
//        
//        MultiLayerNetwork.setDefaultLayer(nl);
//        
//        try { 
//            jButton2ActionPerformed( null );
//            this.jTextField1.setForeground(Color.green);
//        } 
//        catch( Exception ex ) { 
//            ex.printStackTrace();
//            this.jTextField1.setForeground(Color.red);
//        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        this.jTextField2.setForeground(Color.orange);
        
        
        try { 
            
            // load the data from WIKI
            
            String networkName = this.jTextField1.getText();
            String layerName = this.jTextField2.getText();
            
            System.out.println( "TMN_name        : " + networkName );
            System.out.println( "TMN_layern_name : " + layerName );
            
            
            String[] q = loadQueriesForNetworkLayer( networkName, layerName, this );
            
                 
            System.out.println( q[0] );
            System.out.println( q[1] );
            
            this.nodes.setText( q[0]);
            this.edgesStatic.setText( q[1]);
            
            NetworkLayer nl = new NetworkLayer();
        
            nl.edgelistQ = this.edgesStatic.getText();
            nl.nodelistQ = this.nodes.getText();       
        
            MultiLayerNetwork.setDefaultLayer(nl);

            this.jTextField2.setForeground(Color.green);
        } 
        catch( Exception ex ) { 
            ex.printStackTrace();
            
            NetworkLayer nl = new NetworkLayer();
        
            nl.edgelistQ = "EL ???";
            nl.nodelistQ = "NL ???";       
        
            MultiLayerNetwork.setDefaultLayer(nl);

            this.jTextField2.setForeground(Color.red);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        resetWorkspace();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String liste = loadTMNListeFromMetastore( this );
        
        this.jtaNTMNListe.setText( liste );
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField edgesStatic;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinnerPARTITION;
    private javax.swing.JTextArea jTa_LOG;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JCheckBox jcbAppend;
    private javax.swing.JCheckBox jcbDIRECTED;
    private javax.swing.JTextArea jtaLAYERLISTE;
    private javax.swing.JTextArea jtaNTMNListe;
    private javax.swing.JTextArea jtaQuery;
    private javax.swing.JTextArea jtaResponse;
    private javax.swing.JTextField nodes;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
    
    private void updateWorkspaceStatus() {
        
//        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
//
//        Workspace workspace = pc.getCurrentWorkspace();
//    
//        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getModel();
//        AttributeModel attributeModel = Lookup.getDefault().lookup(AttributeController.class).getModel();
//
//        boolean isDirected = graphModel.isDirected();
//        boolean isUnDirected = graphModel.isUndirected();
//        boolean isHierarchical = graphModel.isHierarchical();
//        boolean isMixed = graphModel.isMixed();
//        
//        this.jrbDirected.setSelected(isDirected);
//        this.jrbUndirected.setSelected(isDirected);
//        this.jrbHierarchical.setSelected(isHierarchical);
//        this.jrbMixed.setSelected(isMixed);
//        
//        UndirectedGraph graph = graphModel.getUndirectedGraph();
//        System.out.println("Nodes: " + graph.getNodeCount());
//        System.out.println("Edges: " + graph.getEdgeCount());
//        
//        this.jlEdges.setText(""+graph.getEdgeCount());        
//        this.jlNodes.setText(""+graph.getNodeCount());        

        
    }

    private void resetWorkspace() {
        System.out.println(">>> RESET workspace now ... (no save!!!)");

        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.closeCurrentProject();
    
        pc.newProject();
    }

    private void setWaiting() {
        // indicate activity via MousePointer ...
    }

    private void setDone() {

    }

    public void centerOnScreen() {
         
        setVisible(true);
        System.out.println( ">>> open Hadoop connector GUI ... ");
    }
    
    public static String loadNetworkLayersForTMN(String networkName, TimedependentMultiLayerNetworkManager comp){
        
        StringBuffer q = new StringBuffer();
        
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;

        try {

            httpClient = HttpClients.createDefault();
            httpGet = new HttpGet();

            String query = "[[Modification date::+]]|?Modification date|sort=Modification date|order=Ddesc";

            String queryForLayerSQL = "[[Category:TMN_layer]][[belongs_to_TMN::"+networkName+"]]|?TMN_layer_name";
                    
            URI uri = new URI( HadoopClusterDefaults.getEtoshaMetastoreURI() + "wiki/api.php?action=ask&format=json&query=" + encodeQuery( queryForLayerSQL ) );
            
            String uri2 = HadoopClusterDefaults.getEtoshaMetastoreURI() + "/api.php?action=ask&format=json&query=" + queryForLayerSQL ;

            httpGet.setURI( uri );
            
            System.out.println("[Request          :]\n" + uri2);
            System.out.println("[Request (encoded):]\n" + HadoopClusterDefaults.getEtoshaMetastoreURI() + "wiki/api.php?action=ask&format=json&query=" + encodeQuery( queryForLayerSQL ));
            
            if( comp != null ) comp.setQuery( uri2 );
            else System.out.println( "ERROR: comp == null !");
            
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (response.getEntity().getContent())));

            StringBuffer sb = new StringBuffer();
            String output;
            
            System.out.println(">>> Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                sb.append( output);
            }
            
            if( comp != null ) comp.setResponse( sb.toString() );
             
            
            JSONObject obj1 = new JSONObject( sb.toString() );
            
            JSONObject obj2 = obj1.getJSONObject( "query" );
               
            JSONObject obj3 = obj2.getJSONObject( "results" );
            Iterator<String> it = obj3.keys(); 
            while( it.hasNext() ){
                String key = it.next();
                JSONObject obj = obj3.getJSONObject(key);
                JSONObject obj4 = obj.getJSONObject("printouts");
                String nln = obj4.optString("TMN layer name" );
                System.out.println( nln ); 
                q.append( nln.substring( 2 , nln.length() - 2 ) + "\n"); 
            }
            
            
    
        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            try{
                if( response != null ) response.close();
                httpClient.close();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return q.toString();
    
    
    }

    public static String[] loadQueriesForNetworkLayer(String networkName, String layerName, TimedependentMultiLayerNetworkManager comp) {
        
        String[] q = new String[2];
        
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;

        try {

            httpClient = HttpClients.createDefault();
            httpGet = new HttpGet();

            String query = "[[Modification date::+]]|?Modification date|sort=Modification date|order=Ddesc";

            String queryForLayerSQL = "[[Category:TMN_layer]][[TMN_layer_name::"+layerName+"]][[belongs_to_TMN::"+networkName+"]]|?TMN_layer_nlq|?TMN_layer_elq";
                    
            URI uri = new URI( HadoopClusterDefaults.getEtoshaMetastoreURI() + "api.php?action=ask&format=json&query=" + encodeQuery( queryForLayerSQL ) );
            
            String uri2 = HadoopClusterDefaults.getEtoshaMetastoreURI() + "/api.php?action=ask&format=json&query=" + queryForLayerSQL ;

            httpGet.setURI( uri );
            
            System.out.println("[Request:]\n" + uri2);
            
            if( comp != null ) comp.setQuery( uri2 );
            

//            ArrayList<NameValuePair> nvps;
//            nvps = new ArrayList<NameValuePair>();
//            nvps.add(new BasicNameValuePair("content-type", "application/json"));
//            nvps.add(new BasicNameValuePair("x-kii-appid", "xxxxx"));
//            nvps.add(new BasicNameValuePair("x-kii-appkey", "xxxxxxxxxxxxxx"));

//             StringEntity input = new StringEntity("{\"username\": \"dummyuser\",\"password\": \"dummypassword\"}");
//             input.setContentType("application/json");
//             
//             httpPost.setEntity(input);
//
//            for (NameValuePair h : nvps)
//            {
//                httpPost.addHeader(h.getName(), h.getValue());
//            }

            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (response.getEntity().getContent())));

            StringBuffer sb = new StringBuffer();
            String output;
            
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                sb.append( output);
            }
            
            if( comp != null ) comp.setResponse( sb.toString() );
             
            
            JSONObject obj1 = new JSONObject( sb.toString() );
            
            JSONObject obj2 = obj1.getJSONObject( "query" );
               
            JSONObject obj3 = obj2.getJSONObject( "results" );
            
            JSONObject obj4 = obj3.getJSONObject( layerName );
           
            JSONObject obj5 = obj4.getJSONObject( "printouts" );
            
            String nlq = obj5.optString("TMN layer nlq" );
            String elq = obj5.optString( "TMN layer elq" );
            
            System.out.println( nlq );
            System.out.println( elq );
            
            q[0] = URLDecoder.decode( nlq.substring( 2 , nlq.length() - 2 ) );
            q[1] = URLDecoder.decode( elq.substring( 2 , elq.length() - 2 ) );
            

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            try{
                if( response != null ) response.close();
                httpClient.close();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return q;
    
    
    }
    
    private static String encodeQuery(String qu) {
        return URLEncoder.encode( qu );
    }

    private void setQuery(String uri2) {
        this.jtaQuery.setText( uri2 );
    }
    
    private void setResponse(String resp) {
        this.jtaResponse.setText( resp );
    }

    static public String loadTMNListeFromMetastore( TimedependentMultiLayerNetworkManager comp ){
        
        StringBuffer q = new StringBuffer();
        
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;

        try {

            httpClient = HttpClients.createDefault();
            httpGet = new HttpGet();

            String query = "[[Modification date::+]]|?Modification date|sort=Modification date|order=Ddesc";

            String queryForLayerSQL = "[[Category:TMN]]|?TMN_name";
                    
            URI uri = new URI( HadoopClusterDefaults.getEtoshaMetastoreURI() + "/api.php?action=ask&format=json&query=" + encodeQuery( queryForLayerSQL ) );
            
            String uri2 = HadoopClusterDefaults.getEtoshaMetastoreURI() + "/api.php?action=ask&format=json&query=" + queryForLayerSQL ;

            httpGet.setURI( uri );
            
            System.out.println("[Request:]\n" + uri2);
            
            if( comp != null ) comp.setQuery( uri2 );
            
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (response.getEntity().getContent())));

            StringBuffer sb = new StringBuffer();
            String output;
            
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                sb.append( output);
            }
            
            if( comp != null ) comp.setResponse( sb.toString() );
             
            
            JSONObject obj1 = new JSONObject( sb.toString() );
            
            JSONObject obj2 = obj1.getJSONObject( "query" );
               
            JSONObject obj3 = obj2.getJSONObject( "results" );
            Iterator<String> it = obj3.keys(); 
            while( it.hasNext() ){
                String key = it.next();
                JSONObject obj = obj3.getJSONObject(key);
                String nlnfull = obj.optString("fulltext" );
                JSONObject obj4 = obj.getJSONObject("printouts");

                String nln = obj4.optString("TMN name" );
                                
                
                System.out.println( nln ); 
                q.append( nln.substring( 2 , nln.length() - 2 ) + " : " + nlnfull + "\n"); 
            }
            
            
        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (URISyntaxException ex) {
            Exceptions.printStackTrace(ex);
        } catch (JSONException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try{
                if( response != null ) response.close();
                httpClient.close();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return q.toString();
    
    
    }


}
