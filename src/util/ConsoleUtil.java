package util;

import model.RoleLevel1;
import model.RoleLevel2;
import model.RoleLevel3;
import model.User;
import to.UserTO;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleUtil {

    public static String parameter;

    public static boolean requestParameters(UserTO userTO) {
        if (isExit("Введите имя пользователя:")) return true;
        else
            userTO.setFirstName(parameter);
        if (isExit("Введите фамилию пользователя:")) return true;
        else
            userTO.setLastName(parameter);
        if (isExit("Введите email пользователя:")) return true;
        else
            userTO.setEmail(parameter);
        if (isExit("Введите роли пользователя:" +
                "\nРоли 1ур.:" + Arrays.asList(RoleLevel1.values()) +
                "\nРоли 2ур.:" + Arrays.asList(RoleLevel2.values()) +
                "\nРоли 3ур.:" + Arrays.asList(RoleLevel3.values()) +
                "\nразделив пробелом." +
                "\nДля SUPER_ADMIN - другие роли выбирать запрещено" +
                "\nОдновременно пользователь может иметь по 1 роли с каждого уровня")) return true;
        else
            userTO.setRoles(parameter);
        if (isExit("Введите мобильный телефон(ы) пользователя:\n" +
                "В количестве от 1 до 3, разделив \",\" Формат - 375 *****")) return true;
        else
            userTO.setMobile(parameter);
        return false;
    }

    public static String getParameter(String menu) {
        System.out.println(menu);
        System.out.print("(или \"exit\" для возврата в главное меню)\n");
        parameter = new Scanner(System.in).nextLine();
        return parameter;
    }

    public static boolean requestEmail(UserTO userTO) {
        if (isExit("Введите email")) return true;
        else
            userTO.setEditEmail(parameter);
        return false;
    }

    public static boolean isExit(String menu) {
        return "exit".equals(getParameter(menu));
    }

    public static void printMainMenu() {
        System.out.print("\n\n *** Главное меню для работы со списком пользователей *** \n\n" +
                "1. Создать пользователя \n" +
                "2. Редактировать пользователя \n" +
                "3. Удалить пользователя \n" +
                "4. Поиск пользователя \n" +
                "5. Все пользователи \n" +
                "6. Сохранить данные на диск \n" +
                "7. Выход из программы \n" +
                "\n Выберите необходимый пункт меню... \n");
    }

    public static void printConfirmation(String str, String user) {
        System.out.print("\n Пользователь:\n");
        System.out.print(user);
        System.out.print("\n " + str + "\n");
        waitToBack();
    }

    public static void printError(RuntimeException e) {
        printError(e.getMessage());
    }

    public static void printError(String str) {
        System.out.print("\n <<< Ошибка ввода данных! Повторите ввод! >>> \n");
        System.out.print(str + "\n");
    }

    public static void printUsers(List<User> users) {
        for (User user : users) {
            System.out.println("--------------------------------------");
            System.out.println(user.toString());
            System.out.println("--------------------------------------");
        }
        waitToBack();
    }

    public static void waitToBack() {
        System.out.print("\n\n Нажмите \"enter\" для возврата в главное меню...");
        new Scanner(System.in).nextLine();
    }
}
