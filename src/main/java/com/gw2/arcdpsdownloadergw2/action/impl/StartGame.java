package com.gw2.arcdpsdownloadergw2.action.impl;

import java.io.File;

import com.gw2.arcdpsdownloadergw2.Utils;
import com.gw2.arcdpsdownloadergw2.action.Action;

/**
 * Performs all steps to start GW2.
 */
public class StartGame implements Action {

    private static final String CLI_PARAM_AUTOLOGIN = "-autologin";

    private static Thread gameThread = null;

    @Override
    public void execute() {
        File gameFile = Utils.getGW2Executable();
        if (gameFile == null || !gameFile.exists()) {
            System.out.println("Could not start game: " + "Please open settings to specify game directory.");
            return;
        }
        if (gameThread != null) {
            System.out.println("Game is already starting or running.");
            return;
        }
        String executablePath = gameFile.toPath().toAbsolutePath().toString();
        ProcessBuilder processBuilder = new ProcessBuilder(executablePath, CLI_PARAM_AUTOLOGIN);

        gameThread = new Thread(() -> {
            try {
                System.out.println("Starting game...");
                Process process = processBuilder.start();
                int result = process.waitFor();
                if (result == 0) {
                    System.out.println("Game closed.");
                } else {
                    System.out.println("Game closed unexpected. Code: " + result);
                }
            } catch (Exception e) {
                System.out.println("Could not start game: " + e.getMessage());
            } catch (Throwable e) {
                System.out.println("An error occured: " + e.getMessage());
            }
            gameThread = null;
        });
        gameThread.start();

    }

}
