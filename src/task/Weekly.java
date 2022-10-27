package task;

import util.Repeatability;

import java.time.LocalDateTime;

public class Weekly implements Repeatability {
    @Override
    public LocalDateTime getNextAppointment(LocalDateTime appointment) {
        return appointment.plusWeeks(1);
    }
}
