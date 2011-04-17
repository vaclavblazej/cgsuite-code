/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cgsuite.ui.worksheet;

import java.awt.Color;
import java.util.logging.Logger;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.cgsuite.ui.worksheet//Worksheet//EN",
autostore = false)
public final class WorksheetTopComponent extends TopComponent
{

    private static WorksheetTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "org/cgsuite/ui/worksheet/thermograph.png";
    private static final String PREFERRED_ID = "WorksheetTopComponent";

    public WorksheetTopComponent()
    {
        initComponents();
        jScrollPane1.getViewport().setBackground(Color.white);
        setName(NbBundle.getMessage(WorksheetTopComponent.class, "CTL_WorksheetTopComponent"));
        setToolTipText(NbBundle.getMessage(WorksheetTopComponent.class, "HINT_WorksheetTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        worksheetPanel1 = new org.cgsuite.ui.worksheet.WorksheetPanel();

        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jScrollPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jScrollPane1ComponentResized1(evt);
            }
        });
        jScrollPane1.setViewportView(worksheetPanel1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane1ComponentResized(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_jScrollPane1ComponentResized
    {//GEN-HEADEREND:event_jScrollPane1ComponentResized
        worksheetPanel1.updateComponentSizes();
    }//GEN-LAST:event_jScrollPane1ComponentResized

    private void jScrollPane1ComponentResized1(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_jScrollPane1ComponentResized1
    {//GEN-HEADEREND:event_jScrollPane1ComponentResized1
        worksheetPanel1.updateComponentSizes();
    }//GEN-LAST:event_jScrollPane1ComponentResized1

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private org.cgsuite.ui.worksheet.WorksheetPanel worksheetPanel1;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized WorksheetTopComponent getDefault()
    {
        if (instance == null)
        {
            instance = new WorksheetTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the WorksheetTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized WorksheetTopComponent findInstance()
    {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null)
        {
            Logger.getLogger(WorksheetTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof WorksheetTopComponent)
        {
            return (WorksheetTopComponent) win;
        }
        Logger.getLogger(WorksheetTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType()
    {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened()
    {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed()
    {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p)
    {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p)
    {
        if (instance == null)
        {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p)
    {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID()
    {
        return PREFERRED_ID;
    }
}
