package table.component.datePanel;

public class Date {
    private long year;
    private int day;
    private int month;

    Date(long year, int day, int month) {
        this.year = year;
        this.day = day;
        this.month = month;
    }
    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String toString() {
        return String.valueOf(year) + '-' + (month+1) +
                '-' + day;
    }
}
