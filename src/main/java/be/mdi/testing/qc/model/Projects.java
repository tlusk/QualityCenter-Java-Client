package be.mdi.testing.qc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Projects")
public class Projects {

    @XmlElement(name="Project")
    List<Project> projects;

    public Project get(int i) {
        return projects.get(i);
    }

    public List<String> getNames() {
        List<String> projectList = new ArrayList<String>();

        for(Project project: projects) {
            projectList.add(project.getName());
        }

        return projectList;

    }

}
