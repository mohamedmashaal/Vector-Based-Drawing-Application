package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

import eg.edu.alexu.csd.oop.draw.Shape;

public class ClassGetter {
	private Class<Shape> shape = Shape.class; 
    private final ClassLoader mainClassLoader = getClass().getClassLoader();
    private final Set<String> moduleClasses;
    private ArrayList<Class<? extends Shape>> classes = new ArrayList<>();
    private URL url ;

    private ClassGetter(URL url) {
    	this.url = url ;
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

    public static ClassGetter newInstance(JarFile Plugin) {
        try {
            return new ClassGetter(new URL(String.format("jar:file:%s!/", Plugin.getName())));
        } catch(MalformedURLException e) {
            throw new IllegalArgumentException(String.format("Path to module jar could not be converted into proper URL: %s", e.getMessage()));
        }
    }


    public ArrayList<Class<? extends Shape>> getClasses(){
        for(String x : moduleClasses) {
        	Class<?> toAdd;
			try {
				ClassLoader loader =  URLClassLoader.newInstance(new URL[] {url}, mainClassLoader);
				toAdd = loader.getClass().forName(x, true, loader);
				if(!toAdd.isInterface() &&!Modifier.isAbstract(toAdd.getModifiers())&&toAdd.newInstance() instanceof Shape) {
	        		System.out.println("It is ");
					classes.add((Class<? extends Shape>) toAdd);
	        	}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	return classes;
    }
}
