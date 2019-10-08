//Testing
import org.junit.jupiter.api.Test;

import org.mockserver.integration.ClientAndServer;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

//Project
import be.mdi.testing.qc.client.QCRestClient;
import be.mdi.testing.qc.model.Domains;
import be.mdi.testing.qc.model.Domain;

import java.util.ArrayList;
import java.util.List;

public class DomainsTest {

    private ClientAndServer mockServer;

    @Test
    public void theMethodCanGenerateAnObjectFromAValidXmlForOneDomain() {
        mockServer = startClientAndServer(1080);
        mockServer
                .when(request("/qcbin/rest/domains"))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("some-cookie", "to avoid default")
                        .withBody("<Domains>\n" +
                                "       <Domain Name=\"Domain_NAME\"/>\n" +
                                "</Domains>"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        Domains domains = qcc.getDomains();

        Domain domain = domains.get(0);

        System.out.println(domain.getName());
        assert domain.getName().equals("Domain_NAME");

        mockServer.stop();
    }

    @Test
    public void theMethodCanGenerateAnObjectFromAValidXmlForThreeDomainsAndPrintFullList() {
        List<String> listToVerifyAgainst = new ArrayList<String>();
        listToVerifyAgainst.add("Domain_NAME_1");
        listToVerifyAgainst.add("Domain_NAME_2");
        listToVerifyAgainst.add("Domain_NAME_3");

        mockServer = startClientAndServer(1080);
        mockServer
                .when(request("/qcbin/rest/domains"))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("some-cookie", "to avoid default")
                        .withBody("<Domains>\n" +
                                "       <Domain Name=\"Domain_NAME_1\"/>\n" +
                                "       <Domain Name=\"Domain_NAME_2\"/>\n" +
                                "       <Domain Name=\"Domain_NAME_3\"/>\n" +
                                "</Domains>"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        Domains domains = qcc.getDomains();

        System.out.println(domains.getNames().toString());
        assert domains.getNames().equals(listToVerifyAgainst);

        mockServer.stop();
    }

    //TODO write test so that the domain names are put to a list when there's projects included.
    @Test
    public void theMethodCanGenerateAnObjectFromAValidXmlForThreeDomainsAndPrintFullListWithProjects() {
        List<String> listToVerifyAgainst = new ArrayList<String>();
        listToVerifyAgainst.add("Domain_NAME_1");
        listToVerifyAgainst.add("Domain_NAME_2");
        listToVerifyAgainst.add("Domain_NAME_3");

        mockServer = startClientAndServer(1080);
        mockServer
                .when(request("/qcbin/rest/domains"))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("some-cookie", "to avoid default")
                        .withBody("<Domains>\n" +
                                "       <Domain Name=\"Domain_NAME_1\"/>\n" +
                                "       <Domain Name=\"Domain_NAME_2\"/>\n" +
                                "       <Domain Name=\"Domain_NAME_3\"/>\n" +
                                "</Domains>"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        Domains domains = qcc.getDomains();

        System.out.println(domains.getNames().toString());
        assert domains.getNames().equals(listToVerifyAgainst);

        mockServer.stop();
    }

    //TODO write test that checks if domains and properties are hierarchically correctly represented.

}
