import task.*;
import util.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            // todo: обрабатываем пункт меню 2
                            break;
                        case 3:
                            // todo: обрабатываем пункт меню 3
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

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        Task task = new Task(taskName);
        System.out.println("введите описание задачи: ");
        String taskDescription = scanner.next();
        task.setDescription(taskDescription);
        System.out.println("введите тип задачи:\n "
                + "1. личная\n"
                + "2. рабочая");
        int taskType = scanner.nextInt();
        switch (taskType) {
            case 1:
                task.setTaskType(TaskType.PERSONAL);
                break;
            case 2:
                task.setTaskType(TaskType.WORKING);
                break;
            default:
                System.out.println("неверный ввод");
        }
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
                break;
            case 2:
                task.setRepeatability(new Daily());
                break;
            case 3:
                task.setRepeatability(new Weekly());
                break;
            case 4:
                task.setRepeatability(new Monthly());
                break;
            case 5:
                task.setRepeatability(new Yearly());
                break;
            default:
                System.out.println("неверный ввод");
        }
        System.out.println("введите дату в формате dd.MM.yyyy HH:mm");
        String appointment = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(appointment, formatter);
            task.setAppointment(dateTime);
        }catch (DateTimeParseException e){
            System.out.println("неверный формат даты");
        }
    }

    private static void printMenu() {
        System.out.println(
                " 1. Добавить задачу \n" +
                        " 2. Удалить задачу \n" +
                        " 3. Получить задачу на указанный день\n" +
                        " 0. Выход");

    }
}