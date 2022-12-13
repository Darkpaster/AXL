package axl.general.imports;

import axl.LOGGER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Imports
{
    private static HashMap<String, Class<?>> classes = new HashMap<>();

    public static void import_one(String dir, String as)
    {
        Class<?> load = ClassLoader.loadClass(dir.replace('/', '.'));

        if (as.equals(""))
            as = load.getSimpleName();
        classes.put(as, load);
    }

    public static void import_one(String dir)
    {
        import_one(dir, "");
    }

    public static void import_mul(String dir)
    {
        List<Class<?>> classes = ClassLoader.loadClasses(dir);
        for(Class<?> class_: classes)
            Imports.classes.put(class_.getSimpleName(), class_);
    }

    public static Class<?> getClass(String name)
    {
        if(!classes.containsKey(name)) {
            LOGGER.log("[CODE-GEN] не удалось загрузить класс \""+name+"\"", true);
            return String.class;
        }
        return (classes.get(name));
    }

    public static String ClassToTypeJvm(String name)
    {
        Class<?> clazz = getClass(name);
        return 'L'+clazz.getName().replace('.', '/')+';';
    }

    public static String ClassToTypeJvm(Class<?> clazz)
    {
        return 'L'+clazz.getName().replace('.', '/')+';';
    }

    public static ArrayList<String> getClassesSimple()
    {
        return new ArrayList<>(classes.keySet());
    }
}

