/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gw2.arcdpsdownloadergw2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Jani Eriksson <https://github.com/jani-e>
 */
public class UI {

    private Scanner scanner;
    private FileHandler fileHandler;
    private JFrame window;

    public UI() {
        this.scanner = new Scanner(System.in);
        this.fileHandler = new FileHandler();
    }

    public UI(String title) {
        this();
        this.window = new JFrame(title);
        window.setPreferredSize(new java.awt.Dimension(500, 200));
        window.setSize(new java.awt.Dimension(500, 200));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        JTextArea output = new JTextArea();
        output.setPreferredSize(new java.awt.Dimension(500, 200));
        output.setEditable(false);
        output.setEnabled(false);
        output.setLineWrap(true);
        output.setBackground(Color.BLACK);

        CustomOutputStream outputStream = new CustomOutputStream(output);
        System.setOut(new PrintStream(outputStream));

        window.add(output, BorderLayout.CENTER);
    }

    public void start() {
        if (window == null) {
            startConsole();
        } else {
            startUI();
        }
    }

    private void startUI() {
        window.setVisible(true);

        System.out.println("Locating GW2 installation...");
        File location = FileHandler.getGW2Location();
        if (location == null) {
            System.out.println("Could not locate installation. Please specify in configuration");
            return;
        }
        System.out.println("Using location " + location);
        fileHandler.downloadIfNeeded();

        GW2Starter starter = new GW2Starter();
        starter.start();
    }

    private void startConsole() {
        while (true) {
            System.out.println("\n--------\nConfiguration\n--------");
            System.out.println(Configuration.get().toString());

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

    private class CustomOutputStream extends OutputStream {
        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            // redirects data to the text area
            textArea.append(String.valueOf((char) b));
            // scrolls the text area to the end of data
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}
