package be.mdi.testing.qc.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Domain")
public class Domain {

    private String name;
    private Projects projects;

    @XmlAttribute(name = "Name")
    public String getName() {
        return name;
    }
    @XmlElement(name = "Projects")
    public Projects getProjects() { return projects; }

    private void setName(String name) {
        this.name = name;
    }

    private void setProjects(Projects projects) {
        this.projects = projects;
    }
}
