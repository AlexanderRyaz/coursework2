package task;

import util.Repeatability;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Yearly implements Repeatability {
    @Override
    public List<LocalDateTime> getNextAppointments(LocalDateTime appointment, LocalDateTime endDate) {
        List<LocalDateTime> nextAppointments = new ArrayList<>();
        LocalDateTime temp = appointment;
        while (temp.isBefore(endDate)) {
            LocalDateTime localDateTime = temp.plusYears(1);
            nextAppointments.add(localDateTime);
            temp = localDateTime;
        }
        return nextAppointments;
    }

    @Override
    public String toString() {
        return "Ежегодно";
    }
}
