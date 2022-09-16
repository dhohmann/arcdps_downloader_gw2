/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gw2.arcdpsdownloadergw2;

import java.io.File;

/**
 * File handler for ArcDPS files.
 * 
 * @author Jani Eriksson <https://github.com/jani-e>
 */
public class FileHandler {
    private Downloader downloader;

    public FileHandler() {
        this.downloader = new Downloader();
    }

    public void removeFile() {
        File arcdps = new File("C:/Program Files/Guild Wars 2/d3d11.dll");
        if (arcdps.delete()) {
            System.out.println("File removed successfully.");
        } else {
            System.out.println("Error in removing.");
        }
    }

    /**
     * Checks before an update, if there is a new version.
     */
    public void downloadIfNeeded() {
        System.out.println("Checking for new updates...");
        if (downloader.checkUpdateAvailable()) {
            System.out.println("Found update. Starting download...");
            download();
        } else {
            System.out.println("No update available.");
        }

    }

    /**
     * Downloads the new version.
     */
    public void download() {
        if (this.downloader.downloadFile()) {
            System.out.println("Download successful");
        } else {
            System.out.println("Error in downloading");
        }
    }

}
