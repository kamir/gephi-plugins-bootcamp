
package org.gephi.plugin.hadoop.connector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.etosha.MultiLayerNetwork;
import org.etosha.g2h.SimpleGraphExporter;
import org.gephi.project.api.Project;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "org.etosha.HadoopImporterToolAction"
)
@ActionRegistration(
        iconBase = "org/gephi/plugin/hadoop/connector/iconNet16.png",
        displayName = "#CTL_HadoopImporterToolAction"
)

@ActionReference(path = "Menu/File", position = 100, separatorAfter = 150)
@Messages("CTL_HadoopImporterToolAction=Hadoop SQL-Import Tool for Multilayer Networks ...")
public final class HadoopSQLImporterToolAction implements ActionListener {

    public static ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        
    @Override
    public void actionPerformed(ActionEvent e) {

        javax.swing.JOptionPane.showMessageDialog(null, "> init default layer in MultiLayerNetwork ...");
        
        Project pro = pc.getCurrentProject();
        Workspace workspace = pc.newWorkspace(pro);
        
        // will call SMW to get a network descriptor ...
        MultiLayerNetwork.initNetworks();
        
        SimpleGraphExporter se = new SimpleGraphExporter();
        se.centerOnScreen();
        
    }
}
