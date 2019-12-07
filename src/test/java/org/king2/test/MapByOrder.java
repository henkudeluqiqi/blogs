package org.king2.test;

import java.util.*;

public class MapByOrder {


    public static void main(String[] args) {
        Map<String, Integer> map = getMap();
        sort(map);

    }

    // 当前的最小值
    public static Integer MIN_VALUE = null;
    // Map中进行了多少个排序
    public static Integer ORDER_SIZE = 0;
    // 当前最小值在LIST当中的索引
    public static Integer CURRENT_MIN_VALUE_INDEX = null;
    // 当前Map遍历的索引
    public static Integer CURRENT_MAP_INDEX = 0;
    // 有序的MapKey
    public static String[] MAP_KEY_LISTS;

    public static void sort(Map<String, Integer> map) {

        MAP_KEY_LISTS = new String[map.size()];
        for (int i = 0; i < 2; i++) {
            int j = 0;
            for (Map.Entry<String, Integer> data : map.entrySet()) {
                if (i == 0) {
                    MAP_KEY_LISTS[j++] = data.getKey();
                } else {
                    Integer value = data.getValue();
                    if (MIN_VALUE == null) {
                        MIN_VALUE = value;
                        ORDER_SIZE++;
                        CURRENT_MIN_VALUE_INDEX = 0;
                    } else {
                        ++CURRENT_MAP_INDEX;
                        if (value <= MIN_VALUE) {
                            for (Integer orderSize = ORDER_SIZE; orderSize > 0; orderSize--) {
                                MAP_KEY_LISTS[orderSize] = MAP_KEY_LISTS[orderSize - 1];
                            }
                            MAP_KEY_LISTS[0] = data.getKey();
                            MIN_VALUE = value;
                            ORDER_SIZE++;
                        } else {
                            for (Integer integer = 1; integer < ORDER_SIZE; integer++) {
                                if (value < map.get(MAP_KEY_LISTS[integer])) {
                                    String mapKeyList = MAP_KEY_LISTS[CURRENT_MAP_INDEX];
                                    MAP_KEY_LISTS[CURRENT_MAP_INDEX] = MAP_KEY_LISTS[integer];
                                    MAP_KEY_LISTS[integer] = mapKeyList;
                                }
                            }

                            ORDER_SIZE++;
                        }
                    }

                }
            }
        }

        Map<String, Integer> newMap = new HashMap<>();
        newMap.putAll(map);
        map.clear();
        for (String mapKeyList : MAP_KEY_LISTS) {
            map.put(mapKeyList, newMap.get(mapKeyList));
            System.out.println("KEY:" + mapKeyList + ",Value:" + newMap.get(mapKeyList));
        }
        System.out.println("-------------------");
        newMap.clear();
    }

    public static Map<String, Integer> getMap() {

        Map<String, Integer> map = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int i1 = random.nextInt(1000);
            map.put(i + "", i1);
            if (i == 1) {
                map.put(i + "复制" + "", i1);
            }
        }
        return map;
    }
}
