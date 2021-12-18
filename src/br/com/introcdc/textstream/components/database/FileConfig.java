package br.com.introcdc.textstream.components.database;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/11/2021 - 23:58
 */

import br.com.introcdc.textstream.components.loader.LoaderComponents;
import br.com.introcdc.textstream.components.login.LoginComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileConfig {

    private final Map<String, String> objects = new HashMap<>();

    private boolean loaded = false;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public Map<String, String> getObjects() {
        return objects;
    }

    public void put(String key, String value) {
        getObjects().put(key, value);
    }

    public boolean has(String key) {
        return getObjects().containsKey(key);
    }

    public String get(String key) {
        return getObjects().get(key);
    }

    public String get(String key, String defaultValue) {
        return getObjects().getOrDefault(key, defaultValue);
    }

    public void load(File file) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(file));
            getObjects().clear();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String key = line.split(":", 2)[0];
                String value = line.split(":", 2)[1];
                getObjects().put(LoginComponents.aesToString(key, LoaderComponents.password()),
                        LoginComponents.aesToString(value, LoaderComponents.password()));
            }
            scanner.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        setLoaded(true);
    }

    public void save(File file) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            for (String key : getObjects().keySet()) {
                printWriter.println(LoginComponents.stringToAes(key, LoaderComponents.password()) + ":" +
                        LoginComponents.stringToAes(getObjects().get(key), LoaderComponents.password()));
            }
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

}
