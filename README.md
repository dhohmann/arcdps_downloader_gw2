# arcdps_downloader_gw2
Small Java program to download latest Arcdps to Guild Wars 2


## Requirements
Java 8+

## Usage
Download the file and use it to start the game.
1. The program will check the installed ArcDPS version and checks for updates.
2. When new updates are found, a new version will be downloaded.
3. The program will start the game afterwards.

## Configuration
The configuration is located under your user directory in the folder ```arcdpsdownloadergw2```

### directx.version
Change according to the direct x version used by the game. (See under Settings > UI. Is the legacy support enabled -> DirectX9)

Allowed values: 
- `9` for DirectX9
- `11` for DirectX11

### gw2.autostart
Change this to `true` when you want to start the game automatically, `false` otherwise.

### gw2.directory
Change this according to the location of your GW2 installation. This path should point to the folder containing the executable.