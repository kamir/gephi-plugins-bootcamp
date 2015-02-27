/**
 *
 * The main purpose of the Gephi-Hadoop-Connector project is the integration of
 * a loader function for Graphs, stored in Hadoop clusters into Gephi.
 *
 */
package org.gephi.plugin.hadoop.connector;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.gephi.plugins.etosha.impala.ImpalaImportConnector;

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "org.etosha.hdgs.gephi.plugin.MainAction")
@ActionRegistration(
        iconBase = "org/gephi/plugin/hadoop/connector/treeSun16.png",
        displayName = "#CTL_MainAction")
@ActionReference(path = "Menu/File", position = 0, separatorBefore = -50, separatorAfter = 50)
@Messages("CTL_MainAction=Load Network from Hadoop ... ")
public final class LoadFromHadoopMainAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        int opt = 1; // default is IMPALA !!!

        String[] options = new String[]{"Hive", "Impala"};

//        int opt = JOptionPane.showOptionDialog(null, "Select import mode ...", "Where is your network stored?", 
//        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
//        null, options, options[0]);
        switch (opt) {

            case 4: {
                break;
            }

            case 3: {
                break;
            }

            case 2: {
                break;
            }

            case 1: {

                String message = "Selected mode : (" + opt + ") " + options[opt] + "\n"
                        + "Your Impala server is: " + HadoopClusterDefaults.IMPALA_DEAMON_IP;

                javax.swing.JOptionPane.showMessageDialog(null, message);

                ImpalaImportConnector.main(null);

                break;
            }

            case 0: {
                // HiveImportConnector.main( null );
                break;
            }

        }

    }
}
