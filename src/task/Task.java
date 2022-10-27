package task;

import util.Repeatability;
import util.TaskType;

import java.time.LocalDateTime;

public class Task {
    private static int counter = 1;
    private final int id;
    private  String name;
    private String description;
    private TaskType taskType;
    private LocalDateTime creationDate;
    private Repeatability repeatability;
    private LocalDateTime appointment;
    private boolean isDeleted;

    public Task(String name) {
        this.id = counter++;
        this.name = name;
        this.creationDate = LocalDateTime.now();
        this.isDeleted = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Repeatability getRepeatability() {
        return repeatability;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setRepeatability(Repeatability repeatability) {
        this.repeatability = repeatability;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getAppointment() {
        return appointment;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setAppointment(LocalDateTime appointment) {
        this.appointment = appointment;
    }

    public boolean isValid() {

        return this.taskType != null
                && this.repeatability != null
                && this.name != null
                && !this.name.isEmpty()
                && this.description != null
                && !this.description.isEmpty()
                && this.appointment != null;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", taskType=" + taskType +
                ", creationDate=" + creationDate +
                ", repeatability=" + repeatability +
                ", appointment=" + appointment +
                '}';
    }
}
