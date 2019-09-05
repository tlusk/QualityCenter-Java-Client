//Testing
import org.junit.jupiter.api.Test;

import org.mockserver.integration.ClientAndServer;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

//Project
import be.mdi.testing.qc.client.QCRestClient;
import be.mdi.testing.qc.model.Projects;
import be.mdi.testing.qc.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectsTest {

    private ClientAndServer mockServer;

    @Test
    public void theMethodCanGenerateAnObjectFromAValidXmlForOneProject() {
        mockServer = startClientAndServer(1080);
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

        mockServer.stop();
    }

    @Test
    public void theMethodCanGenerateAnObjectFromAValidXmlForThreeProjectsAndPrintFullList() {
        List<String> listToVerifyAgainst = new ArrayList<String>();
        listToVerifyAgainst.add("PROJECT_NAME_1");
        listToVerifyAgainst.add("PROJECT_NAME_2");
        listToVerifyAgainst.add("PROJECT_NAME_3");

        mockServer = startClientAndServer(1080);
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

        mockServer.stop();
    }
}
