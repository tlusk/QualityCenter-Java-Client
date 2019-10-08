package be.mdi.testing.qc.model.entities;

import be.mdi.testing.qc.model.fields.DefectField;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "QcEntity")
public class Defect extends QcEntity {

    public Defect() {
        setType("defect");
    }

    public String getField(DefectField field) {
        return fields.get(field.getName());
    }

    public void setField(DefectField field, String value) {
        fields.put(field.getName(), value);
    }
}