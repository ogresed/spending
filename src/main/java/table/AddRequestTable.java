package table;

public class AddRequestTable extends BaseTable {
    private String attributes = "";
    private String condition =  "";
    private String groupBy = "";
    private String orderBy = "";

    public AddRequestTable(String nameOfTable) {
        super(nameOfTable);
        this.nameOfTable = nameOfTable;
    }

    @Override
    public String createStringQuery() {
        query =
        "select " + attributes + " from " + nameOfTable + " " +
                condition + " " +
                groupBy + " " +
                orderBy +
                ";";
        return query;
    }

    public AddRequestTable from(String attributes) {
        this.attributes = attributes;
        return this;
    }

    public AddRequestTable where(String condition) {
        this.condition = "where ( " + condition + ") ";
        return this;
    }

    public AddRequestTable groupBy(String groupBy) {
        this.groupBy = "group by " + groupBy;
        return this;
    }

    public AddRequestTable orderByASC(String attrName) {
        this.orderBy = "order by " + attrName + " asc";
        return this;
    }

    public AddRequestTable orderByDESC(String attrName) {
        this.orderBy = "order by " + attrName + " desc";
        return this;
    }

    public AddRequestTable fromGetAllAttributes() {
        attributes = "*";
        return this;
    }
}
