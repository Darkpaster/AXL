import axl.LOGGER;
import axl.lexer.Lexer;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class AXL {
    public static final String file =
            """
            boolean true 2f 34 -434 24523432 234324234244 'f' "test"
            """;

    public static void main(String[] args) {
        Lexer lexer = new Lexer(file);
        LOGGER.save();
//        {
//            Class<?> c = Class.forName("java.lang.System");
//            Field f = c.getField("out");
//            System.out.println(f.getGenericType().getTypeName());
//
//            Class<?> cc = Class.forName(f.getGenericType().getTypeName());
//
//            ArrayList<Class<?>> params = new ArrayList<>();
//            params.add(String.class);
//
//            Method m = cc.getMethod("print", getParams(params));
//            System.out.println(m.getAnnotatedReturnType().getType().getTypeName());
//        }
    }

//    public static Class<?>[] getParams(ArrayList<Class<?>> classes) {
//        Class<?>[] params = new Class<?>[classes.size()];
//        for (int i = 0; i < classes.size(); i++) {
//            params[i] = classes.get(i);
//        }
//        return params;
//    }
}