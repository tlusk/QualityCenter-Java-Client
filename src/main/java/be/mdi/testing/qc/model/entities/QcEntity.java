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
package be.mdi.testing.qc.model.entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QcEntity {

    protected String type;
    protected Map<String,String> fields;

    public QcEntity() {
        fields = new HashMap<String, String>();
    }

    @XmlAttribute(name = "Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "Fields")
    @XmlJavaTypeAdapter(MapFieldsAdapter.class)
    protected Map<String,String> getFields() {
        return fields;
    }

    protected void setFields(Map<String,String> fields) {
        this.fields = fields;
    }
}

class MapFieldsAdapter extends XmlAdapter<ValueList, Map<String, String>> {
    @Override
    public Map<String, String> unmarshal(ValueList value) throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        for (ValuePair entry : value.entries) {
            map.put(entry.name, entry.value);
        }

        return map;
    }

    @Override
    public ValueList marshal(Map<String, String> value) throws Exception {
        ValueList map = new ValueList();
        map.entries = new ArrayList<ValuePair>();
        for (String name : value.keySet()) {
            ValuePair entry = new ValuePair();
            entry.name = name;
            entry.value = value.get(name);
            map.entries.add(entry);
        }
        return map;
    }
}

class ValueList {
    @XmlElement(name="Field")
    public List<ValuePair> entries;
}

class ValuePair {
    @XmlAttribute(name="Name")
    public String name;

    @XmlElement(name="Value")
    public String value;
}