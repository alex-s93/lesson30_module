package com.alevel.module3.dao.impl;

import java.util.HashMap;
import java.util.Map;

class TestUtils {
    private static int i = 0;

    static Map<String, String> getData() {
        Map<String, String> data = new HashMap<>();
        data.put("fullName", "Alex"+ i + " Solodkov"+i);
        data.put("email", "test"+i+"@test.com");
        data.put("login", "test"+i);
        i++;
        return data;
    }
}
