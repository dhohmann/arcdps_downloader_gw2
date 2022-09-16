package com.gw2.arcdpsdownloadergw2.action;

import java.io.File;

import com.gw2.arcdpsdownloadergw2.Utils;

/**
 * Performs all steps to remove the ArcDPS files from the system.
 */
public class UninstallArcDPS implements Action {

    @Override
    public void execute() {
        uninstallDirectX9();
        uninstallDirectX11();
    }

    private void uninstallDirectX9() {
        File file = new File(Utils.getGW2Location(), "bin64/d3d9.dll");
        if (file.exists()) {
            System.out.println("Uninstalling DirectX9 version...");
            if (file.delete()) {
                System.out.println("Uninstalled successfully.");
            } else {
                System.out.println("Could not remove file.");
            }
        }
    }

    private void uninstallDirectX11() {
        File file = new File(Utils.getGW2Location(), "d3d11.dll");
        if (file.exists()) {
            System.out.println("Uninstalling DirectX11 version...");
            if (file.delete()) {
                System.out.println("Uninstalled successfully.");
            } else {
                System.out.println("Could not remove file.");
            }
        }
    }

}
