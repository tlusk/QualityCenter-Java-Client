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
import be.mdi.testing.qc.model.QcType;
import be.mdi.testing.qc.model.entities.QcRun;
import be.mdi.testing.qc.model.fields.QcRunField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class QcRunTest extends BaseMockTest {

    @Test
    public void theModelGeneratesAValidXmlTest() throws JAXBException {

        QcRun qcRun = new QcRun();

        qcRun.setField(QcRunField.RUN_NAME, "the name");
        qcRun.setField(QcRunField.EXECUTION_DATE, "2019-07-20");

        StringWriter sw = new StringWriter();

        JAXBContext contextObj = JAXBContext.newInstance(QcRun.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshallerObj.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        marshallerObj.marshal(qcRun, sw);

        Assertions.assertEquals(sw.toString(), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<Entity Type=\"run\">\n" +
                "    <Fields>\n" +
                "        <Field Name=\"execution-date\">\n" +
                "            <Value>2019-07-20</Value>\n" +
                "        </Field>\n" +
                "        <Field Name=\"run-name\">\n" +
                "            <Value>the name</Value>\n" +
                "        </Field>\n" +
                "    </Fields>\n" +
                "</Entity>\n");
    }

    @Test
    public void getTheUrlForTheDefectObjectWithoutIdDefined() {
        QcRun d = new QcRun();
        d.setDomain("theDomain");
        d.setProject("theProject");
        assert d.getUrl().equals("rest/domains/theDomain/projects/theProject/runs");
    }

    @Test
    public void getTheUrlForTheDefectObjectWithTheIdDefined() {
        QcRun d = new QcRun();
        d.setDomain("theDomain");
        d.setProject("theProject");
        d.setField(QcRunField.ID, "1");

        assert d.getUrl().equals("rest/domains/theDomain/projects/theProject/runs/1");
    }

    @Test
    public void theMethodCanGenerateAnObjectFromAValidXml() {
        mockServer
                .when(request("/qcbin/rest/domains/theDomain/projects/theProject/runs/1"))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("some-cookie", "to avoid default")
                        .withBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                "<Entity Type=\"run\">\n" +
                                "    <Fields>\n" +
                                "        <Field Name=\"run-name\">\n" +
                                "            <Value>the description</Value>\n" +
                                "        </Field>\n" +
                                "        <Field Name=\"execution-date\">\n" +
                                "            <Value>2019-07-20</Value>\n" +
                                "        </Field>\n" +
                                "    </Fields>\n" +
                                "</Entity>"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        QcRun qcRun = qcc.getRun("theDomain", "theProject", 1);

        System.out.println(qcRun.getField(QcRunField.RUN_NAME));
        assert qcRun.getField(QcRunField.RUN_NAME).equals("the description");
        assert qcRun.getProject().equals("theProject");
        assert qcRun.getDomain().equals("theDomain");
        Assertions.assertEquals(QcType.RUN, qcRun.getQcType());
    }
}
