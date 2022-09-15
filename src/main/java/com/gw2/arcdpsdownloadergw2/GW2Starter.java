package com.gw2.arcdpsdownloadergw2;

import java.io.File;
import java.io.IOException;

public class GW2Starter {

    private File gameFile;

    public GW2Starter() {
        this.gameFile = new File(FileHandler.getGW2Location(), "Gw2-64.exe");
    }

    public void start() {
        if (!gameFile.exists()) {
            System.out.println("Could not start game: Game not found");
            return;
        }

        String executablePath = gameFile.toPath().toAbsolutePath().toString();

        try {
            System.out.println("Starting game...");
            System.out.println("This window will close after 15 seconds.");
            Runtime.getRuntime().exec(executablePath);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
        }
        System.exit(0);
    }

}
