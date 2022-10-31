package dailyplanner;

import exception.NotValidTaskException;
import task.Task;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DailyPlanner {
   private final Map<Integer, Task> dailyPlanner = new HashMap<>();


    public Map<Integer, Task> getDailyPlanner() {
        return dailyPlanner;
    }

    public void addDTask(Task task) throws NotValidTaskException {
        if (task.isValid()) {
            dailyPlanner.put(task.getId(), task);
            return;
        }
        throw new NotValidTaskException("невозможно добавить задачу в ежедневник, не хватает данных");
    }

    public void removeTaskById(int id) {
        if (dailyPlanner.containsKey(id)) {
            Task task = dailyPlanner.get(id);
            task.setDeleted(true);
        } else {
            System.out.println("задача с id " + id + " не найдена");
        }
    }

    public void printRemovedTasks() {
        System.out.println("удаленные задачи: ");
        for (Task task : dailyPlanner.values().stream().filter(Task::isDeleted).collect(Collectors.toList())) {
            System.out.println(task);
        }
    }

    public Map<LocalDateTime, List<Task>> groupTasksByDate() {
        Map<LocalDateTime, List<Task>> collect = dailyPlanner.values()
                .stream().collect(Collectors.groupingBy(Task::getAppointment));
        return collect;
    }
}
