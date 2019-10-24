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
import be.mdi.testing.qc.model.entities.QcDefects;
import be.mdi.testing.qc.model.fields.QcDefectField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.StringReader;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class QcEntitiesTest extends BaseMockTest {

    @Test
    public void theReturnTypeIsCorrect() {
        QcDefects es = new QcDefects();
        es.add(new QcDefect());

        Assertions.assertEquals(QcDefect.class, es.get(0).getClass());
    }

    @Test
    public void theQceDefectsObjectCompilesAndruns() {
        QcDefects defList = new QcDefects();
    }

    @Test
    public void testMarshallTextToXmlObject() {
        String xmlString =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<Entities TotalResults=\"2\">\n" +
                        "    <Entity Type=\"defect\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"has-change\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"planned-closing-ver\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-reference\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"subject\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"reproducible\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"request-id\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"data\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"request-server\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"others-linkage\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"description\">\n" +
                        "                <Value>the description</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"priority\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Returned XML cannot be validated against XSD.</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"run-reference\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle-reference\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"dev-comments\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"creation-time\">\n" +
                        "                <Value>2010-03-02</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"to-mail\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"request-note\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"closing-version\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle-id\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"detection-version\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"last-modified\">\n" +
                        "                <Value>2010-03-04 14:30:00</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"closing-date\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"linkage\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"detected-in-rcyc\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"detected-in-rel\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"severity\">\n" +
                        "                <Value>2-Medium</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bug-ver-stamp\">\n" +
                        "                <Value>1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"extended-reference\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"estimated-fix-time\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"target-rel\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"project\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"detected-by\">\n" +
                        "                <Value>sa</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"step-reference\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"owner\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"target-rcyc\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"actual-fix-time\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"request-type\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "    <Entity Type=\"defect\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"has-change\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"planned-closing-ver\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-reference\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"subject\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"reproducible\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"request-id\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"data\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"request-server\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>2</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"others-linkage\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"description\">\n" +
                        "                <Value>Problem observed with temp lt 4 and humidity gt 60.</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"priority\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Car does not start in cold weather.</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"run-reference\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle-reference\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"dev-comments\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"creation-time\">\n" +
                        "                <Value>2010-03-02</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"to-mail\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"request-note\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"closing-version\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle-id\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"detection-version\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"last-modified\">\n" +
                        "                <Value>2010-03-04 14:32:56</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"closing-date\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"linkage\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"detected-in-rcyc\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"detected-in-rel\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"severity\">\n" +
                        "                <Value>3-High</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bug-ver-stamp\">\n" +
                        "                <Value>2</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"extended-reference\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"estimated-fix-time\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"target-rel\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"project\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"detected-by\">\n" +
                        "                <Value>sa</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"step-reference\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"owner\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"target-rcyc\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"actual-fix-time\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"request-type\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "</Entities>";

        JAXBContext jaxbContext;
        try
        {
            jaxbContext = JAXBContext.newInstance(QcDefects.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            QcDefects defList = (QcDefects) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));

            System.out.println(defList);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void theObjectIsReturnedOkFromTheService() {
        mockServer
                .when(request("/qcbin/rest/domains/theDomain/projects/theProject/defects"))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("some-cookie", "to avoid default")
                        .withBody(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                "<Entities TotalResults=\"2\">\n" +
                                "    <Entity Type=\"defect\">\n" +
                                "        <Fields>\n" +
                                "            <Field Name=\"has-change\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"planned-closing-ver\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"test-reference\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"subject\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"reproducible\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"request-id\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"data\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"request-server\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"id\">\n" +
                                "                <Value>1</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"others-linkage\">\n" +
                                "                <Value>N</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"description\">\n" +
                                "                <Value>the description</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"priority\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"name\">\n" +
                                "                <Value>Returned XML cannot be validated against XSD.</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"run-reference\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"cycle-reference\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"dev-comments\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"creation-time\">\n" +
                                "                <Value>2010-03-02</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"to-mail\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"request-note\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"closing-version\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"cycle-id\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"detection-version\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"last-modified\">\n" +
                                "                <Value>2010-03-04 14:30:00</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"status\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"closing-date\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"linkage\">\n" +
                                "                <Value>N</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"detected-in-rcyc\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"detected-in-rel\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"severity\">\n" +
                                "                <Value>2-Medium</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"bug-ver-stamp\">\n" +
                                "                <Value>1</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"attachment\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"extended-reference\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"estimated-fix-time\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"target-rel\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"project\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"detected-by\">\n" +
                                "                <Value>sa</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"step-reference\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"owner\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"target-rcyc\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"actual-fix-time\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"request-type\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "        </Fields>\n" +
                                "    </Entity>\n" +
                                "    <Entity Type=\"defect\">\n" +
                                "        <Fields>\n" +
                                "            <Field Name=\"has-change\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"planned-closing-ver\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"test-reference\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"subject\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"reproducible\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"request-id\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"data\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"request-server\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"id\">\n" +
                                "                <Value>2</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"others-linkage\">\n" +
                                "                <Value>N</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"description\">\n" +
                                "                <Value>Problem observed with temp lt 4 and humidity gt 60.</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"priority\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"name\">\n" +
                                "                <Value>Car does not start in cold weather.</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"run-reference\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"cycle-reference\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"dev-comments\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"creation-time\">\n" +
                                "                <Value>2010-03-02</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"to-mail\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"request-note\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"closing-version\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"cycle-id\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"detection-version\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"last-modified\">\n" +
                                "                <Value>2010-03-04 14:32:56</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"status\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"closing-date\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"linkage\">\n" +
                                "                <Value>N</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"detected-in-rcyc\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"detected-in-rel\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"severity\">\n" +
                                "                <Value>3-High</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"bug-ver-stamp\">\n" +
                                "                <Value>2</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"attachment\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"extended-reference\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"estimated-fix-time\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"target-rel\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"project\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"detected-by\">\n" +
                                "                <Value>sa</Value>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"step-reference\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"owner\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"target-rcyc\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"actual-fix-time\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "            <Field Name=\"request-type\">\n" +
                                "                <Value/>\n" +
                                "            </Field>\n" +
                                "        </Fields>\n" +
                                "    </Entity>\n" +
                                "</Entities>"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        QcDefects defects = qcc.getDefects("theDomain", "theProject");

        System.out.println(defects.get(0).getField(QcDefectField.DESCRIPTION));
        Assertions.assertEquals(
                "the description",
                defects.get(0).getField(QcDefectField.DESCRIPTION));
        Assertions.assertEquals(
                "Problem observed with temp lt 4 and humidity gt 60.",
                defects.get(1).getField(QcDefectField.DESCRIPTION));
        Assertions.assertEquals(2, defects.getTotalresults());
    }
}
