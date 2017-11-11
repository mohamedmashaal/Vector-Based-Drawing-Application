package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class ClassFinder extends URLClassLoader {

    private final ClassLoader mainClassLoader = ClassFinder.class.getClassLoader();
    private final Set<String> moduleClasses;

    private ClassFinder(URL url) {
        super(new URL[]{ url });
        try {
            JarURLConnection connection = (JarURLConnection) url.openConnection();

            this.moduleClasses = connection.getJarFile().stream()
                .map(JarEntry::getName)
                .filter(name -> name.endsWith(".class"))
                .map(name -> name.replace(".class", "").replaceAll("/", "."))
                .collect(Collectors.toSet());
            for(String x : moduleClasses) {
            	System.out.println(x);
            }
        } catch(IOException e) {
            throw new IllegalArgumentException(String.format("Unexpected error while reading module jar: %s", e.getMessage()));
        }
    }

    public static ClassFinder newInstance(JarFile libraryJar) {
        try {
            return new ClassFinder(new URL(String.format("jar:file:%s!/", libraryJar.getName())));
        } catch(MalformedURLException e) {
            throw new IllegalArgumentException(String.format("Path to module jar could not be converted into proper URL: %s", e.getMessage()));
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if(moduleClasses.contains(name)) {
            Class<?> clazz = findLoadedClass(name);
            if(clazz != null) {
                return clazz;
            } else {
                return findClass(name);
            }
        } else {
            return mainClassLoader.loadClass(name);
        }
    }
}
