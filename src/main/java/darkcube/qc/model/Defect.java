package darkcube.qc.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Entity")
public class Defect extends Entity {

    public String getField(DefectField field) {
        return fields.get(field.getName());
    }

    public void setField(DefectField field, String value) {
        fields.put(field.getName(), value);
    }
}