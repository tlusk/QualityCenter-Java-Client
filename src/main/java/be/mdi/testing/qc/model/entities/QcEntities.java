package be.mdi.testing.qc.model.entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Entities")
public class QcEntities {

    @XmlAttribute(name = "TotalResults")
    public int totalresults;

    @XmlElement(name = "Entity")
    public List<QcEntity> entities;

    public QcEntity get(int index) {
        return entities.get(index);
    }

    public int getTotalresults() {
        return totalresults;
    }

    private void setTotalresults(int totalResults) {
        this.totalresults = totalResults;
    }
}
