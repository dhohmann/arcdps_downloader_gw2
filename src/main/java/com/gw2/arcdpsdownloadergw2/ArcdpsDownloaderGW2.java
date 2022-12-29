/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.gw2.arcdpsdownloadergw2;

import javax.swing.UIManager;

import com.gw2.arcdpsdownloadergw2.action.ActionManager;
import com.gw2.arcdpsdownloadergw2.ui.Window;

/**
 * Main class.
 * 
 * @author Jani Eriksson <https://github.com/jani-e>
 */
public class ArcdpsDownloaderGW2 {

    private static Configuration configuration = new Configuration();
    private static ActionManager actionManager = new ActionManager();
    private static Window window = new Window();

    /**
     * Retrieves the configuration of the program.
     * 
     * @return Configuration
     */
    public static Configuration getConfig() {
        return configuration;
    }

    /**
     * Retrieves the actionmanager of the program.
     * 
     * @return the actionManager
     */
    public static ActionManager getActionManager() {
        return actionManager;
    }

    /**
     * @return the window
     */
    public static Window getWindow() {
        return window;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        window.show();
    }

}
