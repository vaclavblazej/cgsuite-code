/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cgsuite.ui.tips;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingUtilities;
import org.openide.modules.ModuleInstall;
import org.openide.windows.WindowManager;

public class Installer extends ModuleInstall {

    @Override
    public void restored()
    {
        SwingUtilities.invokeLater(showTips);
    }

    Runnable showTips = new Runnable(){
        public void run(){
            WindowManager.getDefault().getMainWindow().addWindowListener(new WindowAdapter() {
                public void windowOpened(WindowEvent e) {
                    TipTCAction.showDialog(false);
                }
            });
        }
    };
}
