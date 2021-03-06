/*
 * Copyright (c) 2009 The Jackson Laboratory
 * 
 * This software was developed by Gary Churchill's Lab at The Jackson
 * Laboratory (see http://research.jax.org/faculty/churchill).
 *
 * This is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jax.qtl.fit.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.help.CSH;
import javax.help.HelpSet;
import javax.help.SecondaryWindow;

import org.jax.qtl.QTL;
import org.jax.qtl.cross.QtlBasket;
import org.jax.qtl.fit.FitQtlCommand;
import org.jax.r.gui.RCommandEditorAndPreviewPanel;

/**
 * Dialog for issuing fitqtl commands
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public class FitQtlDialog extends javax.swing.JDialog
{
    /**
     * every {@link java.io.Serializable} is supposed to have one of these
     */
    private static final long serialVersionUID = 2682916325615402866L;
    
    private static final String HELP_ID_STRING = "Fit_QTL";
    
    private final RCommandEditorAndPreviewPanel editorAndPreviewPanel;
    
    private final FitQtlPanel fitQtlPanel;

    private final ConcurrentLinkedQueue<ActionListener> actionListenerList;
    
    /**
     * Constructor
     * @param parent
     *          the parent frame
     * @param selectedQtlBasket
     *          the QTL basket to start with (can be null)
     */
    public FitQtlDialog(
            java.awt.Frame parent,
            QtlBasket selectedQtlBasket)
    {
        super(parent, "Fit QTL", true);
        
        this.fitQtlPanel = new FitQtlPanel(this, selectedQtlBasket);
        this.editorAndPreviewPanel = new RCommandEditorAndPreviewPanel(
                this.fitQtlPanel);
        this.actionListenerList = new ConcurrentLinkedQueue<ActionListener>();
        this.initComponents();
        
        // initialize the help stuff
        HelpSet hs = QTL.getInstance().getMenubar().getHelpSet();
        CSH.setHelpIDString(
                this.helpButton,
                HELP_ID_STRING);
        this.helpButton.addActionListener(
                new CSH.DisplayHelpFromSource(
                        hs,
                        SecondaryWindow.class.getName(),
                        null));
    }
    
    /**
     * Add an action listener. The listener will get called if a FIT
     * command is successfully accepted by the user.
     * @param actionListener
     *          the action listener to add
     */
    public void addActionListener(ActionListener actionListener)
    {
        this.actionListenerList.add(actionListener);
    }
    
    /**
     * Remove an action listener.
     * @param actionListener
     *          the action listener to remove
     */
    public void removeActionListener(ActionListener actionListener)
    {
        this.actionListenerList.remove(actionListener);
    }
    
    /**
     * Tell all of the action listeners that we've finished.
     */
    private void fireEditingCompleted()
    {
        Iterator<ActionListener> actionIter = this.actionListenerList.iterator();
        ActionEvent actionEvent = new ActionEvent(
                this,
                ActionEvent.ACTION_LAST + 1,
                "commandEditingCompleted");
        while(actionIter.hasNext())
        {
            actionIter.next().actionPerformed(actionEvent);
        }
    }
    
    /**
     * Getter for the fit qtl command. This function should only be called
     * in response to having received an {@link ActionEvent} from this
     * class in order to make sure that what we return is valid.
     * @see #addActionListener(ActionListener)
     * @return
     *          the current command
     */
    public FitQtlCommand getFitQtlCommand()
    {
        return this.fitQtlPanel.getFitQtlCommand();
    }
    
    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("all")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContentPanel = this.editorAndPreviewPanel;
        actionButtonPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        actionButtonPanel.add(okButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        actionButtonPanel.add(cancelButton);

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/action/help-16x16.png"))); // NOI18N
        helpButton.setText("Help ...");
        actionButtonPanel.add(helpButton);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(actionButtonPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
            .add(mainContentPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(mainContentPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(actionButtonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if(this.fitQtlPanel.validateData())
        {
            this.fireEditingCompleted();
            this.dispose();
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionButtonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JPanel mainContentPanel;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
    
}
