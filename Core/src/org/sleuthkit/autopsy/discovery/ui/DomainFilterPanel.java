/*
 * Autopsy
 *
 * Copyright 2020 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.discovery.ui;

import org.sleuthkit.autopsy.centralrepository.datamodel.CentralRepository;
import org.sleuthkit.autopsy.discovery.search.AttributeSearchData;
import org.sleuthkit.autopsy.discovery.search.FileSearchData;
import org.sleuthkit.autopsy.discovery.search.SearchData;

/**
 * Filter panel for searching domain attributes with Discovery.
 */
public class DomainFilterPanel extends AbstractFiltersPanel {

    private static final long serialVersionUID = 1L;
    private static final AttributeSearchData.AttributeType ARTIFACT_TYPE = AttributeSearchData.AttributeType.DOMAIN;

    /**
     * Creates new form DomainFilterPanel.
     */
    public DomainFilterPanel() {
        super();
        initComponents();
        addFilter(new DataSourceFilterPanel(), false, null, 0);
        addFilter(new ArtifactTypeFilterPanel(), false, null, 1);
        addFilter(new DateFilterPanel(), false, null, 1);
        int[] pastOccurrencesIndices = null;
        if (CentralRepository.isEnabled()) {
            pastOccurrencesIndices = new int[]{2, 3, 4};
        }
        addFilter(new PastOccurrencesFilterPanel(), true, pastOccurrencesIndices, 0);
        addPanelsToScrollPane(domainFiltersSplitPane);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JScrollPane domainFiltersScrollPane = new javax.swing.JScrollPane();
        javax.swing.JPanel domainFiltersPanel = new javax.swing.JPanel();
        domainFiltersSplitPane = new javax.swing.JSplitPane();

        setPreferredSize(new java.awt.Dimension(225, 70));
        setLayout(new java.awt.BorderLayout());

        domainFiltersSplitPane.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DomainFilterPanel.class, "DomainFilterPanel.domainFiltersSplitPane.border.title"))); // NOI18N
        domainFiltersSplitPane.setResizeWeight(0.5);
        domainFiltersSplitPane.setToolTipText(org.openide.util.NbBundle.getMessage(DomainFilterPanel.class, "DomainFilterPanel.domainFiltersSplitPane.toolTipText")); // NOI18N

        javax.swing.GroupLayout domainFiltersPanelLayout = new javax.swing.GroupLayout(domainFiltersPanel);
        domainFiltersPanel.setLayout(domainFiltersPanelLayout);
        domainFiltersPanelLayout.setHorizontalGroup(
            domainFiltersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(domainFiltersPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(domainFiltersSplitPane)
                .addGap(8, 8, 8))
        );
        domainFiltersPanelLayout.setVerticalGroup(
            domainFiltersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(domainFiltersPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(domainFiltersSplitPane)
                .addGap(8, 8, 8))
        );

        domainFiltersScrollPane.setViewportView(domainFiltersPanel);

        add(domainFiltersScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    FileSearchData.FileType getFileType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    SearchData.ResultType getResultType() {
        return SearchData.ResultType.ATTRIBUTE;
    }

    @Override
    AttributeSearchData.AttributeType getArtifactType() {
        return ARTIFACT_TYPE;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane domainFiltersSplitPane;
    // End of variables declaration//GEN-END:variables
}
