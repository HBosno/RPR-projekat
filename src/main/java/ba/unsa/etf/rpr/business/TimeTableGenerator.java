package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.TimeTable;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.ArrayList;
import java.util.List;

public class TimeTableGenerator {
    public List<TimeTable> generate(int start, int end, String frequency, boolean isWeekend) throws AppException {
        int frequencyInMinutes;
        if (isWeekend) {
            frequencyInMinutes = frequencyToMinutes(weekendFrequency(frequency));
        } else {
            frequencyInMinutes = frequencyToMinutes(frequency);
        }

        List<TimeTable> list = new ArrayList<>();
        for (int i = start; i < end; i += frequencyInMinutes) {
            list.add(new TimeTable(minutesToTime(i), minutesToTime(i + frequencyInMinutes)));
        }
        return list;
    }

    private String minutesToTime(int minutes){
        int hours = minutes / 60;
        minutes = minutes % 60;
        return hours + ":" + (minutes < 10 ? "0" + minutes : minutes);
    }

    private String weekendFrequency(String frequency) {
        switch (frequency) {
            case "5 min":
                return "20 min";
            case "15 min":
                return "30 min";
            case "20 min":
                return "50 min";
            case "30 min":
                return "1 h";
            case "45 min":
                return "1h 30 min";
            case "55 min":
                return "2 h";
            case "1 h":
                return "1 h 30 min";
        }
        return "";
    }

    private int frequencyToMinutes(String frequency) throws AppException {
        if (frequency.contains("min") && frequency.contains("h")) {
            String[] array = frequency.split(" ");
            return Integer.parseInt(array[0]) * 60 + Integer.parseInt(array[2]);
        } else if (frequency.contains("min")) {
            return Integer.parseInt(frequency.replaceAll("min", "").trim());
        } else if (frequency.contains("h")) {
            return Integer.parseInt(frequency.replaceAll("h", "").trim()) * 60;
        }
        throw new AppException("Invalid frequency");
    }
}