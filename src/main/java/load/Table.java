package load;

public class Table {
    private String name;
    private String pseudonym;
    private NamesAttr[] attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public NamesAttr[] getAttributes() {
        return attributes;
    }

    public void setAttributes(NamesAttr[] attributes) {
        this.attributes = attributes;
    }

    public Table(String name, String pseudonym, NamesAttr[] attributes) {
        this.name = name;
        this.pseudonym = pseudonym;
        this.attributes = attributes;
    }
}
