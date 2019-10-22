package table;

public class Attribute {
    private String strVal;

    public Attribute(String value) {

        strVal =  "'" + value + "'";
    }

    public Attribute(int value) {
        strVal = String.valueOf(value);
    }

    public String getAttribute() {
        return strVal;
    }
}
