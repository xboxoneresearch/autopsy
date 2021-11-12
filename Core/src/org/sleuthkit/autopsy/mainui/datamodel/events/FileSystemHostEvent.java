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
package org.sleuthkit.autopsy.mainui.datamodel.events;

import java.util.Objects;

/**
 * An event that affects the given host.
 */
public class FileSystemHostEvent implements DAOEvent {

    private final Long hostObjectId;

    public FileSystemHostEvent(Long hostObjectId) {
        this.hostObjectId = hostObjectId;
    }

    public Long getHostObjectId() {
        return hostObjectId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.hostObjectId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileSystemHostEvent other = (FileSystemHostEvent) obj;
        if (!Objects.equals(this.hostObjectId, other.hostObjectId)) {
            return false;
        }
        return true;
    }

    @Override
    public DAOEvent.Type getType() {
        return DAOEvent.Type.RESULT;
    }
}
