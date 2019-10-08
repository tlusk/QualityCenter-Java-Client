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