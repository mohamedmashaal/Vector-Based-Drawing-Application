package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import eg.edu.alexu.csd.oop.draw.Shape;

public class ClassGetter {
	private final ClassLoader mainClassLoader = getClass().getClassLoader();
	private final Set<String> moduleClasses;
	private ArrayList<Class<? extends Shape>> classes = new ArrayList<>();
	private URL url;

	private ClassGetter(URL url) {
		this.url = url;
		this.moduleClasses = new HashSet<>();
		try {
			JarURLConnection connection = (JarURLConnection) url.openConnection();
			JarFile file = connection.getJarFile();
			Enumeration<JarEntry> entries = file.entries();
			while (entries.hasMoreElements()) {
				JarEntry e = entries.nextElement();
				if (e.getName().endsWith(".class")) {
					String name = e.getName().replace(".class", "").replaceAll("/", ".");
					moduleClasses.add(name);
				}
			}
		} catch (IOException e) {
			throw new IllegalArgumentException(
					String.format("Unexpected error while reading module jar: %s", e.getMessage()));
		}
	}

	public static ClassGetter newInstance(JarFile Plugin) {
		try {
			return new ClassGetter(new URL(String.format("jar:file:%s!/", Plugin.getName())));
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(
					String.format("Path to module jar could not be converted into proper URL: %s", e.getMessage()));
		}
	}

	public ArrayList<Class<? extends Shape>> getClasses() {
		for (String x : moduleClasses) {
			Class<?> toAdd;
			try {
				ClassLoader loader = URLClassLoader.newInstance(new URL[] { url }, mainClassLoader);
				toAdd = loader.getClass().forName(x, true, loader);
				if (!toAdd.isInterface() && !Modifier.isAbstract(toAdd.getModifiers())
						&& toAdd.newInstance() instanceof Shape) {
					classes.add((Class<? extends Shape>) toAdd);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return classes;
	}
}
