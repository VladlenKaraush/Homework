package main.java.ru.sberbank.jschool.homework.classloaders;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader {

    private final String rootDirectory;

    CustomClassLoader(String rootDirectory){
        this.rootDirectory = rootDirectory;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String path = rootDirectory + "/" + name + ".class";
        File file = new File(path);
        boolean exist = file.exists();//path itself is correct, since such file exists

        if(exist) {
            try {
                byte[] b = Files.readAllBytes(Paths.get(path));
                //name = PrintPlugin
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        else{
            return findSystemClass(name);
        }
    }
}
