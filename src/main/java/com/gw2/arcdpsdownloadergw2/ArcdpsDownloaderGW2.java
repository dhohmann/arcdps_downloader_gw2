/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.gw2.arcdpsdownloadergw2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.gw2.arcdpsdownloadergw2.console.Console;
import com.gw2.arcdpsdownloadergw2.ui.Window;

/**
 * Main class.
 * 
 * @author Jani Eriksson <https://github.com/jani-e>
 */
public class ArcdpsDownloaderGW2 {

    private static Configuration configuration = new Configuration();
    private static FileHandler fileHandler = new FileHandler();
    private static String version = null;

    public static Configuration getConfig() {
        return configuration;
    }

    public static FileHandler getFileHandler() {
        return fileHandler;
    }

    public static void main(String[] args) {
        if (args.length > 0 && "ui".equalsIgnoreCase(args[0])) {
            Window window = new Window();
            window.show();
        } else {
            Console console = new Console();
            console.start();
        }

    }

    public static String getVersion() {
        if (version == null) {
            InputStream s = ArcdpsDownloaderGW2.class.getResourceAsStream("/version.txt");
            InputStreamReader reader = new InputStreamReader(s);
            StringBuilder data = new StringBuilder();
            try {
                int length = -1;
                char[] cbuf = new char[20];
                while ((length = reader.read(cbuf)) != -1) {
                    data.append(cbuf, 0, length);
                }
                reader.close();
            } catch (IOException e) {
            }
            if (data.length() == 0) {
                return "undefined";
            } else {
                version = data.toString();
            }
        }
        return version;
    }

}
