package com.gw2.arcdpsdownloadergw2.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gw2.arcdpsdownloadergw2.Utils;
import com.gw2.arcdpsdownloadergw2.ui.components.JLink;

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
        getContentPane().setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(150, 200));
        JPanel content = new JPanel();
        BoxLayout layout = new BoxLayout(content, BoxLayout.Y_AXIS);
        content.setLayout(layout);

        JLabel authorTitle = new JLabel("Authors");
        authorTitle.setFont(new Font("Default", Font.BOLD, 12));
        authorTitle.setAlignmentX(Box.CENTER_ALIGNMENT);
        content.add(authorTitle);
        for (String author : AUTHORS) {
            JLabel a = new JLabel(author);
            a.setFont(new Font("Default", Font.PLAIN, 12));
            a.setAlignmentX(Box.CENTER_ALIGNMENT);
            content.add(a);
        }
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel versionTitle = new JLabel("Version");
        versionTitle.setFont(new Font("Default", Font.BOLD, 12));
        versionTitle.setAlignmentX(Box.CENTER_ALIGNMENT);
        content.add(versionTitle);
        JLabel version = new JLabel(Utils.getVersion());
        version.setFont(new Font("Default", Font.PLAIN, 12));
        version.setAlignmentX(Box.CENTER_ALIGNMENT);
        content.add(version);

        content.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel link = new JLink(getGithubLogo(), "View on Github", "https://github.com/dhohmann/arcdps_downloader_gw2");
        link.setForeground(Color.BLUE);
        link.setFont(new Font("Default", Font.PLAIN, 12));
        link.setAlignmentX(Box.CENTER_ALIGNMENT);
        content.add(link);
        add(content, new GridBagConstraints());
    }

    private Icon getGithubLogo() {
        URL url = getClass().getResource("/assets/github-mark.png");
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            if (icon.getImage().getHeight(this) > getFont().getSize()) {
                
                return new ImageIcon(icon.getImage().getScaledInstance(getFont().getSize(), getFont().getSize(), java.awt.Image.SCALE_SMOOTH));
            }
            return icon;
        }

        return null;
    }
}
