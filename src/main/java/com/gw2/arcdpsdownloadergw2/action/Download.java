package com.gw2.arcdpsdownloadergw2.action;

import com.gw2.arcdpsdownloadergw2.Downloader;

/**
 * Performs all steps for the download.
 */
public class Download implements Action {

    @Override
    public void execute() {
        try {
            Downloader downloader = new Downloader();
            System.out.println("Checking for updates...");
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
            System.out.println("Error during download" + e.getMessage());
        }

    }

}
