/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gw2.arcdpsdownloadergw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
 * Downloader for ArcDPS.
 * 
 * @author Jani Eriksson <https://github.com/jani-e>
 */
public class Downloader {

    private URL url;
    private String filePath;

    private InputStream getStream(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.addRequestProperty("User-Agent", "java");
        urlConnection.setReadTimeout(5000);
        urlConnection.setConnectTimeout(5000);
        urlConnection.connect();
        return urlConnection.getInputStream();
    }

    public Downloader() {
        try {
            this.url = new URL("https://www.deltaconnected.com/arcdps/x64/d3d11.dll");
            this.filePath = Utils.getArcDPSLocation(ArcdpsDownloaderGW2.getConfig().getDirectXVersion())
                    .getAbsolutePath();

        } catch (MalformedURLException | FileNotFoundException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean downloadFile() {
        try {
            System.out.println(String.format("Using DirectX%d version. If needed, change it in configuration.",
                    ArcdpsDownloaderGW2.getConfig().getDirectXVersion()));

            InputStream inputStream = getStream(this.url);
            Files.copy(inputStream, Paths.get(this.filePath), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Calculates the last modification date of ArcDPS.
     * 
     * @return {@code null}, if the date could not be retrieved, the date otherwise.
     */
    public Date getLastModification() {
        try {
            StringBuilder output = new StringBuilder();
            URL url = new URL("https://www.deltaconnected.com/arcdps/x64/");

            InputStreamReader reader = new InputStreamReader(getStream(url));
            char[] buffer = new char[256];
            int length = -1;
            while ((length = reader.read(buffer)) > 0) {
                output.append(buffer, 0, length);
            }
            reader.close();
            int i = output.indexOf("<a href=\"d3d11.dll\">d3d11.dll</a>");
            i = output.indexOf("indexcollastmod\">", i) + 17;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String p = output.substring(i, output.indexOf("</td>", i));
            return format.parse(p);
        } catch (IOException ex) {
            System.out.println("Could not retrieve last modification date: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Could not parse last modification date: " + ex.getMessage());
        }
        return null;
    }

    /**
     * Checks, if an update of ArcDPS is available.
     * 
     * @return {@code true}, if an update is found.
     */
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
            System.out.println("sdgsdgdsdgsdgsgd");
            return false;
        }
        return currentVersion.before(updateVersion);
    }

    public static void main(String[] args) {
        System.out.println(new Downloader().checkUpdateAvailable());
    }
}
