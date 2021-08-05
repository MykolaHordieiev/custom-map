package com.map.custom.implementation;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        System.out.println("pol");
        Map<Long, String> map = new CustomMap<>();
//        for (int i = 0; i < 100; i++) {
//            map.put((long) i, String.valueOf(i));
//        }
        map.put(1L, "pesik");
        map.put(2L, "post");


        System.out.println(map.keySet());
        System.out.println(map.values());

        System.out.println(map);
        System.out.println(map.entrySet());

//        map.clear();
        map.put(2L, "21");
        System.out.println(map);

        Map<Long, String> map1 = new CustomMap<>();
        map1.put(111L, "212");
        map1.put(2213L, "kaka");
        map.putAll(map1);
        System.out.println(map);
        System.out.println(map.keySet());
        System.out.println(map.values());
        map.put(33L, "pol");
        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println("=============================================");
        System.out.println(map.entrySet());
        System.out.println(map);

    }
}
