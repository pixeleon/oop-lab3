package net.pixeleon.khpi.oop.labthree;

import java.util.Map;
import java.util.TreeMap;

public class UsersPasswords {
    public static void main(String[] args) {
        Map<String, String> userPasswordMap = new TreeMap<>();
        userPasswordMap.put("alexdarkstalker", "19alex98");
        userPasswordMap.put("user", "password");
        userPasswordMap.put("mizar", "od007");
        userPasswordMap.put("jenn", "1cow1");
        userPasswordMap.put("sams", "evo32plus");
        userPasswordMap.put("sponge-bob", "9ary");

        for (Map.Entry<String, String> entry : userPasswordMap.entrySet()) {
            if (entry.getValue().length() > 6)
                System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
