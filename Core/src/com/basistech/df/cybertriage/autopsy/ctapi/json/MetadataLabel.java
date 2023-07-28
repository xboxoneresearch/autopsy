/*
 * Autopsy Forensic Browser
 *
 * Copyright 2023 Basis Technology Corp.
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
package com.basistech.df.cybertriage.autopsy.ctapi.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Metadata entry.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataLabel {

    private final String key;
    private final String value;
    private final String extendedInfo;
    
    @JsonCreator
    public MetadataLabel(
            @JsonProperty("key") String key, 
            @JsonProperty("value") String value, 
            @JsonProperty("info") String extendedInfo
    ) {
        this.key = key;
        this.value = value;
        this.extendedInfo = extendedInfo;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    
    public String getExtendedInfo() {
        return extendedInfo;
    }
    
}
