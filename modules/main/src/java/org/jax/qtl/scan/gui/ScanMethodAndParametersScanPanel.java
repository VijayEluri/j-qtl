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

package org.jax.qtl.scan.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.jax.qtl.cross.Cross;
import org.jax.qtl.scan.PhenotypeDistribution;
import org.jax.qtl.scan.ScanCommandBuilder;
import org.jax.qtl.scan.ScanMethod;
import org.jax.qtl.scan.ScanType;
import org.jax.qtl.ui.CalcGenoprobDialog;
import org.jax.qtl.ui.ImputationDialog;
import org.jax.r.gui.RCommandEditorListener;
import org.jax.util.TextWrapper;

/**
 * This panel is responsible for editing the "scan method" and
 * "scan parameters" portion of a {@link ScanCommandBuilder}
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public class ScanMethodAndParametersScanPanel extends ScanCommandEditorPanel
{
    /**
     * every {@link java.io.Serializable} is supposed to have one of these
     */
    private static final long serialVersionUID = -6382665791697788497L;

    private final ScanCommandBuilder scanCommand;
    
    private final ConvergenceParametersPanel convergenceParametersPanel;
    
    private final OtherScanParametersPanel otherScanParametersPanel;

    private final JDialog parentDialog;
    
    /**
     * Constructor
     * @param parentDialog
     *          the parent used for internal dialogs
     * @param scanCommand
     *          the scan command that this panel will edit
     */
    public ScanMethodAndParametersScanPanel(
            JDialog parentDialog,
            ScanCommandBuilder scanCommand)
    {
        this.scanCommand = scanCommand;
        this.parentDialog = parentDialog;
        this.convergenceParametersPanel = new ConvergenceParametersPanel(scanCommand);
        this.otherScanParametersPanel = new OtherScanParametersPanel(scanCommand);
        
        this.initComponents();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addRCommandEditorListener(RCommandEditorListener editorListener)
    {
        super.addRCommandEditorListener(editorListener);
        this.convergenceParametersPanel.addRCommandEditorListener(editorListener);
        this.otherScanParametersPanel.addRCommandEditorListener(editorListener);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeRCommandEditorListener(
            RCommandEditorListener editorListener)
    {
        super.removeRCommandEditorListener(editorListener);
        this.convergenceParametersPanel.removeRCommandEditorListener(editorListener);
        this.otherScanParametersPanel.removeRCommandEditorListener(editorListener);
    }
    
    /**
     * Updates the scan method
     * @param scanMethod
     *          the new scan method to use
     */
    private void setSelectedScanMethod(ScanMethod scanMethod)
    {
        if(this.scanCommand.getScanMethod() != scanMethod)
        {
            this.scanCommand.setScanMethod(scanMethod);
            this.refreshConvergencePanel();
            this.fireCommandModified();
        }
    }
    
    /**
     * Update the convergence panel based on the scan method.
     */
    private void refreshConvergencePanel()
    {
        ScanMethod scanMethod = this.scanCommand.getScanMethod();
        boolean enableConvergenceParameters =
            scanMethod == ScanMethod.EM_ALGORITHM ||
            scanMethod == ScanMethod.EXTENDED_HALEY_KNOTT_METHOD;
        this.convergenceParametersPanel.setEnabled(enableConvergenceParameters);
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

        scanMethodLabel = new javax.swing.JLabel();
        scanMethodComboBox = new javax.swing.JComboBox();
        convergencePanelDownCast = this.convergenceParametersPanel;
        otherScanParametersDownCast = this.otherScanParametersPanel;

        setMinimumSize(new java.awt.Dimension(500, 300));

        scanMethodLabel.setText("Scan Method:");

        scanMethodComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanMethodComboBoxActionPerformed(evt);
            }
        });

        convergencePanelDownCast.setBorder(javax.swing.BorderFactory.createTitledBorder("Convergence Parameters (For EM & Extended Haley Knott)"));

        otherScanParametersDownCast.setBorder(javax.swing.BorderFactory.createTitledBorder("Other Scan Parameters"));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, convergencePanelDownCast, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, otherScanParametersDownCast, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(scanMethodLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(scanMethodComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(scanMethodLabel)
                    .add(scanMethodComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(convergencePanelDownCast, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(otherScanParametersDownCast, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scanMethodComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanMethodComboBoxActionPerformed
        Object selectedItem = this.scanMethodComboBox.getSelectedItem();
        if(selectedItem instanceof ScanMethod)
        {
            this.setSelectedScanMethod((ScanMethod)selectedItem);
        }
        else
        {
            this.setSelectedScanMethod(null);
        }
    }//GEN-LAST:event_scanMethodComboBoxActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel convergencePanelDownCast;
    private javax.swing.JPanel otherScanParametersDownCast;
    private javax.swing.JComboBox scanMethodComboBox;
    private javax.swing.JLabel scanMethodLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * Validate the data contained in this panel. The user is alerted if
     * there is something wrong with the data format
     * @return
     *          true iff the validation succeeds
     */
    public boolean validateData()
    {
        return this.convergenceParametersPanel.validateData() &&
               this.validateGenotypeData();
    }
    
    /**
     * Make sure that "sim.geno" or "calc.genoprob" was called if they
     * were supposed to be called.
     * @return
     *          true on success and false on failure
     */
    private boolean validateGenotypeData()
    {
        ScanMethod scanMethod = this.scanCommand.getScanMethod();
        if(scanMethod == ScanMethod.EM_ALGORITHM ||
           scanMethod == ScanMethod.HALEY_KNOTT_REGRESSION ||
           scanMethod == ScanMethod.EXTENDED_HALEY_KNOTT_METHOD)
        {
            return this.validateGenotypeProbabilitiesCalculated(
                    scanMethod);
        }
        else if(scanMethod == ScanMethod.MULTIPLE_IMPUTATION)
        {
            return this.validateGenotypeProbabilitiesSimulated(
                    scanMethod);
        }
        else
        {
            return true;
        }
    }

    /**
     * Validate that genotype probabilities were calculated "calc.genoprob"
     * @param scanMethod
     *          the scan method we're requiring this for
     * @return
     *          true iff valid
     */
    private boolean validateGenotypeProbabilitiesSimulated(
            ScanMethod scanMethod)
    {
        final Cross cross = this.scanCommand.getCross();
        if(cross.getSimulateGenotypeWasUsed())
        {
            return true;
        }
        else
        {
            final String message =
                "The scan method that you have selected \"" +
                scanMethod.toString() + "\" requires that " +
                "genotype probabilities be simulated before " +
                "running the scan command. Would you like to do this now?";
            boolean confirmed = this.getConfirmation(
                    "Genotype Probabilities Not Yet Simulated",
                    message);
            if(confirmed)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        ScanMethodAndParametersScanPanel.this.simulateGenotypeProbabilities(
                                cross);
                    }
                });
            }
            
            return false;
        }
    }
    
    /**
     * Open up the dialog that allows the user to do a sim.geno
     * on the given cross
     * @param cross
     *          the cross
     */
    private void simulateGenotypeProbabilities(Cross cross)
    {
        ImputationDialog simulateGenotypeProbabilitiesDialog =
            new ImputationDialog(
                    this.parentDialog,
                    new Cross[] {cross},
                    cross);
        simulateGenotypeProbabilitiesDialog.setVisible(true);
    }
    
    /**
     * Ask the user if we can do something
     * @param title
     *          title for the dialog
     * @param message
     *          message in the dialog
     * @return
     *          the users response
     */
    private boolean getConfirmation(String title, String message)
    {
        int response = JOptionPane.showConfirmDialog(
                this.parentDialog,
                TextWrapper.wrapText(
                        message,
                        TextWrapper.DEFAULT_DIALOG_COLUMN_COUNT),
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return response == JOptionPane.OK_OPTION;
    }

    /**
     * Validate that genotype probabilities were simulated "sim.geno"
     * @param scanMethod
     *          the scan method we're requiring this for
     * @return
     *          true iff valid
     */
    private boolean validateGenotypeProbabilitiesCalculated(
            ScanMethod scanMethod)
    {
        final Cross cross = this.scanCommand.getCross();
        if(cross.getCalculateConditionalProbabilitiesWasUsed())
        {
            return true;
        }
        else
        {
            final String message =
                "The scan method that you have selected \"" +
                scanMethod.toString() + "\" requires that " +
                "genotype probabilities be calculated before " +
                "running the scan command. Would you like to do this now?";
            boolean confirmed = this.getConfirmation(
                    "Genotype Probabilities Not Yet Calculated",
                    message);
            if(confirmed)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        ScanMethodAndParametersScanPanel.this.calculateGenotypeProbabilities(
                                cross);
                    }
                });
            }
            
            return false;
        }
    }

    /**
     * Calculate genotype probabilities for the given cross
     * @param cross
     *          the cross
     */
    protected void calculateGenotypeProbabilities(Cross cross)
    {
        CalcGenoprobDialog calculateGenotypeProbabilitiesDialog =
            new CalcGenoprobDialog(
                    this.parentDialog,
                    new Cross[] {cross},
                    cross);
        calculateGenotypeProbabilitiesDialog.setVisible(true);
    }

    /**
     * This function tells this panel that a component other than itself
     * has edited the {@link ScanCommandBuilder} and that we need to refresh
     * our graphics to reflect those changes.
     */
    public void refreshGui()
    {
        // the "other" parameters are only valid for scan two
        this.otherScanParametersPanel.setVisible(
                this.scanCommand.getScanType() == ScanType.SCANTWO);
        
        PhenotypeDistribution phenotypeDistribution =
            this.scanCommand.getPhenotypeDistribution();
        
        // figure out what scan methods we should allow
        ScanMethod currentScanMethod =
            this.scanCommand.getScanMethod();
        List<ScanMethod> availableMethodsList =
            new ArrayList<ScanMethod>();
        if(phenotypeDistribution == PhenotypeDistribution.BINARY)
        {
            // can only be "em" or "mr"
            availableMethodsList.add(ScanMethod.EM_ALGORITHM);
            availableMethodsList.add(ScanMethod.MARKER_REGRESSION);
        }
        else if(phenotypeDistribution != PhenotypeDistribution.OTHER)
        {
            availableMethodsList.addAll(
                    Arrays.asList(ScanMethod.values()));
        }
        
        // remove any methods that aren't supported
        List<ScanMethod> supportedMethodsList = Arrays.asList(
            this.scanCommand.getScanType().getSupportedScanMethods());
        availableMethodsList.retainAll(supportedMethodsList);
        
        DefaultComboBoxModel scanMethodModel =
            (DefaultComboBoxModel)this.scanMethodComboBox.getModel();
        scanMethodModel.removeAllElements();
        if(availableMethodsList.size() == 0)
        {
            scanMethodModel.addElement("Non Parametric");
            this.scanMethodComboBox.setEnabled(false);
        }
        else
        {
            boolean currentScanMethodIsAvailable = false;
            for(ScanMethod currAvailableMethod: availableMethodsList)
            {
                scanMethodModel.addElement(currAvailableMethod);
                if(currAvailableMethod == currentScanMethod)
                {
                    currentScanMethodIsAvailable = true;
                }
            }
            
            if(currentScanMethodIsAvailable)
            {
                scanMethodModel.setSelectedItem(currentScanMethod);
            }
            this.scanMethodComboBox.setEnabled(true);
        }
        
        this.refreshConvergencePanel();
        
        this.fireCommandModified();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ScanCommandBuilder getScanCommand()
    {
        return this.scanCommand;
    }
    
}
