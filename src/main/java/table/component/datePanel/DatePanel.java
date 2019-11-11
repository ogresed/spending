package table.component.datePanel;

import table.component.Field;

import javax.swing.*;
import java.time.MonthDay;
import java.time.YearMonth;
import java.util.Vector;

import static java.time.Year.isLeap;
import static table.component.ComponentType.DATE;

public class DatePanel extends Field {

    private static final String[] month = {
             "Январь", "Февраль", "Март", "Апрель",
            "Май", "Июнь", "Июль", "Август",
            "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
    };
    private static final int[] daysToAdding = {
            3, 0, 3, 2, 3, 2, 3, 3, 2, 3, 2, 3
    };
    private static final int BEGIN_YEAR = 2019;
    private static final Long[] years = {
            2019L, 2020L, 2021L, 2022L, 2023L, 2024L
    };
    private static final int[] extraDays = {29, 30, 31};

    private Vector<Integer> days = new Vector<>();
    private JComboBox<Long> yearsBox;
    private JComboBox<String> monthsBox;
    private JComboBox<Integer> daysBox;

    public enum Option {
        All, MonthAndYear
    }

    public enum Mode {
        SET_CURRENT_DATE
    }

    public DatePanel(String name, Option option, Mode mode) {
        super(name);
        TYPE = DATE;
        yearsMonthInitialization(mode);

        if(option == Option.All) {
            daysInitialization(mode);
        }
    }

    private void daysInitialization(Mode mode) {
        monthsBox.addActionListener(e -> {
            for(int i = days.size() - 28; i > 0; i--) {
                days.remove(28 + i - 1);
            }
            int toAdding = daysToAdding[monthsBox.getSelectedIndex()];
            if(toAdding == 0) {
                if(isLeap(yearsBox.getItemAt(yearsBox.getSelectedIndex()))) {
                    toAdding = 1;
                }
            }
            for(int i = 0; i < toAdding; i++) {
                days.add(extraDays[i]);
            }
        });
        daysBox = new JComboBox<>(days);
        add(daysBox);
        int day;
        for(day = 0; day < 31; day++) {
            days.add(day+1);
        }
        if(mode == Mode.SET_CURRENT_DATE) {
            daysBox.setSelectedIndex(MonthDay.now().getDayOfMonth() - 1);
        }
    }

    private void yearsMonthInitialization (Mode mode) {
        yearsBox = new JComboBox<>(years);
        monthsBox = new JComboBox<>(month);
        add(yearsBox);
        add(monthsBox);
        if(mode == Mode.SET_CURRENT_DATE) {
            var now = YearMonth.now();
            yearsBox.setSelectedIndex(now.getYear() - BEGIN_YEAR);
            monthsBox.setSelectedIndex(now.getMonthValue() - 1);
        }
    }
    @Override
    public String getValue() {
        return "'"+getDate().toString()+"'";
    }

    private Date getDate() {
        return new Date (
                years[yearsBox.getSelectedIndex()],
                monthsBox.getSelectedIndex(),
                days.get(daysBox.getSelectedIndex())
                );
    }

    public long getYear() {
        return years[yearsBox.getSelectedIndex()];
    }

    public int getMonth() {
        return monthsBox.getSelectedIndex() + 1;
    }
}
