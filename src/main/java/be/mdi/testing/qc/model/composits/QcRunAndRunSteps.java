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
package be.mdi.testing.qc.model.composits;

import static be.mdi.testing.qc.client.StaticQCRestClient.getQcClient;
import be.mdi.testing.qc.model.entities.QcRun;
import be.mdi.testing.qc.model.entities.QcRunStep;
import be.mdi.testing.qc.model.entities.QcRunSteps;
import be.mdi.testing.qc.model.fields.QcRunField;

/**
 * This class is a composit object.
 * It is a QcRun object that contains QcRunSteps.
 * This to facilitate fluent usage of the API.
 *
 */
public class QcRunAndRunSteps implements QcCommitable {

    private QcRun qcRun;
    private QcRunSteps qcRunSteps;

    public QcRunAndRunSteps(QcRun qcRun) {
        this.qcRun = qcRun;
        this.qcRunSteps = new QcRunSteps();
    }

    public QcRunAndRunSteps addStep(QcRunStep qcRunStep) {
        qcRunSteps.add(qcRunStep);
        return this;
    }

    public QcRunAndRunSteps setRun(QcRun qcRun) {
        this.qcRun = qcRun;
        return this;
    }

    public QcRun getQcRun() {
        return qcRun;
    }

    public QcRunAndRunSteps setQcRunSteps(QcRunSteps qcRunSteps) {
        this.qcRunSteps = qcRunSteps;
        return this;
    }

    public QcRunSteps getQcRunSteps() {
        return qcRunSteps;
    }

    public QcRunAndRunSteps setRunField(QcRunField qcRunField, String value) {
        qcRun.setField(qcRunField, value);
        return this;
    }

    public void commit() {
        qcRun = getQcClient().postEntity(QcRun.class, qcRun);
        String id = qcRun.getField(QcRunField.ID);
        qcRunSteps.setRunId(id);
        qcRunSteps = getQcClient().postEntities(QcRunSteps.class, qcRunSteps);
    }

    public void update() {

    }
}
