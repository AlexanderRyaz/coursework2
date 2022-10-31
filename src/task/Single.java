package task;

import util.Repeatability;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Single implements Repeatability {

    @Override
    public List<LocalDateTime> getNextAppointments(LocalDateTime appointment, LocalDateTime endDate) {
        List<LocalDateTime> nextAppointments = new ArrayList<>();
        nextAppointments.add(appointment);
        return nextAppointments;
    }

    @Override
    public String toString() {
        return "Однократно";
    }
}
