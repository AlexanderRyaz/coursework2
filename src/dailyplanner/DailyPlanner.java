package dailyplanner;

import exception.NotValidTaskException;
import task.Task;

import java.util.HashMap;
import java.util.Map;

public class DailyPlanner {
    Map<Integer, Task> dailyPlanner = new HashMap<>();

    public Map<Integer, Task> getDailyPlanner() {
        return dailyPlanner;
    }
    public void addDTask(Task task){
        if (task.isValid()){
            dailyPlanner.put(task.getId(), task);
            return;
        }throw new NotValidTaskException("невозможно добавить задачу в ежедневник, не хватает данных");
    }
}
