package util;

import java.time.LocalDateTime;

public interface Repeatability {
LocalDateTime getNextAppointment(LocalDateTime appointment);
}
