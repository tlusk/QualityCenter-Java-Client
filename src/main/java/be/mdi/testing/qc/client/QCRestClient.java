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

import be.mdi.testing.qc.model.QcType;
import be.mdi.testing.qc.model.entities.*;
import be.mdi.testing.qc.model.Domains;
import be.mdi.testing.qc.model.Projects;

/**
 * This class implemetns the {@Link RestCallHandler.Class} class.
 * The methods in this class aim to be readable.
 * The implementation may get verbose and repetitive.
 * A degree of verbosity and repetitiveness is tolerated to be able to offer a readable interface.
 */
public class QCRestClient {

    private RestCallHandler callHandler;

    /**
     * QCRestClient core instance.
     * To instantiate - Call the constructor.
     * After creating the object, you must log in by calling the login() method on this object.
     * To log out, the logout() method may be used. If not logging out, then the session may be active until expiration.
     * This is to be avoided.
     * @param host - String, host name - only the server, not the /qcbin/rest part!
     * @param username - Username in string format
     * @param password - password in string format (TODO not safe - check implementation)
     */
    public QCRestClient(String host, String username, String password) {
        callHandler = new RestCallHandler(host, username, password);
    }

    // Overhead & General items like logging in

    /**
     * Log in with the host, username and password provided in the constructor on creation of the object.
     * @return QCRestClient (fluent api)
     */
    public QCRestClient login() {
        callHandler.login();
        return this;
    }

    /**
     * Log out of the session.
     * Advised to do so to keep the sessions clean.
     */
    public void logout() { callHandler.logout(); }

    /**
     * Check if the client is logged in.
     * @return boolean (logged in: true; not logged in: false - true when is-authenticated returns OK.
     */
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

    public <T> T postEntity(Class<T> retType, QcEntity entity) {
        return callHandler.postRestData(retType, entity, entity.getUrl());
    }

    public Integer postEntities(QcEntities entity) {
        return
                callHandler.postRestData(entity, entity.getUrl());
    }

    public <T> T postEntities(Class<T> retType, QcEntities qcEntities) {
        return callHandler.postRestData(retType, qcEntities, qcEntities.getUrl());
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

    public QcRuns getRuns(String domain, String project) {
        return getEntities(
                QcRuns.class,
                QcType.RUN,
                domain,
                project
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
}
