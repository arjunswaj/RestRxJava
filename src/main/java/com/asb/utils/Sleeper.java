package com.asb.utils;

/**
 * Sleeper which sleeps.
 * Created by arjun on 12/03/16.
 */
public class Sleeper {
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
