package mediaDB;

import java.lang.reflect.Method;

/*
Quellen:
https://flicsdb.com/call-main-method-from-another-main-method-in-java/
 */
public class GUIMain {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("mediaDB.ui.gui.SeperateGUI");
            Method meth = cls.getMethod("main", String[].class);
            meth.invoke(null,(Object)args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

