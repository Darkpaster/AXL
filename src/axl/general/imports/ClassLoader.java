package axl.general.imports;

import axl.LOGGER;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassLoader {

    private static final String CLASS_SUFFIX = ".class";

    public static Class<?> loadClass(String cls) {
        try {
            return Class.forName(cls.replace('/', '.'));
        } catch (ClassNotFoundException e) {
            LOGGER.log("[CODE-GEN] не удалось загрузить класс \""+cls.replace('/', '.')+"\"", true);
            return String.class;
        }
    }

    private static List<Class<?>> processDirectory(File dir, String pk) {
        List<Class<?>> classes = new ArrayList<>();
        for (String file : Objects.requireNonNull(dir.list())) {
            String cls;

            if (file.endsWith(CLASS_SUFFIX)) {

                cls = pk + '.' + file.substring(0, file.length() - 6);
                classes.add(loadClass(cls));
            }

            File subdir = new File(dir, file);
            if (subdir.isDirectory()) {
                classes.addAll(processDirectory(subdir, pk + '.' + file));
            }
        }
        return classes;
    }

    private static List<Class<?>> processJarfile(URL resource, String pk) {
        List<Class<?>> classes = new ArrayList<>();

        String relPath = pk.replace('.', '/');
        String resPath = resource.getPath();
        String jarPath = resPath.replaceFirst("[.]jar!.*", ".jar").replaceFirst("file:", "");

        try (JarFile jarFile = new JarFile(jarPath)) {

            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();

                String entryName = entry.getName();
                String className = null;

                if (entryName.endsWith(CLASS_SUFFIX) && entryName.startsWith(relPath)
                        && entryName.length() > (relPath.length() + "/".length())) {
                    className = entryName.replace('/', '.').replace('\\', '.').replace(CLASS_SUFFIX, "");
                }

                if (className != null) {
                    classes.add(loadClass(className));
                }
            }
        } catch (IOException e) {
            LOGGER.log("[CODE-GEN] не удалось прочтитать jar файл ~\""+pk+"\"", true);
        }
        return classes;
    }

    private static List<Class<?>> getPackageClasses(String pkg) throws IOException {
        return getPackageClasses(pkg, java.lang.ClassLoader.getSystemClassLoader());
    }

    private static List<Class<?>> getPackageClasses(String pkgname, java.lang.ClassLoader l) throws IOException {
        List<Class<?>> classes = new ArrayList<>();

        String relPath = pkgname.replace('.', '/');

        Enumeration<URL> resources = l.getResources(relPath);
        if (!resources.hasMoreElements()) {
            throw new IOException();
        } else {
            do {
                URL resource = resources.nextElement();
                if (resource.toString().startsWith("jar:")) {
                    classes.addAll(processJarfile(resource, pkgname));
                } else {
                    File dir = new File(resource.getPath());
                    classes.addAll(processDirectory(dir, pkgname));
                }
            } while (resources.hasMoreElements());
        }
        return classes;
    }

    private static final String START_CLASS_PATH = "/modules/java.base/";

    private static List<Class<?>> findClasses(Path module) throws IOException {
        List<Class<?>> classes = new ArrayList<>();
        Files.walkFileTree(module, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                String fileName = file.getFileName().toString();
                if (fileName.endsWith(".class") && !fileName.contains("$")) {
                    classes.add(loadClass(file.toString().substring(START_CLASS_PATH.length(), file.toString().length()-CLASS_SUFFIX.length()).replace('/', '.')));
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return classes;
    }

    static List<Class<?>> loadClasses(String pk)
    {
        List<Class<?>> classes;
        try {
            classes = getPackageClasses(pk);
        } catch (IOException e) {
            try {
                FileSystem fileSystem = FileSystems.getFileSystem(URI.create("jrt:/"));
                Path modules = fileSystem.getPath("/modules/java.base/" + pk.replace('.', '/'));
                classes = findClasses(modules);
            } catch (IOException ex) {
                LOGGER.log("[CODE-GEN] не удалось загрузить пакет \""+pk+"\"", true);
                return new ArrayList<>();
            }
        }

        List<Class<?>> classes_final = new ArrayList<>();
        for (Class<?> class_: classes)
        {
            if(class_.getSimpleName().length()+pk.length()+1 == class_.getName().length())
                if(!class_.getName().contains("$"))
                    classes_final.add(class_);
        }

        return classes_final;
    }
}
