import dailyplanner.DailyPlanner;
import task.*;
import util.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DailyPlanner dailyPlanner = new DailyPlanner();
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
        dailyPlanner.addDTask(task);
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
                        " 0. Выход");

    }

    private static void printTasksForDate(Scanner scanner, DailyPlanner dailyPlanner) {
        LocalDateTime dateTime = readDateTime(scanner);
        System.out.println("задачи на дату: " + dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        for (Task task : dailyPlanner.getDailyPlanner().values()) {
            if (task.getAppointment().equals(dateTime)) {
                System.out.println(task);
            }
        }
    }

    private static void removeTaskById(Scanner scanner, DailyPlanner dailyPlanner) {
        while (true) {
            try {
                System.out.println("введите id удаляемой задачи ");
                String idAsString = scanner.next();
                int id = Integer.parseInt(idAsString);
                dailyPlanner.removeTaskById(id);
                System.out.println("задача удалена");
                break;
            } catch (NumberFormatException e) {
                System.out.println("введено неверное значение");
            }
        }
    }
}