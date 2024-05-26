package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.TimeTable;

import java.util.ArrayList;
import java.util.List;

public class TimeTableGenerator {
    public List<TimeTable> generate(int start, int end, int frequency) {
        List<TimeTable> list = new ArrayList<>();
        for (int i = start; i < end; i += frequency) {
            list.add(new TimeTable(minutesToTime(i), minutesToTime(i + frequency)));
        }
        return list;
    }

    private String minutesToTime(int minutes){
        int hours = minutes / 60;
        minutes = minutes % 60;
        return hours + ":" + (minutes < 10 ? "0" + minutes : minutes);
    }
}