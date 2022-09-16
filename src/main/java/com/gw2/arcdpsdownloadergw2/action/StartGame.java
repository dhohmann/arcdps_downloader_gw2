package com.gw2.arcdpsdownloadergw2.action;

import java.io.File;
import java.io.IOException;

import com.gw2.arcdpsdownloadergw2.Utils;

/**
 * Performs all steps to start GW2.
 */
public class StartGame implements Action {

    @Override
    public void execute() {
        File gameFile = new File(Utils.getGW2Location(), "Gw2-64.exe");
        if (!gameFile.exists()) {
            System.out.println("Could not start game: Game not found");
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
