/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.gw2.arcdpsdownloadergw2;

import java.io.IOException;

/**
 *
 * @author Jani Eriksson <https://github.com/jani-e>
 */
public class ArcdpsDownloaderGW2 {

    public static void main(String[] args) {
        try {
            if(!Configuration.getConfigFile().exists()){
                Configuration.save(Configuration.get());
            }
        } catch (IOException e) {
            System.out.println("Could not load or create configuration: " + e.toString());
        }

        UI ui = new UI("ArcDPSDownloader");
        ui.start();
        
    }

}
