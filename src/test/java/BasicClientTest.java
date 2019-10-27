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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


public class BasicClientTest extends BaseMockTest {

    @Test
    public void theClientCanLogIn() {
        mockServer
                .when(request("/qcbin/authentication-point/authenticate")
                        .withHeader("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ="))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("LWSSO_COOKIE_KEY", "akeyvalue"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "username", "password");

        Assertions.assertEquals(true, qcc.login().isLoggedIn());
    }

    @Test
    public void theClientCanLogInAndLogOut() {
        mockServer
                .when(request("/qcbin/authentication-point/authenticate")
                        .withHeader("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ="))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("LWSSO_COOKIE_KEY", "akeyvalue1"));

        mockServer
                .when(request("/qcbin/authentication-point/logout")
                        .withCookie("LWSSO_COOKIE_KEY", "akeyvalue1"))
                .respond(response());

        mockServer
                .when(request("/qcbin/rest/is-authenticated")
                        .withCookie("LWSSO_COOKIE_KEY", "akeyvalue1"))
                .respond(
                        response()
                                .withStatusCode(403)
                );

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "username", "password");

        qcc.login();
        qcc.logout();

        assert qcc.isLoggedIn() == false;
    }

    @Test
    public void theClientCanUseAnEntityObjectToDeferAGenericTypeForAPost() {
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
    }

    @Test
    public void theClientCanUseAnEntityObjectToDeferAGenericTypeForAPut() {
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
    }
}
