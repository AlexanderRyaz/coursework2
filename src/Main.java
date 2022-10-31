import dailyplanner.DailyPlanner;
import exception.NotValidTaskException;
import task.*;
import util.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NotValidTaskException {
        DailyPlanner dailyPlanner = new DailyPlanner();
        Task task1 = new Task("днюха");
        task1.setDescription("праздновать");
        task1.setRepeatability(new Yearly());
        task1.setTaskType(TaskType.PERSONAL);
        task1.setAppointment(LocalDateTime.parse("05.10.2023 00:00",
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        Task task2 = new Task("свадьба");
        task2.setDescription("праздновать");
        task2.setRepeatability(new Yearly());
        task2.setEndDate(LocalDateTime.parse("05.10.2026 00:00",
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        task2.setTaskType(TaskType.PERSONAL);
        task2.setAppointment(LocalDateTime.parse("11.10.2023 00:00",
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        Task task3 = new Task("вечеринка");
        task3.setDescription("гулять");
        task3.setRepeatability(new Single());
        task3.setTaskType(TaskType.PERSONAL);
        task3.setAppointment(LocalDateTime.parse("11.10.2023 00:00",
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        dailyPlanner.addDTask(task1);
        dailyPlanner.addDTask(task2);
        dailyPlanner.addDTask(task3);
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner, dailyPlanner);
                            break;
                        case 2:
                            removeTaskById(scanner, dailyPlanner);
                            break;
                        case 3:
                            printTasksForDate(scanner, dailyPlanner);
                            break;
                        case 4:
                            printTasksGroupedByDate(dailyPlanner);
                            break;
                        case 5:
                            printRemovedTasks(dailyPlanner);
                            break;
                        case 6:
                            changeTaskName(scanner, dailyPlanner);
                            break;
                        case 7:
                            changeTaskDescription(scanner, dailyPlanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void changeTaskDescription(Scanner scanner, DailyPlanner dailyPlanner) {
        int taskId = readTaskId(scanner);
        System.out.println("введите новое описание задачи");
        String newDescription = scanner.next();
        Task task = dailyPlanner.getDailyPlanner().get(taskId);
        if (task == null) {
            System.out.println("Задача с таким номером не найдена");
        } else {
            task.setDescription(newDescription);
        }
    }

    private static void changeTaskName(Scanner scanner, DailyPlanner dailyPlanner) {
        int taskId = readTaskId(scanner);
        System.out.println("введите новое название задачи");
        String newName = scanner.next();
        Task task = dailyPlanner.getDailyPlanner().get(taskId);
        if (task == null) {
            System.out.println("Задача с таким номером не найдена");
        } else {
            task.setName(newName);
        }
    }

    private static void printRemovedTasks(DailyPlanner dailyPlanner) {
        dailyPlanner.printRemovedTasks();
    }

    private static void printTasksGroupedByDate(DailyPlanner dailyPlanner) {
        Map<LocalDateTime, List<Task>> localDateTimeListMap = dailyPlanner.groupTasksByDate();
        for (LocalDateTime localDateTime : localDateTimeListMap.keySet()) {
            System.out.println("Задачи на дату: " +
                    localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
            for (Task task : localDateTimeListMap.get(localDateTime)) {
                System.out.println(task);
            }
        }
    }

    private static void inputTask(Scanner scanner, DailyPlanner dailyPlanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        Task task = new Task(taskName);
        System.out.println("введите описание задачи: ");
        String taskDescription = scanner.next();
        task.setDescription(taskDescription);
        boolean isDataCorrect = false;
        while (!isDataCorrect) {
            System.out.println("введите тип задачи:\n "
                    + "1. личная\n"
                    + "2. рабочая");
            int taskType = scanner.nextInt();
            switch (taskType) {
                case 1:
                    task.setTaskType(TaskType.PERSONAL);
                    isDataCorrect = true;
                    break;
                case 2:
                    task.setTaskType(TaskType.WORKING);
                    isDataCorrect = true;
                    break;
                default:
                    System.out.println("неверный ввод");
            }
        }
        isDataCorrect = false;
        while (!isDataCorrect) {
            System.out.println("введите повторяемость задачи:\n "
                    + "1. однократная\n"
                    + "2. ежедневная\n"
                    + "3. еженедельная\n"
                    + "4. ежемесячная\n"
                    + "5. ежегодная");
            int repeatability = scanner.nextInt();
            switch (repeatability) {
                case 1:
                    task.setRepeatability(new Single());
                    isDataCorrect = true;
                    break;
                case 2:
                    task.setRepeatability(new Daily());
                    isDataCorrect = true;
                    break;
                case 3:
                    task.setRepeatability(new Weekly());
                    isDataCorrect = true;
                    break;
                case 4:
                    task.setRepeatability(new Monthly());
                    isDataCorrect = true;
                    break;
                case 5:
                    task.setRepeatability(new Yearly());
                    isDataCorrect = true;
                    break;
                default:
                    System.out.println("неверный ввод");
            }
        }
        LocalDateTime localDateTime = readDateTime(scanner);
        task.setAppointment(localDateTime);
        try {
            dailyPlanner.addDTask(task);
        } catch (NotValidTaskException e) {
            System.out.println("не все данные введены");
        }
    }

    private static LocalDateTime readDateTime(Scanner scanner) {
        while (true) {
            System.out.println("введите дату в формате dd.MM.yyyy HH:mm");
            String appointment = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            try {
                return LocalDateTime.parse(appointment, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("неверный формат даты");
            }
        }
    }

    private static void printMenu() {
        System.out.println(
                " 1. Добавить задачу \n" +
                        " 2. Удалить задачу \n" +
                        " 3. Получить задачу на указанный день\n" +
                        " 4. Получить задачи сгрупированные по датам\n" +
                        " 5. Получить список удаленных задач\n" +
                        " 6. Редактировать название задачи \n" +
                        " 7. Редактировать описание задачи\n" +
                        " 0. Выход");

    }

    private static void printTasksForDate(Scanner scanner, DailyPlanner dailyPlanner) {
        LocalDateTime dateTime = readDateTime(scanner);
        System.out.println("задачи на дату: " + dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        for (Task task : dailyPlanner.groupTasksByDate().get(dateTime)) {
            System.out.println(task);
            if (!(task.getRepeatability() instanceof Single)) {
                System.out.println("так же запланированно на следующие даты: "
                        + Arrays.toString(task.getRepeatability().getNextAppointments(task.getAppointment(),
                        task.getEndDate()).toArray()));
            }
        }
    }

    private static void removeTaskById(Scanner scanner, DailyPlanner dailyPlanner) {
        int id = readTaskId(scanner);
        dailyPlanner.removeTaskById(id);
        System.out.println("задача удалена");
    }

    private static int readTaskId(Scanner scanner) {
        int id;
        while (true) {
            try {
                System.out.println("введите id удаляемой задачи ");
                String idAsString = scanner.next();
                id = Integer.parseInt(idAsString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("введено неверное значение");
            }
        }
        return id;
    }
}