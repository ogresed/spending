package table;

import table.component.Field;
import table.options.AddFrame;

import javax.swing.*;

public class AddRequestTable extends BaseTable {
    private String attributes = "";
    private String condition =  "";
    private String groupBy = "";
    private String orderBy = "";
    private AddFrame selectFrame;

    public AddRequestTable(String nameOfTable) {
        super(nameOfTable);
        this.nameOfTable = nameOfTable;
        selectFrame = new AddFrame(this);

    }

    public void addSelectableAttribute(Field field) {
        selectFrame.addField(field);
        selectFrame.centralize();
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
    @Override
    public JFrame getSelectedWindow() {
        return selectFrame;
    }

    public void select(String insertQuery) {
        requester.insert(insertQuery);
        System.out.println(insertQuery);
    }
}
