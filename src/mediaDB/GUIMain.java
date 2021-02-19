package mediaDB;

import java.lang.reflect.Method;

public class GUIMain {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("mediaDB.ui.gui.InternalGUIMain");
            Method meth = cls.getMethod("main", String[].class);
            meth.invoke(null,(Object)args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

