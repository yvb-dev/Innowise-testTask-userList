package util;

import model.RoleLevel1;
import model.RoleLevel2;
import model.RoleLevel3;
import model.User;
import to.UserTO;
import util.exception.NotValidException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static StringBuilder exceptionMessage;
    private static User user;
    private static int countLevel1;
    private static int countLevel2;
    private static int countLevel3;
    private static final int AMOUNT_LEVEL = 2;

    public static User getValidationUser(UserTO userTO) {

        exceptionMessage = new StringBuilder();
        user = new User();
        countLevel1 = 0;
        countLevel2 = 0;
        countLevel3 = 0;

        validationFirstName(userTO.getFirstName());
        validationLastName(userTO.getLastName());
        validationEmail(userTO.getEmail());
        validationMobile(userTO.getMobile());
        validationRoles(userTO.getRoles());

        if (!exceptionMessage.toString().isEmpty()) {
            throw new NotValidException(exceptionMessage.toString());
        }

        return user;

    }

    private static void validationFirstName(String name) {
        name = name.trim();
        if (name.isEmpty() || name.length() < 3) {
            exceptionMessage.append("Поле имя не должно быть пустым или содержать менее 3 символов\n");
        } else user.setFirstName(name);
    }

    private static void validationLastName(String name) {
        name = name.trim();
        if (name.isEmpty() || name.length() < 3) {
            exceptionMessage.append("Поле фамилия не должно быть пустым или содержать менее 3 символов\n");
        } else user.setLastName(name);
    }

    private static void validationEmail(String email) {
        email = email.trim();
        String regex = "^(.+)@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            exceptionMessage.append("Невалидный email: ").append(email).append("\n");
        } else user.setEmail(email);
    }

    private static void validationMobile(String mobile) {
        ArrayList<String> listMobile = new ArrayList<>();
        String[] numbers = mobile.trim().split(",");
        if (numbers.length == 0 || numbers.length > 3) {
            exceptionMessage.append("Неверное количество номеров: ").append(mobile).append("\n");
            return;
        }
        HashSet<String> set = new HashSet<>();
        for (String number : numbers) {
            boolean isDuplicate = set.add(number.trim());
            if (!isDuplicate) {
                exceptionMessage.append("Не допускается вводить дубликаты номеров: ").append(mobile).append("\n");
                System.out.println(number);
                return;
            }
        }
        for (String number : numbers) {
            if (!number.trim().matches("^[375][0-9\\s-]{9,18}$")) {
                exceptionMessage.append("Неверный формат номера: ").append(number).append("\n");
                return;
            } else listMobile.add(number);
        }
        user.setMobile(listMobile);
    }

    private static void validationRoles(String roles) {
        String[] arrRoles = roles.trim().split("\\s+");
        if (roles.isEmpty() || arrRoles.length > AMOUNT_LEVEL) {
            exceptionMessage.append("Указано некорректное количество или сочетание ролей: ").append(roles).append("\n");
            return;
        }
        for (String role : arrRoles) {
            if (!setRoleLevel(role)) {
                exceptionMessage.append("Такой роли не существует: ").append(role).append("\n");
            }
        }
        if (countLevel3 != 0 && arrRoles.length > 1) {
            exceptionMessage.append("Для SUPER_ADMIN - другие роли выбирать запрещено").append("\n");
            return;
        }
        if (countLevel1 > 1 || countLevel2 > 1) {
            exceptionMessage.append("Одновременно пользователю можно назначать по 1 роли с каждого уровня").append("\n");
        }
    }

    private static boolean setRoleLevel(String level) {
        for (RoleLevel1 le : RoleLevel1.values()) {
            if (le.name().equalsIgnoreCase(level)) {
                user.setRoleLevel1(le);
                countLevel1++;
                return true;
            }
        }
        for (RoleLevel2 le : RoleLevel2.values()) {
            if (le.name().equalsIgnoreCase(level)) {
                user.setRoleLevel2(le);
                countLevel2++;
                return true;
            }
        }
        for (RoleLevel3 le : RoleLevel3.values()) {
            if (le.name().equalsIgnoreCase(level)) {
                user.setRoleLevel3(le);
                countLevel3++;
                return true;
            }
        }
        return false;
    }
}
