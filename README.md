# arcdps_downloader_gw2
Small Java program to download latest Arcdps to Guild Wars 2
Fork of [jani-e/arcdps_downloader_gw2](https://github.com/jani-e/arcdps_downloader_gw2)

## Requirements
Java 8+
Guild Wars 2

## Changelog
More information [here](CHANGELOG.md)

# Installation
1. Install Java if not already installed. [https://www.java.com/](https://www.java.com/)
2. Download the JAR-File into a folder of your choice
3. Double-click the JAR. If needed, select Java to open the file with.

# Usage
1. Double-click the JAR.
1. The program will check the installed ArcDPS version and checks for updates.
4. When new updates are found, a new version will be downloaded.
5. The program will start the game afterwards.

# Configuration
The configuration is located in the same folder.

### directx.version
Change according to the direct x version used by the game. (See under Settings > UI. Is the legacy support enabled -> DirectX9)

Allowed values: 
- `9` for DirectX9
- `11` for DirectX11

### gw2.autostart
Change this to `true` when you want to start the game automatically, `false` otherwise.

### gw2.directory
Change this according to the location of your GW2 installation. This path should point to the folder containing the executable.