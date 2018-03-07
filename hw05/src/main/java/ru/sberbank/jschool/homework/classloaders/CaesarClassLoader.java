package main.java.ru.sberbank.jschool.homework.classloaders;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CaesarClassLoader extends ClassLoader {

    private final String rootDirectory;

    private final int offset;

    public CaesarClassLoader(ClassLoader parent, String rootDirectory, int offset) {
        super(parent);
        this.rootDirectory = rootDirectory;
        this.offset = offset;
    }

    CaesarClassLoader(String rootDirectory, int offset) {
        this.rootDirectory = rootDirectory;
        this.offset = offset;
    }

    public void encodeClass(String fileName) {
        File file = new File(rootDirectory + "/" + fileName.concat(".class"));
        byte[] b;
        try(FileOutputStream fileWriter =
                    new FileOutputStream(rootDirectory + "/" + fileName.concat(".cz"))) {
            b = Files.readAllBytes(Paths.get(rootDirectory + "/" + fileName.concat(".class")));
            byte[] encoded = new byte[b.length];
            int counter = 0;
            for(byte bt: b){
                encoded[counter] = (byte) (b[counter++] + offset);
            }
            fileWriter.write(encoded);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String path = rootDirectory + "/" + name + ".cz";
        File file = new File(path);
        boolean exist = file.exists();//path itself is correct, since such file exists

        if (exist) {
            try {
                byte[] b = Files.readAllBytes(Paths.get(path));
                byte[] decoded = new byte[b.length];
                int counter = 0;
                for (byte bt : b) {
                    decoded[counter] = (byte) (b[counter++] - offset);
                }
                //name = PrintPlugin
                return defineClass(name, decoded, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return findSystemClass(name);
        }
    }
}
