package be.mdi.testing.qc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Domains")
public class Domains {

    @XmlElement(name="Domain")
    List<Domain> domains;

    public Domain get(int i) {
        return domains.get(i);
    }

    public List<String> getNames() {
        List<String> domainList = new ArrayList<String>();

        for(Domain domain: domains) {
            domainList.add(domain.getName());
        }

        return domainList;

    }

}
