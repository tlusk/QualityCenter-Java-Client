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
package be.mdi.testing.qc.model.entities;

import be.mdi.testing.qc.model.QcType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;


public class QcEntities {

    @XmlAttribute(name = "TotalResults")
    private int totalresults;

    @XmlTransient
    private String project;
    @XmlTransient
    private String domain;
    @XmlTransient
    private QcType qcType;

    public QcEntities(QcType qcType) {
        this.qcType = qcType;
    }

    public int getTotalresults() {
        return totalresults;
    }
    public String getDomain() { return domain; }
    public String getProject() { return project; }

    public void setDomain(String domain) { this.domain = domain; }
    public void setProject(String project) {this.project = project; }

    public String getUrl() {
        String url =  "rest/domains/" + domain + "/projects/" + project + "/";

        url += qcType.getRestUrlType();

        return url;
    }
}
