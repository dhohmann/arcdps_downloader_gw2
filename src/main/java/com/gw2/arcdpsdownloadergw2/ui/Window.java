package com.gw2.arcdpsdownloadergw2.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.gw2.arcdpsdownloadergw2.ArcdpsDownloaderGW2;
import com.gw2.arcdpsdownloadergw2.Utils;
import com.gw2.arcdpsdownloadergw2.ui.components.JTextAreaOutputStream;

/**
 * Main window for the UI-based program.
 */
public class Window {

    private JFrame frame;
    private JDialog aboutDialog;

    public Window() {
        frame = new JFrame("ArcDPSDownloader GW2");
        frame.setIconImage(new ImageIcon(getClass().getResource("/assets/download.png")).getImage());
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(500, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menubar = buildMenu();
        frame.setJMenuBar(menubar);

        JTextArea output = new JTextArea();
        output.setPreferredSize(new java.awt.Dimension(500, 200));
        output.setEditable(false);
        output.setEnabled(false);
        output.setDisabledTextColor(Color.WHITE);
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
        download.addActionListener((e) -> performAction("Download"));
        actions.add(download);

        JMenuItem startGame = new JMenuItem("Start GW2");
        startGame.addActionListener((e) -> performAction("StartGame"));
        actions.add(startGame);

        JMenuItem uninstall = new JMenuItem("Uninstall ArcDPS");
        uninstall.addActionListener((e) -> performAction("UninstallArcDPS"));
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
        troubleShoot.addActionListener((e)->{
            JOptionPane.showMessageDialog(frame, "Not implemented");
        });
        helpMenu.add(troubleShoot);

        JMenuItem about = new JMenuItem("About");
        about.addActionListener((e) -> showAbout());
        helpMenu.add(about);

        menubar.add(helpMenu);
        return menubar;
    }

    public void showSettings() {
        new Settings(frame, true).setVisible(true);
    }

    public void showAbout() {
        if (aboutDialog == null) {
            aboutDialog = new About(frame, true);
            aboutDialog.setLocationRelativeTo(frame);
        }
        aboutDialog.setVisible(true);
    }

    public void show() {
        frame.setVisible(true);

        if (Utils.getGW2Executable() != null) {
            ArcdpsDownloaderGW2.getActionManager().execute("Install", "Download", "StartGame");
        } else {
            System.out.println("Please open settings to specify game directory.");
        }

    }

    public void close() {
        frame.dispose();
        if (aboutDialog != null) {
            aboutDialog.dispose();
        }

        frame = null;
        aboutDialog = null;
    }

    private void performAction(String action) {
        ArcdpsDownloaderGW2.getActionManager().execute(action);
    }
}
