import be.mdi.testing.qc.client.QCRestClient;
import be.mdi.testing.qc.model.entities.QcRunStep;
import be.mdi.testing.qc.model.fields.QcRunStepField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class QcRunStepTest extends BaseMockTest {

    @Test
    public void theModelGeneratesAValidXmlTest() throws JAXBException {

        QcRunStep qcRunStep = new QcRunStep();

        qcRunStep.setField(QcRunStepField.DESCRIPTION, "the name");
        qcRunStep.setField(QcRunStepField.EXECUTION_DATE, "2019-07-20");

        StringWriter sw = new StringWriter();

        JAXBContext contextObj = JAXBContext.newInstance(QcRunStep.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshallerObj.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        marshallerObj.marshal(qcRunStep, sw);

        Assertions.assertEquals(sw.toString(), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<Entity Type=\"run-step\">\n" +
                "    <Fields>\n" +
                "        <Field Name=\"execution-date\">\n" +
                "            <Value>2019-07-20</Value>\n" +
                "        </Field>\n" +
                "        <Field Name=\"description\">\n" +
                "            <Value>the name</Value>\n" +
                "        </Field>\n" +
                "    </Fields>\n" +
                "</Entity>\n");
    }

    @Test
    public void getTheUrlForTheDefectObjectWithoutIdDefined() {
        QcRunStep d = new QcRunStep();
        d.setDomain("theDomain");
        d.setProject("theProject");
        d.setField(QcRunStepField.PARENT_ID, "1");
        assert d.getUrl().equals("rest/domains/theDomain/projects/theProject/runs/1/run-steps");
    }

    @Test
    public void getTheUrlForTheDefectObjectWithTheIdDefined() {
        QcRunStep d = new QcRunStep();
        d.setDomain("theDomain");
        d.setProject("theProject");
        d.setField(QcRunStepField.ID, "1");
        d.setField(QcRunStepField.PARENT_ID, "1");

        Assertions.assertEquals(d.getUrl(), "rest/domains/theDomain/projects/theProject/runs/1/run-steps/1");
    }

    @Test
    public void theMethodCanGenerateAnObjectFromAValidXml() {
        mockServer
                .when(request("/qcbin/rest/domains/theDomain/projects/theProject/runs/1/run-steps/1"))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("some-cookie", "to avoid default")
                        .withBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                "<Entity Type=\"run-step\">\n" +
                                "    <Fields>\n" +
                                "        <Field Name=\"description\">\n" +
                                "            <Value>the description</Value>\n" +
                                "        </Field>\n" +
                                "        <Field Name=\"execution-date\">\n" +
                                "            <Value>2019-07-20</Value>\n" +
                                "        </Field>\n" +
                                "    </Fields>\n" +
                                "</Entity>"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        QcRunStep qcRunStep = qcc.getRunStep("theDomain", "theProject", 1, 1);

        assert qcRunStep.getField(QcRunStepField.DESCRIPTION).equals("the description");
        assert qcRunStep.getProject().equals("theProject");
        assert qcRunStep.getDomain().equals("theDomain");
    }
}
