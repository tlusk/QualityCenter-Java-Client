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
import be.mdi.testing.qc.PropertyManager;
import be.mdi.testing.qc.model.entities.QcDefect;
import be.mdi.testing.qc.model.fields.QcDefectField;
import org.junit.jupiter.api.Test;
import static be.mdi.testing.qc.client.StaticQCRestClient.getQcClient;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class StaticQcRestClientTest extends BaseMockTest {

    @Test
    public void testThatTheStaticClientUsesTheInfoFromTheConfigFile() {
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

        QcDefect d = new QcDefect();
        d.setProject("theProject");
        d.setDomain(("theDomain"));
        d.setField(QcDefectField.DESCRIPTION, "the description");
        d.setField(QcDefectField.CLOSING_DATE, "2019-07-20");

        System.out.println(PropertyManager.getProperty("host"));
        System.out.println(PropertyManager.getProperty("username"));
        System.out.println(PropertyManager.getProperty("password"));
        assert getQcClient().postEntity(d) == 201;
    }
}
