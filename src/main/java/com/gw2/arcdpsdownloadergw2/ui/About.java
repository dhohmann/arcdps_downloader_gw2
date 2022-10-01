package com.gw2.arcdpsdownloadergw2.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gw2.arcdpsdownloadergw2.Utils;

/**
 * About dialog with authorship and version info.
 */
public class About extends JDialog {

    private static final String[] AUTHORS = new String[] {
            "Jani Eriksson",
            "Daniel Hohmann"
    };

    public About(JFrame frame, boolean modal) {
        super(frame, "About", modal);
        setMinimumSize(new Dimension(200, 100));
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(3, 1));
        JLabel authorTitle = new JLabel("AUTHORS");
        authorTitle.setFont(new Font("Default", Font.BOLD, 16));
        content.add(authorTitle);
        JPanel authors = new JPanel(new GridLayout(2, 1));
        for (String author : AUTHORS) {
            JLabel a = new JLabel(author);
            a.setFont(new Font("Default", Font.BOLD, 16));
            authors.add(a);
        }
        content.add(authors);
        content.add(new JLabel("Version: " + Utils.getVersion()));
        add(content);
    }
}
