# arcdps_downloader_gw2
Small Java program to download latest Arcdps to Guild Wars 2

Fork of [jani-e/arcdps_downloader_gw2](https://github.com/jani-e/arcdps_downloader_gw2) that provides the same functionality as a console application.

## Requirements
Guild Wars 2

## Changelog
More information [here](CHANGELOG.md)

## Running with Java installed
When you already have Java installed on your system, you can download the JAR file and start the program by double-clicking the JAR.

## Running without Java installed
The ZIP file contains a Java runtime so that you do not need to install an additional program.
1. Download the ZIP file
2. Extract the contents to a folder
3. Execute the .exe file


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
