//Testing
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockserver.integration.ClientAndServer;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

//Project
import be.mdi.testing.qc.client.QCRestClient;
import be.mdi.testing.qc.model.entities.Defect;
import be.mdi.testing.qc.model.fields.DefectField;

//Support
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class DefectTest {

    private ClientAndServer mockServer;

    @Test
    public void theModelGeneratesAValidXmlTest() throws JAXBException {

        Defect testDefect = new Defect();

        testDefect.setField(DefectField.DESCRIPTION, "the description");
        testDefect.setField(DefectField.CLOSING_DATE, "2019-07-20");

        StringWriter sw = new StringWriter();

        JAXBContext contextObj = JAXBContext.newInstance(Defect.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshallerObj.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        marshallerObj.marshal(testDefect, sw);

        Assertions.assertEquals(sw.toString(), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<QcEntity Type=\"defect\">\n" +
                "    <Fields>\n" +
                "        <Field Name=\"description\">\n" +
                "            <Value>the description</Value>\n" +
                "        </Field>\n" +
                "        <Field Name=\"closing-date\">\n" +
                "            <Value>2019-07-20</Value>\n" +
                "        </Field>\n" +
                "    </Fields>\n" +
                "</QcEntity>\n");

        System.out.println(sw.toString());
    }

    @Test
    public void theMethodCanGenerateAnObjectFromAValidXml() {
        mockServer = startClientAndServer(1080);
        mockServer
                .when(request("/qcbin/rest/domains/theDomain/projects/theProject/defects/1"))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("some-cookie", "to avoid default")
                        .withBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                "<QcEntity Type=\"defect\">\n" +
                                "    <Fields>\n" +
                                "        <Field Name=\"description\">\n" +
                                "            <Value>the description</Value>\n" +
                                "        </Field>\n" +
                                "        <Field Name=\"closing-date\">\n" +
                                "            <Value>2019-07-20</Value>\n" +
                                "        </Field>\n" +
                                "    </Fields>\n" +
                                "</QcEntity>"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        Defect defect = qcc.getDefect("theDomain", "theProject", 1);

        System.out.println(defect.getField(DefectField.DESCRIPTION));
        assert defect.getField(DefectField.DESCRIPTION).equals("the description");

        mockServer.stop();
    }
}
