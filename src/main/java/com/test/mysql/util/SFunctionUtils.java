package com.test.mysql.util;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

public class SFunctionUtils {
    public static <T> String getColumnName(SFunction<T, ?> lambda) {
        SerializedLambda serializedLambda = resolveSerializedLambda(lambda);
        String methodName = serializedLambda.getImplMethodName();
        String propertyName = methodName.startsWith("get") ? methodName.substring(3) : methodName;
        return convertToUnderscoreCase(propertyName);
    }

    private static SerializedLambda resolveSerializedLambda(SFunction<?, ?> lambda) {
        try {
            Method method = lambda.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            return (SerializedLambda) method.invoke(lambda);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to resolve SerializedLambda", e);
        }
    }

    private static String convertToUnderscoreCase(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    result.append("_");
                }
                result.append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        SFunction<User, String> getMyAge = User::getMyAge;
        String columnName = getColumnName(getMyAge);
        System.out.println(columnName);
    }
}

class User {
    private String name;
    private String myAge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMyAge() {
        return myAge;
    }

    public void setMyAge(String myAge) {
        this.myAge = myAge;
    }
}
