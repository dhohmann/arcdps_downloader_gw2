package com.gw2.arcdpsdownloadergw2.action.impl;

import java.io.File;

import com.gw2.arcdpsdownloadergw2.Configuration;
import com.gw2.arcdpsdownloadergw2.Utils;
import com.gw2.arcdpsdownloadergw2.action.Action;

/**
 * Performs all steps to remove the ArcDPS files from the system.
 */
public class UninstallArcDPS implements Action {

    @Override
    public void execute() {
        try {
            uninstall(Utils.getArcDPSLocation(Configuration.DIRECTX9));
            uninstall(Utils.getArcDPSLocation(Configuration.DIRECTX11));
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    private void uninstall(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        System.out.println("Uninstalling file " + file + "...");
        if (file.delete()) {
            System.out.println("Uninstalled successfully.");
        }
    }

}
