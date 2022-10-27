package task;

import util.Repeatability;

import java.time.LocalDateTime;

public class Daily implements Repeatability {
    @Override
    public LocalDateTime getNextAppointment(LocalDateTime appointment) {
        return appointment.plusDays(1);
    }
}
