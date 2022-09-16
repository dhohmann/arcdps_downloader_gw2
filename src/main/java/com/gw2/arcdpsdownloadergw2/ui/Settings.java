package com.gw2.arcdpsdownloadergw2.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.gw2.arcdpsdownloadergw2.ArcdpsDownloaderGW2;
import com.gw2.arcdpsdownloadergw2.Utils;

/**
 * Settings dialog to modify the configuration for the program.
 */
public class Settings extends JDialog {

    public static final int PADDING_INNER = 15;

    public Settings(JFrame parent, boolean modal) {
        super(parent, "Settings", modal);
        setMinimumSize(new Dimension(500, 400));
        setResizable(false);

        buildComponents();
        this.pack();
    }

    private final void buildComponents() {
        SpringLayout layout = new SpringLayout();
        JPanel content = new JPanel(layout);

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("Default", Font.BOLD, 15));
        layout.putConstraint(SpringLayout.WEST, title, PADDING_INNER, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, title, PADDING_INNER, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.EAST, title, -PADDING_INNER, SpringLayout.EAST, content);
        content.add(title);

        JLabel directXLabel = new JLabel("DirectX version");
        layout.putConstraint(SpringLayout.WEST, directXLabel, PADDING_INNER, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, directXLabel, 15, SpringLayout.SOUTH, title);
        content.add(directXLabel);

        JComboBox<Integer> directXSelect = new JComboBox<>(new Integer[] { 9, 11 });
        layout.putConstraint(SpringLayout.WEST, directXSelect, 5, SpringLayout.EAST, directXLabel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, directXSelect, 0, SpringLayout.VERTICAL_CENTER,
                directXLabel);
        layout.putConstraint(SpringLayout.EAST, directXSelect, -PADDING_INNER, SpringLayout.EAST,
                content);
        content.add(directXSelect);

        directXSelect.setSelectedItem(ArcdpsDownloaderGW2.getConfig().getDirectXVersion());

        JLabel gamePathLabel = new JLabel("Game directory");
        layout.putConstraint(SpringLayout.WEST, gamePathLabel, PADDING_INNER, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, gamePathLabel, 15, SpringLayout.SOUTH, directXLabel);
        content.add(gamePathLabel);

        JPanel gamePathSelection = new JPanel(new BorderLayout());
        JTextField gamePathValue = new JTextField(50);
        if (Utils.getGW2Location() != null) {
            gamePathValue.setText(Utils.getGW2Location().getAbsolutePath());
        }

        JButton gamePathOpen = new JButton("Open");
        gamePathOpen.addActionListener((e) -> {
            JFileChooser chooser = new JFileChooser();
            if (gamePathValue.getText() != null && gamePathValue.getText().isEmpty()) {
                chooser.setSelectedFile(new File(gamePathValue.getText()));
            }
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int action = chooser.showOpenDialog(this);
            if (action == JFileChooser.APPROVE_OPTION) {
                gamePathValue.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });

        gamePathSelection.add(gamePathValue, BorderLayout.CENTER);
        gamePathSelection.add(gamePathOpen, BorderLayout.EAST);
        layout.putConstraint(SpringLayout.WEST, gamePathSelection, 5, SpringLayout.EAST, gamePathLabel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, gamePathSelection, 0, SpringLayout.VERTICAL_CENTER,
                gamePathLabel);
        layout.putConstraint(SpringLayout.EAST, gamePathSelection, -PADDING_INNER, SpringLayout.EAST, content);
        content.add(gamePathSelection);

        JButton accept = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        JPanel confirmButtons = new JPanel(new GridLayout(1, 2));
        layout.putConstraint(SpringLayout.WEST, confirmButtons, PADDING_INNER, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.EAST, confirmButtons, -PADDING_INNER, SpringLayout.EAST, content);
        layout.putConstraint(SpringLayout.SOUTH, confirmButtons, -PADDING_INNER, SpringLayout.SOUTH, content);

        confirmButtons.add(accept, BorderLayout.WEST);
        confirmButtons.add(cancel, BorderLayout.EAST);

        content.add(confirmButtons);

        this.add(new JScrollPane(content));
    }

}
