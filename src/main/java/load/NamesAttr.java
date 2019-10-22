package load;

public class NamesAttr {
    private String name;
    private String pseudonym;

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

    public NamesAttr(String name, String pseudonym) {
        this.name = name;
        this.pseudonym = pseudonym;
    }
}
