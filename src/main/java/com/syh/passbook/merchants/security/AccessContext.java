package com.syh.passbook.merchants.security;

// Use TreadLocal to store every token of a thread
public class AccessContext {
    private static final ThreadLocal<String> token = new ThreadLocal<>();
    public static String getToken() {
        return token.get();
    }

    public static void setToken(String tolenStr) {
        token.set(tolenStr);
    }

    public static void clearAccessKey() {
        token.remove();
    }
}
