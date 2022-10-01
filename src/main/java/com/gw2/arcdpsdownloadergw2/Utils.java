package com.gw2.arcdpsdownloadergw2;

import java.io.File;

public class Utils {
    private Utils() {

    }

    /**
     * Tries to identify the GW2 location on the system.
     * 
     * @return {@code null}, if the program could not be found, the file otherwise
     * @implNote Is not dynamic right now.
     */
    public static File getGW2Location() {
        File folder = new File(ArcdpsDownloaderGW2.getConfig().getGW2Path());
        if (folder.exists() && new File(folder, "Gw2-64.exe").exists()) {
            return folder;
        }
        return null;
    }
}
