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
import be.mdi.testing.qc.model.entities.*;
import be.mdi.testing.qc.model.fields.QcDefectField;
import be.mdi.testing.qc.model.fields.QcRunField;
import be.mdi.testing.qc.model.fields.QcRunStepField;
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
    public void testMarshallTextToXmlQcDefectsObject() {
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
    public void testMarshallTextToXmlQcRunsObject() {
        String xmlString =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<Entities TotalResults=\"7\">\n" +
                        "    <Entity Type=\"run\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"test-instance\">\n" +
                        "                <Value>1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"user-01\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"execution-date\">\n" +
                        "                <Value>2009-01-27</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"state\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-lokedby\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-config\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-config-id\">\n" +
                        "                <Value>1135</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Run_1-27_14-34-57</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"path\">\n" +
                        "                <Value>5_1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"pinned-baseline\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"run-ver-stamp\">\n" +
                        "                <Value>4</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-version-number\"/>\n" +
                        "            <Field Name=\"os-build\">\n" +
                        "                <Value>Build 2600</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"testcycl-id\">\n" +
                        "                <Value>42</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle-id\">\n" +
                        "                <Value>5</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"host\">\n" +
                        "                <Value>KITE</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"assign-rcyc\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"last-modified\"/>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value>Failed</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-name\">\n" +
                        "                <Value>Windows XP</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-params-values\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-id\">\n" +
                        "                <Value>135</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"subtype-id\">\n" +
                        "                <Value>hp.qc.run.BUSINESS-PROCESS</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"draft\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-sum-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"duration\">\n" +
                        "                <Value>186</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"owner\">\n" +
                        "                <Value>cecil_alm</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"text-sync\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-detected\"/>\n" +
                        "            <Field Name=\"execution-time\">\n" +
                        "                <Value>14:38:14</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-awareness\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"comments\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-sp\">\n" +
                        "                <Value>Service Pack 2</Value>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "    <Entity Type=\"run\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"test-instance\">\n" +
                        "                <Value>1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"user-01\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"execution-date\">\n" +
                        "                <Value>2009-01-27</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"state\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-lokedby\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>2</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-config\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-config-id\">\n" +
                        "                <Value>1133</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Run_1-27_14-46-52</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"path\">\n" +
                        "                <Value>6_2</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"pinned-baseline\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"run-ver-stamp\">\n" +
                        "                <Value>4</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-version-number\"/>\n" +
                        "            <Field Name=\"os-build\">\n" +
                        "                <Value>Build 2600</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"testcycl-id\">\n" +
                        "                <Value>43</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle-id\">\n" +
                        "                <Value>6</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"host\">\n" +
                        "                <Value>KITE</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"assign-rcyc\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"last-modified\"/>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value>Passed</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-name\">\n" +
                        "                <Value>Windows XP</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-params-values\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-id\">\n" +
                        "                <Value>133</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"subtype-id\">\n" +
                        "                <Value>hp.qc.run.BUSINESS-PROCESS</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"draft\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-sum-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"duration\">\n" +
                        "                <Value>139</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"owner\">\n" +
                        "                <Value>cecil_alm</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"text-sync\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-detected\"/>\n" +
                        "            <Field Name=\"execution-time\">\n" +
                        "                <Value>14:49:25</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-awareness\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"comments\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-sp\">\n" +
                        "                <Value>Service Pack 2</Value>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "    <Entity Type=\"run\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"test-instance\">\n" +
                        "                <Value>1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"user-01\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"execution-date\">\n" +
                        "                <Value>2009-01-27</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"state\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-lokedby\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>3</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-config\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-config-id\">\n" +
                        "                <Value>1137</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Run_1-27_14-59-39</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"path\">\n" +
                        "                <Value>6_3</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"pinned-baseline\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"run-ver-stamp\">\n" +
                        "                <Value>4</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-version-number\"/>\n" +
                        "            <Field Name=\"os-build\">\n" +
                        "                <Value>Build 2600</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"testcycl-id\">\n" +
                        "                <Value>44</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle-id\">\n" +
                        "                <Value>6</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"host\">\n" +
                        "                <Value>KITE</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"assign-rcyc\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"last-modified\"/>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value>Passed</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-name\">\n" +
                        "                <Value>Windows XP</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-params-values\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-id\">\n" +
                        "                <Value>137</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"subtype-id\">\n" +
                        "                <Value>hp.qc.run.BUSINESS-PROCESS</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"draft\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-sum-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"duration\">\n" +
                        "                <Value>80</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"owner\">\n" +
                        "                <Value>cecil_alm</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"text-sync\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-detected\"/>\n" +
                        "            <Field Name=\"execution-time\">\n" +
                        "                <Value>15:01:10</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-awareness\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"comments\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-sp\">\n" +
                        "                <Value>Service Pack 2</Value>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "    <Entity Type=\"run\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"test-instance\">\n" +
                        "                <Value>1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"user-01\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"execution-date\">\n" +
                        "                <Value>2009-01-27</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"state\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-lokedby\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>4</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-config\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-config-id\">\n" +
                        "                <Value>1138</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Run_1-27_15-1-44</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"path\">\n" +
                        "                <Value>6_4</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"pinned-baseline\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"run-ver-stamp\">\n" +
                        "                <Value>4</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-version-number\"/>\n" +
                        "            <Field Name=\"os-build\">\n" +
                        "                <Value>Build 2600</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"testcycl-id\">\n" +
                        "                <Value>45</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle-id\">\n" +
                        "                <Value>6</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"host\">\n" +
                        "                <Value>KITE</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"assign-rcyc\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"last-modified\"/>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value>Passed</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-name\">\n" +
                        "                <Value>Windows XP</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-params-values\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-id\">\n" +
                        "                <Value>138</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"subtype-id\">\n" +
                        "                <Value>hp.qc.run.BUSINESS-PROCESS</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"draft\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-sum-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"duration\">\n" +
                        "                <Value>84</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"owner\">\n" +
                        "                <Value>cecil_alm</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"text-sync\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-detected\"/>\n" +
                        "            <Field Name=\"execution-time\">\n" +
                        "                <Value>15:03:18</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-awareness\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"comments\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-sp\">\n" +
                        "                <Value>Service Pack 2</Value>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "    <Entity Type=\"run\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"test-instance\">\n" +
                        "                <Value>1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"user-01\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"execution-date\">\n" +
                        "                <Value>2009-01-28</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"state\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-lokedby\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>6</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-config\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-config-id\">\n" +
                        "                <Value>1142</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Run_1-28_15-51-30</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"path\">\n" +
                        "                <Value>8_6</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"pinned-baseline\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"run-ver-stamp\">\n" +
                        "                <Value>9</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-version-number\"/>\n" +
                        "            <Field Name=\"os-build\">\n" +
                        "                <Value>Build 2600</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"testcycl-id\">\n" +
                        "                <Value>83</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle-id\">\n" +
                        "                <Value>8</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"host\">\n" +
                        "                <Value>KITE</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"assign-rcyc\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"last-modified\"/>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value>Failed</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-name\">\n" +
                        "                <Value>Windows XP</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-params-values\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-id\">\n" +
                        "                <Value>142</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"subtype-id\">\n" +
                        "                <Value>hp.qc.run.QUICKTEST_TEST</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"draft\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-sum-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"duration\">\n" +
                        "                <Value>360</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"owner\">\n" +
                        "                <Value>alice_alm</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"text-sync\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-detected\"/>\n" +
                        "            <Field Name=\"execution-time\">\n" +
                        "                <Value>17:50:31</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-awareness\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"comments\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-sp\">\n" +
                        "                <Value>Service Pack 3</Value>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "    <Entity Type=\"run\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"test-instance\">\n" +
                        "                <Value>1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"user-01\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"execution-date\">\n" +
                        "                <Value>2009-01-28</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"state\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-lokedby\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>7</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-config\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-config-id\">\n" +
                        "                <Value>1143</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Run_1-28_15-51-30</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"path\">\n" +
                        "                <Value>8_7</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"pinned-baseline\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"run-ver-stamp\">\n" +
                        "                <Value>7</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-version-number\"/>\n" +
                        "            <Field Name=\"os-build\">\n" +
                        "                <Value>Build 2600</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"testcycl-id\">\n" +
                        "                <Value>84</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle-id\">\n" +
                        "                <Value>8</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"host\">\n" +
                        "                <Value>QCCL-XP-SP3</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"assign-rcyc\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"last-modified\"/>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value>Failed</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-name\">\n" +
                        "                <Value>Windows XP</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-params-values\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-id\">\n" +
                        "                <Value>143</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"subtype-id\">\n" +
                        "                <Value>hp.qc.run.QUICKTEST_TEST</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"draft\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-sum-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"duration\">\n" +
                        "                <Value>847</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"owner\">\n" +
                        "                <Value>alex_alm</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"text-sync\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-detected\"/>\n" +
                        "            <Field Name=\"execution-time\">\n" +
                        "                <Value>17:57:15</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-awareness\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"comments\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-sp\">\n" +
                        "                <Value>Service Pack 3</Value>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "    <Entity Type=\"run\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"test-instance\">\n" +
                        "                <Value>1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"user-01\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"execution-date\">\n" +
                        "                <Value>2009-01-28</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"state\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-lokedby\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>8</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-config\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-config-id\">\n" +
                        "                <Value>1145</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Run_1-28_15-51-30</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"path\">\n" +
                        "                <Value>8_8</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"pinned-baseline\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"run-ver-stamp\">\n" +
                        "                <Value>7</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"vc-version-number\"/>\n" +
                        "            <Field Name=\"os-build\">\n" +
                        "                <Value>Build 2600</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"testcycl-id\">\n" +
                        "                <Value>85</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle-id\">\n" +
                        "                <Value>8</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"cycle\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"host\">\n" +
                        "                <Value>QCCL-XP-SP3</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"assign-rcyc\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"last-modified\"/>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value>Failed</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-name\">\n" +
                        "                <Value>Windows XP</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-params-values\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-id\">\n" +
                        "                <Value>145</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"subtype-id\">\n" +
                        "                <Value>hp.qc.run.QUICKTEST_TEST</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"draft\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"iters-sum-status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"duration\">\n" +
                        "                <Value>353</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"owner\">\n" +
                        "                <Value>alex_alm</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"text-sync\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-detected\"/>\n" +
                        "            <Field Name=\"execution-time\">\n" +
                        "                <Value>18:11:51</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-change-awareness\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"comments\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"os-sp\">\n" +
                        "                <Value>Service Pack 3</Value>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "</Entities>";

        JAXBContext jaxbContext;
        try
        {
            jaxbContext = JAXBContext.newInstance(QcRuns.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            QcRuns runList = (QcRuns) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));

            Assertions.assertEquals(7, runList.getTotalresults());
            Assertions.assertEquals("2009-01-27", runList.get(0).getField(QcRunField.EXECUTION_DATE));
            Assertions.assertEquals("QCCL-XP-SP3", runList.get(6).getField(QcRunField.HOST));

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testMarshallTextToXmlQcRunStepsObject() {
        String xmlString =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<Entities TotalResults=\"42\">\n" +
                        "    <Entity Type=\"run-step\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"component-data\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"obj-id\">\n" +
                        "                <Value>1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value>Failed</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"desstep-id\"/>\n" +
                        "            <Field Name=\"execution-date\">\n" +
                        "                <Value>2009-01-27</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"line-no\">\n" +
                        "                <Value>0</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-id\">\n" +
                        "                <Value>135</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"extended-reference\">\n" +
                        "                <Value>Test Iteration</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"rel-obj-id\"/>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>1001</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"parent-id\"/>\n" +
                        "            <Field Name=\"expected\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-condition\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"level\">\n" +
                        "                <Value>3</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"description\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Test Iteration 1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"has-linkage\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"path\">\n" +
                        "                <Value>iter1</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"actual\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"execution-time\">\n" +
                        "                <Value>14:38:14</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"step-order\">\n" +
                        "                <Value>1</Value>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "    <Entity Type=\"run-step\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"component-data\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"obj-id\">\n" +
                        "                <Value>14</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value>Failed</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"desstep-id\"/>\n" +
                        "            <Field Name=\"execution-date\">\n" +
                        "                <Value>2009-01-27</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"line-no\">\n" +
                        "                <Value>0</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-id\">\n" +
                        "                <Value>135</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"extended-reference\">\n" +
                        "                <Value>QT-KW</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"rel-obj-id\"/>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>1002</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"parent-id\"/>\n" +
                        "            <Field Name=\"expected\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-condition\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"level\">\n" +
                        "                <Value>2</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"description\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Login</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"has-linkage\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"path\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"actual\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"execution-time\">\n" +
                        "                <Value>14:35:27</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"step-order\">\n" +
                        "                <Value>2</Value>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "    <Entity Type=\"run-step\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"component-data\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"obj-id\"/>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"desstep-id\"/>\n" +
                        "            <Field Name=\"execution-date\">\n" +
                        "                <Value>2009-01-27</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"line-no\">\n" +
                        "                <Value>0</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-id\">\n" +
                        "                <Value>135</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"extended-reference\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"rel-obj-id\"/>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>1003</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"parent-id\"/>\n" +
                        "            <Field Name=\"expected\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-condition\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"level\"/>\n" +
                        "            <Field Name=\"description\">\n" +
                        "                <Value>Start Business Component Login</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Start Business Component</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"has-linkage\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"path\">\n" +
                        "                <Value>Login</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"actual\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"execution-time\">\n" +
                        "                <Value>14:35:19</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"step-order\">\n" +
                        "                <Value>3</Value>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "    <Entity Type=\"run-step\">\n" +
                        "        <Fields>\n" +
                        "            <Field Name=\"component-data\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"obj-id\"/>\n" +
                        "            <Field Name=\"status\">\n" +
                        "                <Value>Passed</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"desstep-id\"/>\n" +
                        "            <Field Name=\"execution-date\">\n" +
                        "                <Value>2009-01-27</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"attachment\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"line-no\">\n" +
                        "                <Value>0</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"test-id\">\n" +
                        "                <Value>135</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"extended-reference\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"rel-obj-id\"/>\n" +
                        "            <Field Name=\"id\">\n" +
                        "                <Value>1004</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"parent-id\"/>\n" +
                        "            <Field Name=\"expected\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"bpta-condition\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"level\"/>\n" +
                        "            <Field Name=\"description\">\n" +
                        "                <Value>Password:.SetText:\n" +
                        "\"492a7707cd8dc1acfb370b\"</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"name\">\n" +
                        "                <Value>Replay Warning</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"has-linkage\">\n" +
                        "                <Value>N</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"path\">\n" +
                        "                <Value>Login</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"actual\">\n" +
                        "                <Value/>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"execution-time\">\n" +
                        "                <Value>14:35:25</Value>\n" +
                        "            </Field>\n" +
                        "            <Field Name=\"step-order\">\n" +
                        "                <Value>4</Value>\n" +
                        "            </Field>\n" +
                        "        </Fields>\n" +
                        "    </Entity>\n" +
                        "</Entities>";

        JAXBContext jaxbContext;
        try
        {
            jaxbContext = JAXBContext.newInstance(QcRunSteps.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            QcRunSteps runStepList = (QcRunSteps) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));

            Assertions.assertEquals(42, runStepList.getTotalresults());
            Assertions.assertEquals("14:35:25", runStepList.get(3).getField(QcRunStepField.EXECUTION_TIME));
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

        Assertions.assertEquals(
                "the description",
                defects.get(0).getField(QcDefectField.DESCRIPTION));
        Assertions.assertEquals(
                "Problem observed with temp lt 4 and humidity gt 60.",
                defects.get(1).getField(QcDefectField.DESCRIPTION));
        Assertions.assertEquals(2, defects.getTotalresults());
    }

    @Test
    public void theEntitiesObjectCanBePostedToTheService() {
        mockServer
                .when(request("/qcbin/rest/domains/theDomain/projects/theProject/defects")
                .withBody(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                                        "<Entities TotalResults=\"2\">" +
                                            "<Entity Type=\"defect\">" +
                                                "<Fields>" +
                                                    "<Field Name=\"description\">" +
                                                        "<Value>the description</Value>" +
                                                    "</Field>" +
                                                "</Fields>" +
                                            "</Entity>" +
                                            "<Entity Type=\"defect\">" +
                                                "<Fields>" +
                                                    "<Field Name=\"description\">" +
                                                        "<Value>the description of the second one</Value>" +
                                                    "</Field>" +
                                                "</Fields>" +
                                              "</Entity>" +
                                        "</Entities>"))
                .respond(response()
                        .withStatusCode(201));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        QcDefects defs = new QcDefects();
        defs.setTotalresults(2);
        defs.setDomain("theDomain");
        defs.setProject("theProject");
        QcDefect def1 = new QcDefect();
        def1.setProject("theProject");
        def1.setDomain("theDomain");
        def1.setField(QcDefectField.DESCRIPTION, "the description");
        QcDefect def2 = new QcDefect();
        def2.setProject("theProject");
        def2.setDomain("theDomain");
        def2.setField(QcDefectField.DESCRIPTION, "the description of the second one");
        defs.add(def1);
        defs.add(def2);
        int status = qcc.postEntities(defs);

        Assertions.assertEquals(201, status);
    }

    @Test
    public void theEntitiesObjectQcRunsCanBePostedToTheService() {
        mockServer
                .when(request("/qcbin/rest/domains/theDomain/projects/theProject/runs")
                        .withBody(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                                        "<Entities TotalResults=\"2\">" +
                                        "<Entity Type=\"run\">" +
                                        "<Fields>" +
                                        "<Field Name=\"host\">" +
                                        "<Value>the description</Value>" +
                                        "</Field>" +
                                        "</Fields>" +
                                        "</Entity>" +
                                        "<Entity Type=\"run\">" +
                                        "<Fields>" +
                                        "<Field Name=\"host\">" +
                                        "<Value>the description of the second one</Value>" +
                                        "</Field>" +
                                        "</Fields>" +
                                        "</Entity>" +
                                        "</Entities>"))
                .respond(response()
                        .withStatusCode(201));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        QcRuns defs = new QcRuns();
        defs.setTotalresults(2);
        defs.setDomain("theDomain");
        defs.setProject("theProject");
        QcRun def1 = new QcRun();
        def1.setProject("theProject");
        def1.setDomain("theDomain");
        def1.setField(QcRunField.HOST, "the description");
        QcRun def2 = new QcRun();
        def2.setProject("theProject");
        def2.setDomain("theDomain");
        def2.setField(QcRunField.HOST, "the description of the second one");
        defs.add(def1);
        defs.add(def2);
        int status = qcc.postEntities(defs);

        Assertions.assertEquals(201, status);
    }

    @Test
    public void theEntitiesObjectQcRunStepsCanBePostedToTheService() {
        mockServer
                .when(request("/qcbin/rest/domains/theDomain/projects/theProject/runs/1/run-steps")
                        .withBody(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                                        "<Entities TotalResults=\"2\">" +
                                        "<Entity Type=\"run-step\">" +
                                        "<Fields>" +
                                        "<Field Name=\"run-id\">" +
                                        "<Value>1</Value>" +
                                        "</Field>" +
                                        "<Field Name=\"description\">" +
                                        "<Value>the description</Value>" +
                                        "</Field>" +
                                        "</Fields>" +
                                        "</Entity>" +
                                        "<Entity Type=\"run-step\">" +
                                        "<Fields>" +
                                        "<Field Name=\"run-id\">" +
                                        "<Value>1</Value>" +
                                        "</Field>" +
                                        "<Field Name=\"description\">" +
                                        "<Value>the description of the second one</Value>" +
                                        "</Field>" +
                                        "</Fields>" +
                                        "</Entity>" +
                                        "</Entities>"))
                .respond(response()
                        .withStatusCode(201));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "abc", "def");
        QcRunSteps defs = new QcRunSteps();
        defs.setTotalresults(2);
        defs.setDomain("theDomain");
        defs.setProject("theProject");
        QcRunStep def1 = new QcRunStep();
        def1.setProject("theProject");
        def1.setDomain("theDomain");
        def1.setField(QcRunStepField.DESCRIPTION, "the description");
        QcRunStep def2 = new QcRunStep();
        def2.setProject("theProject");
        def2.setDomain("theDomain");
        def2.setField(QcRunStepField.DESCRIPTION, "the description of the second one");
        defs.add(def1);
        defs.add(def2);
        defs.setRunId("1");
        int status = qcc.postEntities(defs);

        Assertions.assertEquals(201, status);
    }
}
