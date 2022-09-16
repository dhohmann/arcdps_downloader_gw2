package com.gw2.arcdpsdownloadergw2.console;

import java.util.Scanner;

import com.gw2.arcdpsdownloadergw2.ArcdpsDownloaderGW2;
import com.gw2.arcdpsdownloadergw2.FileHandler;

/**
 * Console application.
 * 
 * @author Jani Eriksson <https://github.com/jani-e>
 */
public class Console {

    private Scanner scanner;
    private FileHandler fileHandler;

    public Console() {
        scanner = new Scanner(System.in);
        fileHandler = ArcdpsDownloaderGW2.getFileHandler();
    }

    public void start() {
        while (true) {
            System.out.println("\n--------\nOptions\n--------");
            System.out.println("1 - Download latest Arcdps");
            System.out.println("2 - Remove Arcdps");
            System.out.println("0 - Close program");
            System.out.print("\nOption: ");
            int input = Integer.parseInt(this.scanner.nextLine());
            switch (input) {
                case 1:
                    this.fileHandler.download();
                    break;
                case 2:
                    this.fileHandler.removeFile();
                    break;
                default:
                    return;
            }
        }
    }
}
