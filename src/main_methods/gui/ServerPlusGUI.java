package main_methods.gui;

import java.lang.reflect.Method;

public class ServerPlusGUI {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("mediaDB.ui.gui.ServerGUI");
            Method meth = cls.getMethod("main", String[].class);
            meth.invoke(null,(Object)args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
