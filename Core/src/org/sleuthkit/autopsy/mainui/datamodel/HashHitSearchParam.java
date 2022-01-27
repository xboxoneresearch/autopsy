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
package org.sleuthkit.autopsy.mainui.datamodel;

import org.sleuthkit.datamodel.BlackboardArtifact;

/**
 * Key for keyword hits in order to retrieve data from DAO.
 */
public class HashHitSearchParam extends AnalysisResultConfigSearchParam {

    private static final String TYPE_ID = "HASH_HIT";

    /**
     * @return The type id for this search parameter.
     */
    public static String getTypeId() {
        return TYPE_ID;
    }

    public HashHitSearchParam(Long dataSourceId, String setName) {
        super(BlackboardArtifact.Type.TSK_HASHSET_HIT, dataSourceId, setName);
    }
}
