package be.mdi.testing.qc.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Project")
public class Project {

    private String name;

    @XmlAttribute(name = "Name")
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
