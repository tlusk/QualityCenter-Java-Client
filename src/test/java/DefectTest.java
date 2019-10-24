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
import be.mdi.testing.qc.model.QcType;
import be.mdi.testing.qc.model.entities.QcDefect;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

//Project
import be.mdi.testing.qc.client.QCRestClient;
import be.mdi.testing.qc.model.fields.QcDefectField;

//Support
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class DefectTest  extends BaseMockTest {

    @Test
    public void theModelGeneratesAValidXmlTest() throws JAXBException {

        QcDefect testDefect = new QcDefect();

        testDefect.setField(QcDefectField.DESCRIPTION, "the description");
        testDefect.setField(QcDefectField.CLOSING_DATE, "2019-07-20");

        StringWriter sw = new StringWriter();

        JAXBContext contextObj = JAXBContext.newInstance(QcDefect.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshallerObj.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        marshallerObj.marshal(testDefect, sw);

        Assertions.assertEquals(sw.toString(), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<Entity Type=\"defect\">\n" +
                "    <Fields>\n" +
                "        <Field Name=\"description\">\n" +
                "            <Value>the description</Value>\n" +
                "        </Field>\n" +
                "        <Field Name=\"closing-date\">\n" +
                "            <Value>2019-07-20</Value>\n" +
                "        </Field>\n" +
                "    </Fields>\n" +
                "</Entity>\n");
    }

    @Test
    public void getTheUrlForTheDefectObjectWithoutIdDefined() {
        QcDefect d = new QcDefect();
        d.setDomain("theDomain");
        d.setProject("theProject");

        assert d.getUrl().equals("rest/domains/theDomain/projects/theProject/defects");
    }

    @Test
    public void getTheUrlForTheDefectObjectWithTheIdDefined() {
        QcDefect d = new QcDefect();
        d.setDomain("theDomain");
        d.setProject("theProject");
        d.setField(QcDefectField.BUG_ID, "1");

        assert d.getUrl().equals("rest/domains/theDomain/projects/theProject/defects/1");
    }

    @Test
    public void theMethodCanGenerateAnObjectFromAValidXml() {
        mockServer
                .when(request("/qcbin/rest/domains/theDomain/projects/theProject/defects/1"))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("some-cookie", "to avoid default")
                        .withBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                "<Entity Type=\"defect\">\n" +
                                "    <Fields>\n" +
                                "        <Field Name=\"description\">\n" +
                                "            <Value>the description</Value>\n" +
                                "        </Field>\n" +
                                "        <Field Name=\"closing-date\">\n" +
                                "            <Value>2019-07-20</Value>\n" +
                                "        </Field>\n" +
                                "    </Fields>\n" +
                                "</Entity>"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        QcDefect defect = qcc.getDefect("theDomain", "theProject", 1);

        System.out.println(defect.getField(QcDefectField.DESCRIPTION));
        assert defect.getField(QcDefectField.DESCRIPTION).equals("the description");
        assert defect.getProject().equals("theProject");
        assert defect.getDomain().equals("theDomain");
        assert defect.getQcType().equals(QcType.DEFECT);
    }
}
