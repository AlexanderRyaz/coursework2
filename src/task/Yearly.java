package task;

import util.Repeatability;

import java.time.LocalDateTime;

public class Yearly implements Repeatability {
    @Override
    public LocalDateTime getNextAppointment(LocalDateTime appointment) {
        return appointment.plusYears(1);
    }
}
