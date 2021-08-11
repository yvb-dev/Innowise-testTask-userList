package util;

import model.User;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializationUtil {

    public static Map<String, User> init() throws IOException, ClassNotFoundException {
        Map<String, User> storage;
        File file = new File("storage/save.ser");
        file.createNewFile();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            if (fileInputStream.available() > 0) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
                    storage = (Map<String, User>) objectInputStream.readObject();
                }
            } else storage = new HashMap<>();
        }
        return storage;
    }

    public static void save(List<User> userList) throws IOException {
        Map<String, User> storage = new HashMap<>();
        for (User user : userList) {
            storage.put(user.getEmail(), user);
        }
        try (FileOutputStream outputStream = new FileOutputStream("storage/save.ser")) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                objectOutputStream.writeObject(storage);
            }
        }
    }
}
