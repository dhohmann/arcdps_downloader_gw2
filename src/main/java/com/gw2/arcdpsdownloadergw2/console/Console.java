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
    private String[] args;

    public Console() {
        this(new String[] {});
    }

    public Console(String[] args) {
        this.scanner = new Scanner(System.in);
        this.fileHandler = ArcdpsDownloaderGW2.getFileHandler();
        this.args = args;
    }

    public void start() {
        if (args.length == 0) {
            runInteractive();
            return;
        }
        if (checkOption("download")) {
            this.fileHandler.download();
            return;
        }
        if (checkOption("remove")) {
            this.fileHandler.removeFile();
            return;
        }
        System.out.println(
                "Unknown option! \nOptions: \n\tdownload: Downloads the latest Arcdps\n\tremove: Removes Arcdps");
    }

    private boolean checkOption(String option) {
        if (option != null) {
            for (String string : args) {
                if (string.toLowerCase().equals(option.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void runInteractive() {
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
