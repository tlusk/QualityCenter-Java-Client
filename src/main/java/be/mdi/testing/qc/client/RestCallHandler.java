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

import be.mdi.testing.qc.model.entities.QcEntities;
import be.mdi.testing.qc.model.entities.QcEntity;
import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

class RestCallHandler {

    private final String host;
    private final String username;
    private final String password;
    private String sessionKey;

    RestCallHandler(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    <T> T getRestData(Class<T> retType, String restUrl) {
        Invocation.Builder invocationBuilder = buildRestRequest(restUrl);
        return invocationBuilder.get().readEntity(retType);
    }

    Integer postRestData(QcEntity qcEntity, String restUrl) {
        Invocation.Builder invocationBuilder = buildRestRequest(restUrl);
        Response response = invocationBuilder.post(Entity.entity(qcEntity, MediaType.APPLICATION_XML_TYPE));
        return response.getStatus();
    }

    <T> T postRestData(Class<T> retType, QcEntity qcEntity, String restUrl) {
        Invocation.Builder invocationBuilder = buildRestRequest(restUrl);
        return invocationBuilder.post(Entity.entity(qcEntity, MediaType.APPLICATION_XML_TYPE)).readEntity(retType);
    }

    Integer postRestData(QcEntities qcEntity, String restUrl) {
        Invocation.Builder invocationBuilder = buildRestRequest(restUrl);
        Response response = invocationBuilder.post(Entity.entity(qcEntity, MediaType.APPLICATION_XML_TYPE));
        return response.getStatus();
    }

    <T> T postRestData(Class<T> retType, QcEntities qcEntities, String restUrl) {
        Invocation.Builder invocationBuilder = buildRestRequest(restUrl);
        return invocationBuilder.post(Entity.entity(qcEntities, MediaType.APPLICATION_XML_TYPE)).readEntity(retType);
    }

    Integer putRestData(QcEntity qcEntity, String restUrl) {
        Invocation.Builder invocationBuilder = buildRestRequest(restUrl);
        Response response = invocationBuilder.put(Entity.entity(qcEntity, MediaType.APPLICATION_XML));
        return response.getStatus();
    }

    <T> T putRestData(Class<T> retType, QcEntity qcEntity, String restUrl) {
        Invocation.Builder invocationBuilder = buildRestRequest(restUrl);
        return invocationBuilder
                .post(
                        Entity.entity(qcEntity, MediaType.APPLICATION_XML_TYPE)
                )
                .readEntity(retType);
    }

    void login() {
        Client client = ClientBuilder.newClient();
        client.register(new HttpBasicAuthFilter(username, password));
        WebTarget webTarget = client.target(host + "/qcbin/authentication-point/authenticate");
        Response response = webTarget.request(MediaType.TEXT_PLAIN_TYPE).get();
        sessionKey = response.getHeaderString("Set-Cookie").split("=")[1].split(" ")[0];
    }

    void logout() {
        buildRestRequest("/qcbin/authentication-point/logout").get();
    }

    boolean isLoggedIn() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(host + "/qcbin/rest/is-authenticated");
        Response response = webTarget.request(MediaType.TEXT_PLAIN_TYPE)
                .header("Cookie", "LWSSO_COOKIE_KEY=" + sessionKey)
                .get();
        return response.getStatus() == 200 ? true : false;
    }

    private Invocation.Builder buildRestRequest(String restUrl) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(host + "/qcbin/" + restUrl);

        if(!isLoggedIn()) {
            login();
        }

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.WILDCARD_TYPE);
        return invocationBuilder.header("Cookie", "LWSSO_COOKIE_KEY=" + sessionKey);
    }
}
