package util;

public enum TaskType {
    PERSONAL("личная"), WORKING("рабочая");
    private final String taskType;

    TaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }
}
