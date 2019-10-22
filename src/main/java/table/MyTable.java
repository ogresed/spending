package table;

public class MyTable {
    private final String name;
    private final String[] attributesNames;
    private final String selectQuery;

    public MyTable(String nameOfTable, String[] attributes) {
        this.name = nameOfTable;
        this.attributesNames = attributes;
        selectQuery = "select * from " + name + ";";
    }

    public MyTable(String nameOfTable, String[] attributes, String condition) {
        this.name = nameOfTable;
        this.attributesNames = attributes;
        this.selectQuery = "select * from " + name + condition + ";";
    }

    public String getInsertQuery(Attribute[] values) {
        if(values.length == 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder().append(" values (").append(values[0].getAttribute());
        for(int i = 1; i < values.length; i++) {
            String val = values[i].getAttribute();
            builder.append(",").append(val);
        }
        builder.append(");");
        return ("insert into " + name + builder.toString());
    }

    public String getSelectQuery() {
        return selectQuery;
    }

    String getName() {
        return name;
    }

    String[] getAttributesNames () {
        return attributesNames;
    }
}
