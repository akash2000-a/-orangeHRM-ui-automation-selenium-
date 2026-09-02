package com.orangehrm.utils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

    public static Object[][] getJsonData(String resourcePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(resourcePath);
            if (is == null) {
                throw new RuntimeException("Could not find file on classpath: " + resourcePath);
            }
            List<Map<String, String>> dataList = mapper.readValue(is, new TypeReference<List<Map<String, String>>>() {});
            
            Object[][] data = new Object[dataList.size()][3];
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, String> map = dataList.get(i);
                data[i][0] = map.get("username");
                data[i][1] = map.get("password");
                data[i][2] = map.get("expectedErrorMessage");
            }
            return data;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file at " + resourcePath + ": " + e.getMessage(), e);
        }
    }
}
