package com.gw2.arcdpsdownloadergw2.action.impl;

import com.gw2.arcdpsdownloadergw2.ArcdpsDownloaderGW2;
import com.gw2.arcdpsdownloadergw2.Configuration;
import com.gw2.arcdpsdownloadergw2.action.Action;

/**
 * Performs all steps for the setup of the program.
 */
public class Install implements Action {

    @Override
    public void execute() {
        if (!Configuration.getConfigFile().exists()) {
            System.out.println("Setting up configuration.");
            ArcdpsDownloaderGW2.getConfig().save();
        } else {
        }
        
    }

}
