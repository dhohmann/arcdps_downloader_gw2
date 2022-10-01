package com.gw2.arcdpsdownloadergw2.action.impl;

import java.io.File;
import java.io.IOException;

import com.gw2.arcdpsdownloadergw2.Utils;
import com.gw2.arcdpsdownloadergw2.action.Action;

/**
 * Performs all steps to start GW2.
 */
public class StartGame implements Action {

    @Override
    public void execute() {
        File gameFile = Utils.getGW2Executable();
        if (gameFile == null || !gameFile.exists()) {
            System.out.println("Could not start game: " + "Please open settings to specify game directory.");
            return;
        }

        String executablePath = gameFile.toPath().toAbsolutePath().toString();
        try {
            System.out.println("Starting game...");
            Runtime.getRuntime().exec(executablePath);
        } catch (IOException e) {
            System.out.println("Could not start game: " + e.getMessage());
        }
    }

}
