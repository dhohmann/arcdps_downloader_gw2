package com.gw2.arcdpsdownloadergw2.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.PrintStream;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import com.gw2.arcdpsdownloadergw2.Utils;
import com.gw2.arcdpsdownloadergw2.action.Action;
import com.gw2.arcdpsdownloadergw2.action.Download;
import com.gw2.arcdpsdownloadergw2.action.Install;
import com.gw2.arcdpsdownloadergw2.action.StartGame;
import com.gw2.arcdpsdownloadergw2.action.UninstallArcDPS;

/**
 * Main window for the UI-based program.
 */
public class Window {

    private JFrame frame;
    private JDialog settingsDialog;
    private JDialog aboutDialog;

    public Window() {
        frame = new JFrame("ArcDPSDownloader GW2");
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(500, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menubar = buildMenu();
        frame.setJMenuBar(menubar);

        JTextArea output = new JTextArea();
        output.setPreferredSize(new java.awt.Dimension(500, 200));
        output.setEditable(false);
        output.setEnabled(false);
        output.setLineWrap(true);
        output.setBackground(Color.BLACK);

        JTextAreaOutputStream outputStream = new JTextAreaOutputStream(System.out, output);
        System.setOut(new PrintStream(outputStream));

        frame.add(output, BorderLayout.CENTER);
    }

    private JMenuBar buildMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu actions = new JMenu("Actions");
        JMenuItem download = new JMenuItem("Download");
        download.setToolTipText("Downloads ArcDPS");
        download.addActionListener((e) -> performAction(new Download()));
        actions.add(download);

        JMenuItem startGame = new JMenuItem("Start GW2");
        startGame.addActionListener((e) -> performAction(new StartGame()));
        actions.add(startGame);

        JMenuItem uninstall = new JMenuItem("Uninstall ArcDPS");
        uninstall.addActionListener((e) -> performAction(new UninstallArcDPS()));
        actions.add(uninstall);
        fileMenu.add(actions);

        JMenuItem settings = new JMenuItem("Settings");
        settings.addActionListener((e) -> showSettings());
        fileMenu.add(settings);

        JMenuItem close = new JMenuItem("Close");
        close.addActionListener((e) -> close());
        fileMenu.add(close);

        menubar.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");

        JMenuItem troubleShoot = new JMenuItem("Troubleshoot");
        helpMenu.add(troubleShoot);

        JMenuItem about = new JMenuItem("About");
        about.addActionListener((e) -> showAbout());
        helpMenu.add(about);

        menubar.add(helpMenu);
        return menubar;
    }

    public void showSettings() {
        if (settingsDialog == null) {
            settingsDialog = new Settings(frame, true);
        }
        settingsDialog.setVisible(true);
    }

    public void showAbout() {
        if (aboutDialog == null) {
            aboutDialog = new About(frame, true);
        }
        aboutDialog.setVisible(true);
    }

    public void show() {
        frame.setVisible(true);

        if (Utils.getGW2Location() != null) {
            performAction(new Install());
            performAction(new Download());
            performAction(new StartGame());
        } else {
            System.out.println("Please open settings to specify game directory.");
        }

    }

    public void close() {
        frame.dispose();
        settingsDialog.dispose();
        aboutDialog.dispose();

        frame = null;
        settingsDialog = null;
        aboutDialog = null;
    }

    private void performAction(Action action) {
        action.execute();
    }


    public static void main(String[] args) {
        new Window().show();
    }
}
