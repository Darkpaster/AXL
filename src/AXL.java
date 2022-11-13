import axl.LOGGER;
import axl.lexer.Lexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AXL {
    public static String file;

    public static void main(String[] args) throws IOException {
        file = Files.readString(Paths.get("C:\\Users\\home\\Documents\\GitHub\\AXL\\src\\Test.axl"));

        Lexer lexer = new Lexer(file);
        LOGGER.save();
//        { yuyg
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