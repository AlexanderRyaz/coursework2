package task;

import util.Repeatability;

import java.time.LocalDateTime;

public class Monthly implements Repeatability {
    @Override
    public LocalDateTime getNextAppointment(LocalDateTime appointment) {
        return appointment.plusMonths(1);
    }
}
