/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gw2.arcdpsdownloadergw2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jani Eriksson <https://github.com/jani-e>
 */
public class Downloader {

    private URL url;
    private String filePath;

    public Downloader() {
        try {
            this.url = new URL("https://www.deltaconnected.com/arcdps/x64/d3d11.dll");
            switch (Configuration.get().getDirectXVersion()) {
                case 9:
                    this.filePath = "C:/Program Files/Guild Wars 2/bin64/d3d9.dll";
                    break;
                default:
                    this.filePath = "C:/Program Files/Guild Wars 2/d3d11.dll";
                    break;
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean downloadFile() {
        try {
            System.out.println(String.format("Using DirectX%d version. If needed, change it in configuration.",
                    Configuration.get().getDirectXVersion()));

            InputStream inputStream = this.url.openStream();
            Files.copy(inputStream, Paths.get(this.filePath), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Date getLastModification() {
        try {
            StringBuilder output = new StringBuilder();
            URL url = new URL("https://www.deltaconnected.com/arcdps/x64/");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            char[] buffer = new char[256];
            int length = -1;
            while ((length = reader.read(buffer)) > 0) {
                output.append(buffer, 0, length);
            }
            reader.close();
            int i = output.indexOf("<a href=\"d3d11.dll\">d3d11.dll</a>");
            i = output.indexOf("indexcollastmod\">", i) + 17;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return format.parse(output.substring(i, output.indexOf("</td>", i)));
        } catch (IOException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, "Could not retrieve last modification date",
                    ex);
        } catch (ParseException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, "Could not parse last modification date",
                    ex);
        }
        return null;
    }

    public boolean checkUpdateAvailable() {
        Date currentVersion = null;
        File file = new File(filePath);
        if (!file.exists()) {
            return true;
        }
        try {
            FileTime time = Files.getLastModifiedTime(file.toPath());
            currentVersion = new Date(time.to(TimeUnit.MILLISECONDS));
        } catch (IOException e) {

        }
        Date updateVersion = getLastModification();
        if (updateVersion == null) {
            return false;
        }
        return currentVersion.before(updateVersion);
    }
}
