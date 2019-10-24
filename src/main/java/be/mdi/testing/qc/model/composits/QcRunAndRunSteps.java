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

import be.mdi.testing.qc.client.QCRestClient;
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

    //TODO get the username and password out of the method and into another vehicle like properties
    public void commit(String host, String username, String password) {
        QCRestClient qcc = new QCRestClient(host, username, password);
        qcc.login();
        qcRun = qcc.postEntity(QcRun.class, qcRun);
        String id = qcRun.getField(QcRunField.ID);
        qcRunSteps.setRunId(id);
        qcRunSteps = qcc.postEntities(QcRunSteps.class, qcRunSteps);
        qcc.logout();
    }

    public void update() {

    }
}
