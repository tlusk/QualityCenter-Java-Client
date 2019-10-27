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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import be.mdi.testing.qc.PropertyManager;

public class PropertyReaderTest {

    @Test
    public void testTheReaderWithASimpleRefProperty() {
        PropertyManager.setProperty("prop", "This property contains text that is ${not replaced}.");
        PropertyManager.setProperty("not replaced", "now modified");
        PropertyManager.resolveProperties();

        String propValue = PropertyManager.getProperty("prop");

        Assertions.assertEquals("This property contains text that is now modified.", propValue);
    }

    @Test
    public void testTheReaderWithANestedRefProperty() {
        PropertyManager.setProperty("prop", "This property contains text that is ${${not replaced}.}");
        PropertyManager.setProperty("not replaced", "now modified");
        PropertyManager.setProperty("now modified.", "awesome.");
        PropertyManager.resolveProperties();

        String propValue = PropertyManager.getProperty("prop");

        Assertions.assertEquals("This property contains text that is awesome.", propValue);
    }

    @Test
    public void testTheReaderWithTwoPropertiesOfWhichOneIsNested() {
        PropertyManager.setProperty("prop", "This property ${contains} text that is ${${not replaced}.}");
        PropertyManager.setProperty("contains", "is showing");
        PropertyManager.setProperty("not replaced", "now modified");
        PropertyManager.setProperty("now modified.", "awesome.");
        PropertyManager.resolveProperties();

        String propValue = PropertyManager.getProperty("prop");

        Assertions.assertEquals("This property is showing text that is awesome.", propValue);
    }

    @Test
    public void testTheReaderWithTripleNestedProps() {
        PropertyManager.setProperty("prop", "This property ${contains} text that is ${${${not} replaced}.}");
        PropertyManager.setProperty("supposed to be replaced", "now modified");
        PropertyManager.setProperty("now modified.", "awesome.");
        PropertyManager.setProperty("not", "supposed to be");
        PropertyManager.setProperty("contains", "is showing");
        PropertyManager.resolveProperties();

        String propValue = PropertyManager.getProperty("prop");

        Assertions.assertEquals("This property is showing text that is awesome.", propValue);
    }

    @Test
    public void testBaseProps() {
        Assertions.assertEquals("http://127.0.0.1:1080", PropertyManager.getProperty("qcclient.host"));
    }
}
