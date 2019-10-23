/*
 * QC REST API client
 *
 * Copyright (C) 2019  matthias.dirickx@outlook.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package be.mdi.testing.qc.model;

import be.mdi.testing.qc.model.entities.QcDefect;
import be.mdi.testing.qc.model.entities.QcRun;
import be.mdi.testing.qc.model.entities.QcRunStep;
import be.mdi.testing.qc.model.fields.QcRunStepField;

public enum QcType {

    BUG("defect", "BUG", "defects", null, null, QcDefect.class),
    DEFECT("defect", "BUG", "defects", null, null, QcDefect.class),
    RUN("run", "RUN", "runs", null, null, QcRun.class),
    RUN_STEP("run-step", "STEP", "run-steps", QcRunStepField.PARENT_ID.getName(), RUN, QcRunStep.class);

    private String smallCapType;
    private String dbType;
    private String restUrlType;
    private String parentIdentifier;
    private QcType parentType;
    private Class retType;

    QcType(String smallCapType, String dbType, String restUrlType, String parentIdentifier, QcType parentType, Class retType) {
        this.smallCapType = smallCapType;
        this.dbType = dbType;
        this.restUrlType = restUrlType;
        this.parentIdentifier = parentIdentifier;
        this.parentType = parentType;
        this.retType = retType;
    }

    public String getSmallCapType() { return smallCapType; }
    public String getDbType() { return dbType; }
    public String getRestUrlType() { return restUrlType; }
    public boolean hasTypeParent() { return (parentType != null); }
    public String getParentIdentifier() { return parentIdentifier; }
    public QcType getParentType() { return parentType; }
    public Class getRetType() { return retType; }
}
