package com.gw2.arcdpsdownloadergw2.action.impl;

import com.gw2.arcdpsdownloadergw2.Downloader;
import com.gw2.arcdpsdownloadergw2.Utils;
import com.gw2.arcdpsdownloadergw2.action.Action;

/**
 * Performs all steps for the download.
 */
public class Download implements Action {

    @Override
    public void execute() {
        if(!Utils.isValidGW2Location(Utils.getGW2Executable())){
            System.out.println("Please open settings to specify game directory.");
            return;
        }
        try {
            Downloader downloader = new Downloader();
            System.out.print("Checking for updates... ");
            if (downloader.checkUpdateAvailable()) {
                System.out.println("Update found.");
                if (downloader.downloadFile()) {
                    System.out.println("Download successful.");
                } else {
                    System.out.println("Download failed.");
                }
            } else {
                System.out.println("No update.");
            }
        } catch (Throwable e) {
            System.out.println("Error during download " + e.toString());
        }

    }

}
