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

public enum QcType {

    BUG("defect", "BUG", "defects", null),
    DEFECT("defect", "BUG", "defects", null),
    RUN("run", "RUN", "runs", null),
    RUN_STEP("run-step", "STEP", "run-steps", QcType.RUN);

    private String smallCapType;
    private String dbType;
    private String restUrlType;
    private QcType typeParent;

    QcType(String smallCapType, String dbType, String restUrlType, QcType typeParent) {
        this.smallCapType = smallCapType;
        this.dbType = dbType;
        this.restUrlType = restUrlType;
        this.typeParent = typeParent;
    }

    public String getSmallCapType() { return smallCapType; }
    public String getDbType() { return dbType; }
    public String getRestUrlType() { return restUrlType; }
    public boolean hasTypeParent() { if(typeParent == null){return false;}else{return true;} }
    public QcType getTypeParent() { return typeParent; }

    public String getEntityUrl() {
        String url = "";
        if(this.hasTypeParent()) {
            url += "/";
            url += this.getTypeParent().getRestUrlType();
        }
        url += "/";
        url += this.getRestUrlType();

        return url;
    }

}
