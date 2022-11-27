package axl.general;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FileImports {
    private static ArrayList<java.lang.Class<?>> classes = new ArrayList<>();

    public static void import_one(String dir)
    {
        try {
            classes.add(java.lang.Class.forName(dir));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

