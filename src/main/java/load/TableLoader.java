package load;
import table.unresolved.MyTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TableLoader {
    private MyTable[] tables;
    private File file;
    private String[] secondTablesNames[];
    private HashMap<String, String[]> attrNames;

    public TableLoader(String fileName) {
        file =  new File(fileName);
        load(file);
    }

    private Table[] load(File file) {
        try {
            Scanner scanner = new Scanner(file);
            int numberOfTables = Integer.valueOf(scanner.nextLine());
            int counter = 0;
            Table[] tables = new Table[numberOfTables];
            while (scanner.hasNext() && counter < numberOfTables) {
                String[] allInfo = scanner.nextLine().split("\\+");
                String nameOfTable = allInfo[0];
                String[] twoNames = nameOfTable.split(" ");
                String namesOfAttributes = allInfo[1];
                String[] attrs = namesOfAttributes.split(",");
                NamesAttr[] na = new NamesAttr[attrs.length];
                int count = 0;
                for(String s : attrs) {
                    String[] pair = s.split("/");
                    na[count] = new NamesAttr(pair[0], pair[1]);
                    count++;
                }
                Table table = new Table(twoNames[0], twoNames[1], na);
                tables[counter] = table;
                counter++;
            }
            return tables;
        } catch (FileNotFoundException e) {
            System.err.println("File not exist");
        } catch (NoSuchElementException | NumberFormatException e) {
            System.err.println("Wrong format of file");
        }
        return null;
    }
}
