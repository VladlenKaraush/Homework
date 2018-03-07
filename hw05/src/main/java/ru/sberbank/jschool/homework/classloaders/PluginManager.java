package main.java.ru.sberbank.jschool.homework.classloaders;

public class PluginManager {

    // directory that contains plugin folders
    private final String rootDirectory;

    public PluginManager(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    /**
     * method takes as a parameter a folder name in the root plugin directory,
     * loads the plugin .class file from the folder if present,
     * and returns a Plugin object
     *
     * @param pluginName - name of the plugin folder
     * @return Plugin
     * @throws PluginNotFoundException - when folder named 'pluginName' is missing,
     *                                   or it contains no .class files
     */
    public Plugin loadPlugin(String pluginName) throws PluginNotFoundException {
        //TODO implement

        CustomClassLoader loader = new CustomClassLoader(rootDirectory);
        try {
            Class<?> c = loader.loadClass(pluginName);
            return (Plugin) c.newInstance();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new PluginNotFoundException("couldn't locate plugin " + pluginName);
        }
    }


    public Plugin loadPlugin(String pluginName, int offset) throws PluginNotFoundException {
        CaesarClassLoader loader = new CaesarClassLoader(rootDirectory, offset);
        try {
            Class<?> c = loader.loadClass(pluginName);
            return (Plugin) c.newInstance();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new PluginNotFoundException("couldn't locate plugin " + pluginName);
        }
    }
}
