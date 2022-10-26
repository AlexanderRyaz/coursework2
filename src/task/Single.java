package task;

import util.Repeatability;

import java.time.LocalDateTime;

public class Single implements Repeatability {

    @Override
    public LocalDateTime getNextAppointment(LocalDateTime appointment) {
        return null;
    }
}
