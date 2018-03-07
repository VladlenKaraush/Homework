package main.java.ru.sberbank.jschool.homework.classloaders;

public class Main {
    public static void main(String[] args) {

        final String rootDir = "hw05/src/main/java/ru/sberbank/jschool/homework/classloaders/plugins";
        final int offset = 3;
        final String pluginName = "PrintPlugin";

        PluginManager pluginManager = new PluginManager(rootDir);
        //czLoader.encodeClass("PrintPlugin");

        Class<?> c = null;
        try {
            Plugin plugin = pluginManager.loadPlugin(pluginName);
            plugin.run(new String[0]);

            plugin = pluginManager.loadPlugin(pluginName, offset);
            plugin.run(new String[0]);

        } catch (PluginNotFoundException e) {
            e.printStackTrace();
        }
    }
}
