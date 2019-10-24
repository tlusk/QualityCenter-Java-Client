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
//Testing
import org.junit.jupiter.api.Test;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

//Project
import be.mdi.testing.qc.client.QCRestClient;
import be.mdi.testing.qc.model.Projects;
import be.mdi.testing.qc.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectsTest extends BaseMockTest {

    @Test
    public void theMethodCanGenerateAnObjectFromAValidXmlForOneProject() {
        mockServer
                .when(request("/qcbin/rest/domains/theDomain/projects"))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("some-cookie", "to avoid default")
                        .withBody("<Projects>\n" +
                                "       <Project Name=\"PROJECT_NAME\"/>\n" +
                                "</Projects>"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        Projects projects = qcc.getProjects("theDomain");

        Project project = projects.get(0);

        System.out.println(project.getName());
        assert project.getName().equals("PROJECT_NAME");
    }

    @Test
    public void theMethodCanGenerateAnObjectFromAValidXmlForThreeProjectsAndPrintFullList() {
        List<String> listToVerifyAgainst = new ArrayList<String>();
        listToVerifyAgainst.add("PROJECT_NAME_1");
        listToVerifyAgainst.add("PROJECT_NAME_2");
        listToVerifyAgainst.add("PROJECT_NAME_3");

        mockServer
                .when(request("/qcbin/rest/domains/theDomain/projects"))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("some-cookie", "to avoid default")
                        .withBody("<Projects>\n" +
                                "       <Project Name=\"PROJECT_NAME_1\"/>\n" +
                                "       <Project Name=\"PROJECT_NAME_2\"/>\n" +
                                "       <Project Name=\"PROJECT_NAME_3\"/>\n" +
                                "</Projects>"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        Projects projects = qcc.getProjects("theDomain");

        System.out.println(projects.getNames().toString());
        assert projects.getNames().equals(listToVerifyAgainst);
    }
}
