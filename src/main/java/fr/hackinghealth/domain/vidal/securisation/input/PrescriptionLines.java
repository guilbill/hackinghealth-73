package fr.hackinghealth.domain.vidal.securisation.input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import fr.hackinghealth.domain.vidal.entry.AbstractEntry;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class PrescriptionLines extends AbstractEntry{
    @XmlElement(name = "prescription-line")
    private List<PrescriptionLine> line;
    
    /**
     * @return the line
     */
    public List<PrescriptionLine> getLine() {
        if (line == null) {
            line = new ArrayList<>();
        }
        return line;
    }
    
    /**
     * @param line the line to set
     */
    public void setLine(final List<PrescriptionLine> line) {
        this.line = line;
    }


}
