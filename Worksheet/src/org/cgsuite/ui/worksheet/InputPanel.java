/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InputPanel.java
 *
 * Created on Apr 24, 2011, 12:12:01 PM
 */

package org.cgsuite.ui.worksheet;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author asiegel
 */
public class InputPanel extends JPanel
{
    /** Creates new form InputPanel */
    public InputPanel()
    {
        initComponents();
        inputPane.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                if ("font".equals(evt.getPropertyName()))
                {
                    inputLabel.setFont((Font) evt.getNewValue());
                }
            }
        });
    }

    public InputPane getInputPane()
    {
        return inputPane;
    }
    
    public JLabel getPrompt()
    {
        return inputLabel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputLabel = new javax.swing.JLabel();
        inputPane = new org.cgsuite.ui.worksheet.InputPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

        inputLabel.setBackground(new java.awt.Color(255, 255, 255));
        inputLabel.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        inputLabel.setText(org.openide.util.NbBundle.getMessage(InputPanel.class, "InputPanel.inputLabel.text")); // NOI18N
        inputLabel.setAlignmentY(0.0F);
        inputLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 8));
        add(inputLabel);

        inputPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));
        inputPane.setAlignmentY(0.0F);
        inputPane.setMinimumSize(new java.awt.Dimension(0, 0));
        add(inputPane);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel inputLabel;
    private org.cgsuite.ui.worksheet.InputPane inputPane;
    // End of variables declaration//GEN-END:variables

}
