/*
 * Autopsy Forensic Browser
 *
 * Copyright 2021 Basis Technology Corp.
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
package org.sleuthkit.autopsy.contentviewers.osaccount;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.Locale.US;
import java.util.Optional;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.openide.util.NbBundle.Messages;
import org.sleuthkit.autopsy.contentviewers.osaccount.SectionData.RowData;
import org.sleuthkit.datamodel.OsAccount;
import org.sleuthkit.datamodel.OsAccountRealm;

/**
 * Panel for displaying the properties of an OsAccount.
 */
public class OsAccountDataPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int KEY_COLUMN = 0;
    private static final int VALUE_COLUMN = 1;
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM dd yyyy", US);

    // Panel constructor.
    OsAccountDataPanel() {
        initialize();
    }

    /**
     * Initializes the panel layout.
     */
    private void initialize() {
        this.setLayout(new GridBagLayout());
    }

    /**
     * Set the OsAccount to display in this panel.
     *
     * @param account OsAccount to display, if null is passed the panel will
     *                appear blank.
     */
    void setOsAccount(OsAccount account) {
        removeAll();

        if (account != null) {
            List<SectionData> data = new ArrayList<>();
            data.add(buildBasicProperties(account));

            OsAccountRealm realm = account.getRealm();
            if (realm != null) {
                data.add(buildRealmProperties(realm));
            }

            addDataComponents(data);
        }
        revalidate();
    }

    /**
     * Give all of the data to display, create the swing components and add to
     * the panel.
     *
     * @param panelData Data to be displayed.
     */
    private void addDataComponents(List<SectionData> panelData) {
        int rowCnt = 0;
        for (SectionData section : panelData) {
            addTitle(section.getTitle(), rowCnt++);

            for (RowData<String, String> rowData : section) {
                String key = rowData.getKey();
                String value = rowData.getValue();

                addPropertyName(key, rowCnt);
                addPropertyValue(value, rowCnt++);
            }
        }

        // Generate the constraints for a Vertical Glue to fill the space, if
        // any at the bottom of the panel.
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = rowCnt;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        add(Box.createVerticalGlue(), constraints);
    }

    @Messages({
        "OsAccountDataPanel_basic_title=Basic Properties",
        "OsAccountDataPanel_basic_login=Login",
        "OsAccountDataPanel_basic_fullname=Full Name",
        "OsAccountDataPanel_basic_address=Address",
        "OsAccountDataPanel_basic_admin=Administrator",
        "OsAccountDataPanel_basic_type=Type",
        "OsAccountDataPanel_basic_creationDate=Creation Date",
    })

    /**
     * Returns the data for the Basic Properties section of the panel.
     *
     * @param account Selected account
     *
     * @return The basic properties data for the given account.
     */
    private SectionData buildBasicProperties(OsAccount account) {
        SectionData data = new SectionData(Bundle.OsAccountDataPanel_basic_title());

        Optional<String> optional = account.getLoginName();
        data.addData(Bundle.OsAccountDataPanel_basic_login(),
                optional.isPresent() ? optional.get() : "");

        optional = account.getFullName();
        data.addData(Bundle.OsAccountDataPanel_basic_fullname(),
                optional.isPresent() ? optional.get() : "");

        data.addData(Bundle.OsAccountDataPanel_basic_address(),
                account.getName() == null || account.getName().isEmpty() ? "" : account.getName());

        data.addData(Bundle.OsAccountDataPanel_basic_admin(), getIsAdminValue(account.isAdmin()));

        data.addData(Bundle.OsAccountDataPanel_basic_type(), account.getOsAccountType().getName());
        
        Optional<Long> crTime = account.getCreationTime();
        if(crTime.isPresent()) {
            data.addData(Bundle.OsAccountDataPanel_basic_creationDate(), DATE_FORMAT.format(new Date(crTime.get() * 1000)));
        }
        else {
            data.addData(Bundle.OsAccountDataPanel_basic_creationDate(), "");
        }

        return data;
    }

    @Messages({
        "OsAccountDataPanel_realm_title=Realm Properties",
        "OsAccountDataPanel_realm_name=Name",
        "OsAccountDataPanel_realm_address=Address",
        "OsAccountDataPanel_realm_confidence=Confidence",
        "OsAccountDataPanel_realm_unknown=Unknown",
        "OsAccountDataPanel_realm_scope=Scope",})

    /**
     * Builds the Realm Properties.
     *
     * @param realm A valid OsAccountRealm.
     *
     * @return Data to be displayed for the given realm.
     */
    private SectionData buildRealmProperties(OsAccountRealm realm) {
        SectionData data = new SectionData(Bundle.OsAccountDataPanel_realm_title());

        Optional<String> optional = realm.getRealmName();
        data.addData(Bundle.OsAccountDataPanel_realm_name(),
                optional.isPresent() ? optional.get() : Bundle.OsAccountDataPanel_realm_unknown());

        optional = realm.getRealmAddr();
        data.addData(Bundle.OsAccountDataPanel_realm_address(),
                optional.isPresent() ? optional.get() : "");
        
        data.addData(Bundle.OsAccountDataPanel_realm_scope(),
            realm.getScope().getName());
        
        data.addData(Bundle.OsAccountDataPanel_realm_confidence(),
                realm.getScopeConfidence().getName());

        return data;
    }

    /**
     * Add a section title to the panel with the given title and location.
     *
     * @param title Section title.
     * @param row   Row in the layout the title will appear.
     */
    private void addTitle(String title, int row) {
        JLabel label = new JLabel(title);
        // Make the title bold.
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        add(label, getTitleContraints(row));
    }

    /**
     * Add the property name at the given row in the layout.
     *
     * @param key The property name.
     * @param row The row in the layout.
     */
    private void addPropertyName(String key, int row) {
        JLabel label = new JLabel(key);
        add(label, getPropertyNameContraints(row));
    }

    /**
     * Add the property value at the given row in the layout.
     *
     * @param value The value to display.
     * @param row   The row in the layout.
     */
    private void addPropertyValue(String value, int row) {
        JLabel label = new JLabel(value);
        add(label, getPropertyValueContraints(row));
    }

    /**
     * Generate the constraints for a title at the given row.
     *
     * @param row The row to generate the title constraints for.
     *
     * @return Constraints for a title row.
     */
    private GridBagConstraints getTitleContraints(int row) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.gridwidth = 2; // The title goes across the other columns
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.insets = new Insets(5, 5, 5, 9);

        return constraints;
    }

    /**
     * Generate the constraints for a property name at the given row.
     *
     * @param row
     *
     * @return Constraints for the property name label.
     */
    private GridBagConstraints getPropertyNameContraints(int row) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = KEY_COLUMN;
        constraints.gridy = row;
        constraints.gridwidth = 1; // The title goes across the other columns
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 13, 5, 5);

        return constraints;
    }

    /**
     * Generate the constraints for a property value at the given row.
     *
     * @param row Row in the layout.
     *
     * @return The constraints for the property label.
     */
    private GridBagConstraints getPropertyValueContraints(int row) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = VALUE_COLUMN;
        constraints.gridy = row;
        constraints.gridwidth = 1; // The title goes across the other columns
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.insets = new Insets(0, 5, 5, 5);

        return constraints;
    }

    @Messages({
        "OsAccountDataPanel_basic_yes=Yes",
        "OsAccountDataPanel_basic_no=No",
        "OsAccountDataPanel_basic_unknown=Unknown",})
    /**
     * Return a string representation of the isAdmin status for OsAccounts.
     *
     * @param isAdmin Admin state for the OsAccount
     *
     * @return String representation of the admin state.
     */
    private String getIsAdminValue(Optional<Boolean> isAdmin) {
        if (!isAdmin.isPresent()) {
            return Bundle.OsAccountDataPanel_basic_unknown();
        } else {
            if (isAdmin.get()) {
                return Bundle.OsAccountDataPanel_basic_yes();
            } else {
                return Bundle.OsAccountDataPanel_basic_no();
            }
        }
    }

}
