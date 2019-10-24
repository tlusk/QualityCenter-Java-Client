package be.mdi.testing.qc.model.entities;

import be.mdi.testing.qc.model.QcType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Entities")
public class QcDefects extends QcEntities implements QcEntitiesInterface<QcDefect> {

    @XmlElement(name = "Entity")
    protected List<QcDefect> entities;

    public QcDefects()  {
        super(QcType.DEFECT);
        entities = new ArrayList<QcDefect>();
    }

    public QcDefect get(int index) {
        QcDefect d = entities.get(index);
        d.setDomain(getDomain());
        d.setProject(getProject());
        return d;
    }

    public void add(QcDefect d) {
        entities.add(d);
    }
}
