package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.TimeTable;

import java.util.List;

public interface TimeTableGenerator {
    List<TimeTable> generate(int start, int end, int frequency);
}
