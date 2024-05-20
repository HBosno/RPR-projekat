package ba.unsa.etf.rpr.domain;

public class TimeTable {
    private String start;
    private String end;

    public TimeTable() {}

    public TimeTable(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

}
