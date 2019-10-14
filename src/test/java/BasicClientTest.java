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
import be.mdi.testing.qc.client.QCRestClient;
import be.mdi.testing.qc.model.entities.QcDefect;
import be.mdi.testing.qc.model.fields.QcDefectField;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class BasicClientTest {

    private ClientAndServer mockServer;

    @Test
    public void theClientCanLogIn() {
        mockServer = startClientAndServer(1080);
        mockServer
                .when(request("/qcbin/authentication-point/authenticate")
                        .withHeader("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ="))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("LWSSO_COOKIE_KEY", "akeyvalue"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "username", "password");

        qcc.login();

        assert qcc.isLoggedIn() == true;


        mockServer.stop();
    }

    @Test
    public void theClientCanLogInAndLogOut() {
        mockServer = startClientAndServer(1080);
        mockServer
                .when(request("/qcbin/authentication-point/authenticate")
                        .withHeader("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ="))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("LWSSO_COOKIE_KEY", "akeyvalue"));

        mockServer
                .when(request("/qcbin/authentication-point/logout")
                        .withCookie("LWSSO_COOKIE_KEY", "akeyvalue"))
                .respond(response());

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "username", "password");

        qcc.login();
        qcc.logout();

        assert qcc.isLoggedIn() == false;


        mockServer.stop();
    }

    @Test
    public void theClientCanUseAnEntityObjectToDeferAGenericTypeForAPost() {
        mockServer = startClientAndServer(1080);
        mockServer
                .when(request("/qcbin/rest/domains/theDomain/projects/theProject/defects")
                .withBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                          "<Entity Type=\"defect\">" +
                              "<Fields>" +
                                  "<Field Name=\"description\">" +
                                      "<Value>the description</Value>" +
                                  "</Field>" +
                                  "<Field Name=\"closing-date\">" +
                                      "<Value>2019-07-20</Value>" +
                                  "</Field>" +
                              "</Fields>" +
                          "</Entity>"))

                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withStatusCode(201)
                        .withCookie("some-cookie", "to avoid default"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");

        QcDefect d = new QcDefect();
        d.setProject("theProject");
        d.setDomain(("theDomain"));
        d.setField(QcDefectField.DESCRIPTION, "the description");
        d.setField(QcDefectField.CLOSING_DATE, "2019-07-20");

        assert qcc.postEntity(d) == 201;

        mockServer.stop();
    }

    @Test
    public void theClientCanUseAnEntityObjectToDeferAGenericTypeForAPut() {
        mockServer = startClientAndServer(1080);
        mockServer
                .when(request("/qcbin/rest/domains/theDomain/projects/theProject/defects/1")
                        .withBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                                "<Entity Type=\"defect\">" +
                                "<Fields>" +
                                "<Field Name=\"description\">" +
                                "<Value>the description</Value>" +
                                "</Field>" +
                                "<Field Name=\"id\">" +
                                "<Value>1</Value>" +
                                "</Field>" +
                                "<Field Name=\"closing-date\">" +
                                "<Value>2019-07-20</Value>" +
                                "</Field>" +
                                "</Fields>" +
                                "</Entity>"))

                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withStatusCode(201)
                        .withCookie("some-cookie", "to avoid default"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");

        QcDefect d = new QcDefect();
        d.setProject("theProject");
        d.setDomain(("theDomain"));
        d.setField(QcDefectField.DESCRIPTION, "the description");
        d.setField(QcDefectField.CLOSING_DATE, "2019-07-20");
        d.setField(QcDefectField.BUG_ID, "1");

        assert qcc.putEntity(d) == 201;

        mockServer.stop();
    }
}
