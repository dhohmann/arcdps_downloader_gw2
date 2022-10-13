package com.gw2.arcdpsdownloadergw2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    private static final String DIRECTX9_PATH = "bin64/d3d9.dll";
    private static final String DIRECTX11_PATH = "d3d11.dll";
    private static String version;

    private Utils() {

    }

    /**
     * Tries to identify the GW2 location on the system.
     * 
     * @return {@code null}, if the program could not be found, the file otherwise
     * @implNote uses the first executable that is found and contains the phrase
     *           <code>gw</code>.
     */
    public static File getGW2Executable() {
        File folder = new File(ArcdpsDownloaderGW2.getConfig().getGW2Path());
        if (!folder.exists()) {
            return null;
        }
        return getExecutablesInFolder(folder);
    }

    private static File getExecutablesInFolder(File folder) {
        File[] executeables = folder.listFiles((f, n) -> {
            if (n.endsWith(".exe")) {
                return true;
            }
            return false;
        });

        for (File file : executeables) {
            if (file.getName().toLowerCase().contains("gw")) {
                return file;
            }
        }
        return null;
    }

    public static boolean isValidGW2Location(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            return getExecutablesInFolder(file) != null;
        }
        return file.getName().toLowerCase().contains("gw");
    }

    /**
     * Retrieves the arcdps location for the specified DirectX version.
     * 
     * @param directX Version of DirectX
     * @return File to install ArcDPS to
     * @throws FileNotFoundException when the GW2 directory is not specified
     */
    public static File getArcDPSLocation(int directX) throws FileNotFoundException {
        if (getGW2Executable() == null) {
            throw new FileNotFoundException("Could not locate GW2 directory");
        }
        switch (directX) {
            case 9:
                return new File(getGW2Executable().getParentFile(), DIRECTX9_PATH);
            default:
                return new File(getGW2Executable().getParentFile(), DIRECTX11_PATH);
        }
    }

    /**
     * Retrieves the version of the downloader.
     * 
     * @return Version string if it could be retrieved, <code>undefined</code>
     *         otherwise
     */
    public static String getVersion() {
        if (version == null) {
            InputStream s = ArcdpsDownloaderGW2.class.getResourceAsStream("/version.txt");
            InputStreamReader reader = new InputStreamReader(s);
            StringBuilder data = new StringBuilder();
            try {
                int length = -1;
                char[] cbuf = new char[20];
                while ((length = reader.read(cbuf)) != -1) {
                    data.append(cbuf, 0, length);
                }
                reader.close();
            } catch (IOException e) {
            }
            if (data.length() == 0) {
                return "undefined";
            } else {
                version = data.toString();
            }
        }
        return version;
    }

    public static class PackageUtils {

        public static Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
            InputStream stream = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(packageName.replaceAll("[.]", "/"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            return reader.lines()
                    .filter(line -> line.endsWith(".class"))
                    .map(line -> getClass(line, packageName))
                    .collect(Collectors.toSet());
        }

        private static Class<?> getClass(String className, String packageName) {
            try {
                return Class.forName(packageName + "."
                        + className.substring(0, className.lastIndexOf('.')));
            } catch (ClassNotFoundException e) {
                // handle the exception
            }
            return null;
        }
    }
}
