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
import be.mdi.testing.qc.model.fields.QcRunStepField;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Entities")
public class QcRunSteps extends QcEntities implements QcEntitiesInterface<QcRunStep>{

    @XmlElement(name = "Entity")
    protected List<QcRunStep> entities;

    public QcRunSteps() {
        super(QcType.RUN_STEP);
        entities = new ArrayList<QcRunStep>();
    }

    public QcRunStep get(int index) {
        QcRunStep rs = entities.get(index);
        rs.setDomain(getDomain());
        rs.setProject(getProject());
        return rs;
    }

    public void add(QcRunStep e) {
        entities.add(e);
    }

    /**
     * Set all the RunSteps' ID with the parent run ID.
     * @param id
     */
    public void setRunId(String id) {
        setParentIdentifier(id);
        for(QcRunStep r : entities) {
            r.setField(QcRunStepField.RUN_ID, id);
        }
    }
}
