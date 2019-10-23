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
package be.mdi.testing.qc.client;

import be.mdi.testing.qc.model.QcStatus;
import be.mdi.testing.qc.model.QcType;
import be.mdi.testing.qc.model.entities.*;
import be.mdi.testing.qc.model.Domains;
import be.mdi.testing.qc.model.Projects;

public class QCRestClient {

    private RestCallHandler callHandler;

    public QCRestClient(String host, String username, String password) {
        callHandler = new RestCallHandler(host, username, password);
    }

    // Overhead & General items like logging in

    public QCRestClient login() {
        callHandler.login();
        return this;
    }

    public void logout() { callHandler.logout(); }

    public boolean isLoggedIn() { return callHandler.isLoggedIn(); }

    // Generic items
    public Domains getDomains() {
        return callHandler.getRestData(Domains.class, "rest/domains");
    }

    public Domains getDomainsWithProjects() {
        return callHandler.getRestData(Domains.class, "rest/domains?include-projects-info=y");
    }

    public Projects getProjects(String domain) {
        return callHandler.getRestData(Projects.class, "rest/domains/" + domain + "/projects");
    }

    //Generic - links deduced with type, project and domain
    public Integer postEntity(QcEntity entity) {
        return
        callHandler.postRestData(entity, entity.getUrl());
    }

    public Integer putEntity(QcEntity entity) {
        return callHandler.putRestData(entity, entity.getUrl());
    }

    //Generic get
    private <T extends QcEntity> T getEntity(Class<T> retType, QcType qcType, String domain, String project, Integer entityId, Integer parentId) {

        T e = callHandler.getRestData(
                retType,
                getEntityGetUrl(qcType, domain, project, entityId, parentId)
        );

        e.setProject(project);
        e.setDomain(domain);
        return e;
    }

    private <T extends QcEntities & QcEntitiesInterface> T getEntities(Class<T> retType, QcType qcType, String domain, String project) {

        T es = callHandler.getRestData(
                retType,
                getEntityGetUrl(qcType, domain, project, null, null)
        );

        es.setProject(project);
        es.setDomain(domain);
        return es;
    }

    private String getEntityGetUrl(QcType qcType, String domain, String project, Integer entityId, Integer parentId) {
        String url = "rest/domains/" + domain + "/projects/" + project + "/";

        if(qcType.hasTypeParent() && parentId != null) {
            url += qcType.getParentType().getRestUrlType() + "/" + parentId + "/";
        }

        url += qcType.getRestUrlType();

        if(entityId != null) {
            url += "/" + entityId;
        }

        return url;
    }

    public QcDefect getDefect(String domain, String project, Integer defectId) {
        return getEntity(
                QcDefect.class,
                QcType.DEFECT,
                domain,
                project,
                defectId,
                null
                );
    }

    public QcDefects getDefects(String domain, String project) {
        return getEntities(
                QcDefects.class,
                QcType.DEFECT,
                domain,
                project
        );
    }

    public QcRun getRun(String domain, String project, int runId) {
        return getEntity(
                QcRun.class,
                QcType.RUN,
                domain,
                project,
                runId,
                null
        );
    }

    public QcRunStep getRunStep(String domain, String project, int runId, int runStepId) {
        return getEntity(
                QcRunStep.class,
                QcType.RUN_STEP,
                domain,
                project,
                runStepId,
                runId
        );
    }



    /* Create new run with result for specific test instance
    * in first instance --> assume that if multiple are returned, must take the first one.
    * Just to be robust at the start - we must manage this more elegantly.
    * Note that to create a run and set the status you need to:
    *    1. Create a run with status "Not Completed" -- Yes, this is necessary
    *    2. Update the run with the correct status.
    * Reference: https://lobsterautomation.wordpress.com/2017/01/18/hp-alm-rest-api/
    * The first approach is to update based on the combination of a specific Test instance id based on:
    *     - the test set id
    *     - the test config id
    * A later approach may include searching for a configuration in a test set linked to a specific cycle*/
    public void createRunForResult(String domain, String project, QcRun qcRun, QcStatus status) {

    }
}
