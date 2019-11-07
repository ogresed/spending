package table;

import java.util.ArrayList;

public class VisibleTable extends BaseTable {

    public VisibleTable(String nameOfTable, ArrayList<String> attrs) {
        super(nameOfTable, attrs);
        query = "select * from "+ nameOfTable + ";";
    }

    @Override
    public String createStringQuery() {
        return query;
    }
}
