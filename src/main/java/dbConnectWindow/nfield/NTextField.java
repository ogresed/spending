package dbConnectWindow.nfield;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class NTextField extends Fenced implements NField {
    private JTextField textField;
    private JComboBox<String> variants;

    public NTextField(String title) {
        super(title);
        setLayout(new GridLayout(2,1));
        textField = new JTextField(14);
        variants = new JComboBox<>();
        add(textField);
        add(variants);

        variants.addActionListener(e -> textField.setText((String)variants.getSelectedItem()));
    }
    @Override
    public String getText() {
        return textField.getText();
    }

    public void setText(String str) {
        textField.setText(str);
    }

    public void addItems(HashSet<String> set) {
        for(String str : set) {
            variants.addItem(str);
        }
    }
}
