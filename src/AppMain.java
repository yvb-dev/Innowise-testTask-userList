import com.sun.org.slf4j.internal.LoggerFactory;
import model.User;
import repository.InMemoryUserRepository;
import service.ConsoleUserService;
import to.UserTO;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

import static util.ConsoleUtil.*;
import static util.ConsoleUtil.printError;
import static util.SerializationUtil.*;

public class AppMain {

    public static void main(String[] args) {

        // It is an object that carries data between layers.
        UserTO userTO;
        ConsoleUserService service = null;

        // Init application and loading data from file.
        try {
            service = new ConsoleUserService(new InMemoryUserRepository(init()));
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Не удалось инициализировать приложение.");
            System.err.println(e.getMessage());
            System.exit(1);
        }

        while (true) {

            printMainMenu();

            Integer itemMenu;
            try {
                itemMenu = new Scanner(System.in).nextInt();
            } catch (NoSuchElementException e) {
                printError("Введите цифру от 1 до 7");
                continue;
            }

            switch (itemMenu) {
                case 1:
                    System.out.print("\n\n *** Добавление нового пользователя *** \n\n");
                    userTO = new UserTO();
                    do {
                        // Get transfer object and check for return to the main menu.
                        if (requestParameters(userTO)) break;
                        try {
                            // Create new <User>.
                            User user = service.create(userTO);
                            printConfirmation("успешно добавлен.", user.toString());
                            break;
                        } catch (RuntimeException e) {
                            printError(e);
                        }
                    }
                    while (true);
                    break;

                case 2:
                    System.out.print("\n\n *** Редактирование пользователя *** \n\n");
                    userTO = new UserTO();
                    do {
                        // Get email and check for return to the main menu.
                        if (requestEmail(userTO)) break;
                        // Get and check to exist user for received email from {@link #requestEmail(userTO)} method
                        User user = service.get(userTO.getEditEmail());
                        if (user != null) {
                            System.out.println("\nВведите новые данные для указанного пользователя: " + user.toString() + "\n");
                            if (requestParameters(userTO)) break;
                            try {
                                // Update <User> for received email from {@link #requestEmail(userTO)} method
                                user = service.update(userTO);
                                printConfirmation("успешно изменен.", user.toString());
                                break;
                            } catch (RuntimeException e) {
                                printError(e);
                            }
                        } else {
                            printError("Пользователя с email: " + userTO.getEditEmail() + " не существует.");
                        }
                    }
                    while (true);
                    break;

                case 3:
                    System.out.print("\n\n*** Удалить пользователя *** \n\n");
                    userTO = new UserTO();
                    do {
                        if (requestEmail(userTO)) break;
                        // Delete <User> for received email from {@link #requestEmail(userTO)} method
                        if (service.delete(userTO.getEditEmail())) {
                            printConfirmation("успешно удален.", "c email: " + userTO.getEditEmail());
                            break;
                        } else {
                            printError("Пользователя с email: " + userTO.getEditEmail() + " не существует.");
                        }
                    }
                    while (true);
                    break;

                case 4:
                    System.out.print("\n\n*** Поиск пользователя *** \n\n");
                    userTO = new UserTO();
                    do {
                        if (requestEmail(userTO)) break;
                        // Get <User> for received email from {@link #requestEmail(userTO)} method
                        User user = service.get(userTO.getEditEmail());
                        if (user != null) {
                            printConfirmation("Найден!", user.toString());
                            break;
                        } else {
                            printError("Пользователя с email: " + userTO.getEditEmail() + " не существует.");
                        }
                    }
                    while (true);
                    break;

                case 5:
                    System.out.print("\n\n*** Все пользователи *** \n\n");
                    // Get List of Users.
                    printUsers(service.getAll());
                    break;

                case 6:
                    try {
                        // Saving List<Users> as Map<String, User> to file.
                        save(service.getAll());
                        System.out.println("Данные успешно сохранены.");
                    } catch (IOException e) {
                        System.err.println("!!! Ошибка сохранения данных: !!!");
                        System.err.println(e.getMessage());
                        System.err.println("!!! повторите попытку. !!!");
                    }
                    break;

                case 7:
                    try {
                        // Saving before exit.
                        save(service.getAll());
                    } catch (IOException e) {
                        System.err.println("!!! Ошибка сохранения данных: !!!");
                        System.err.println(e.getMessage());
                        System.out.print("\n\n Нажмите \"enter\" для возврата в главное меню или \"exit\" для выхода из программы");
                        if (new Scanner(System.in).nextLine().trim().equals("exit")) {
                            System.exit(1);
                        } else break;
                    }
                    System.exit(0);
            }
        }
    }
}
