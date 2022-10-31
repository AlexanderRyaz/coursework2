package util;

import java.time.LocalDateTime;
import java.util.List;

public interface Repeatability {
    List<LocalDateTime> getNextAppointments(LocalDateTime appointment, LocalDateTime endDate);
}
