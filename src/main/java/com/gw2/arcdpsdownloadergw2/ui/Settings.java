package com.gw2.arcdpsdownloadergw2.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileFilter;

import com.gw2.arcdpsdownloadergw2.ArcdpsDownloaderGW2;
import com.gw2.arcdpsdownloadergw2.Configuration;
import com.gw2.arcdpsdownloadergw2.Utils;

/**
 * Settings dialog to modify the configuration for the program.
 */
public class Settings extends JDialog {

    public static final int PADDING_INNER = 15;

    private DefaultComboBoxModel<Integer> directXModel = new DefaultComboBoxModel<>(new Integer[] { 9, 11 });
    private JTextField gamePathValue = null;
    private JCheckBox autostartCheckbox;

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
        directXLabel.setToolTipText("DirectX version your game runs on. To determine the version check under UI settings if the checkbox for 'legacy support' is enabled. If it is, you are using DirectX 9.");
        layout.putConstraint(SpringLayout.WEST, directXLabel, PADDING_INNER, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, directXLabel, 15, SpringLayout.SOUTH, title);
        content.add(directXLabel);

        JComboBox<Integer> directXSelect = new JComboBox<>();
        layout.putConstraint(SpringLayout.WEST, directXSelect, 5, SpringLayout.EAST, directXLabel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, directXSelect, 0, SpringLayout.VERTICAL_CENTER,
                directXLabel);
        layout.putConstraint(SpringLayout.EAST, directXSelect, -PADDING_INNER, SpringLayout.EAST,
                content);
        content.add(directXSelect);

        directXSelect.setModel(directXModel);
        directXModel.setSelectedItem(ArcdpsDownloaderGW2.getConfig().getDirectXVersion());

        JLabel gamePathLabel = new JLabel("Game executable");
        gamePathLabel.setToolTipText("Path to the executable for GW2");
        layout.putConstraint(SpringLayout.WEST, gamePathLabel, PADDING_INNER, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, gamePathLabel, 15, SpringLayout.SOUTH, directXLabel);
        content.add(gamePathLabel);

        JPanel gamePathSelection = new JPanel(new BorderLayout());
        gamePathValue = new JTextField(50);

        if (Utils.getGW2Executable() != null) {
            gamePathValue.setText(Utils.getGW2Executable().getAbsolutePath());
        }

        JButton gamePathOpen = new JButton("Open");
        gamePathOpen.addActionListener((e) -> {
            JFileChooser chooser = new JFileChooser();
            if (gamePathValue.getText() != null && !gamePathValue.getText().isEmpty()) {
                chooser.setCurrentDirectory(new File(gamePathValue.getText()));
            }
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setFileFilter(new FileFilter() {

                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().endsWith(".exe");
                }

                @Override
                public String getDescription() {
                    return "Application";
                }

            });
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

        JLabel autostartLabel = new JLabel("Autostart GW2");
        autostartLabel.setToolTipText("Starts GW2 automatically after the update process");

        layout.putConstraint(SpringLayout.WEST, autostartLabel, PADDING_INNER, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, autostartLabel, 15, SpringLayout.SOUTH, gamePathLabel);
        content.add(autostartLabel);

        autostartCheckbox = new JCheckBox("Autostart");
        autostartCheckbox.setSelected(ArcdpsDownloaderGW2.getConfig().getAutostart());
        layout.putConstraint(SpringLayout.WEST, autostartCheckbox, 5, SpringLayout.EAST, autostartLabel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, autostartCheckbox, 0, SpringLayout.VERTICAL_CENTER,
                autostartLabel);
        layout.putConstraint(SpringLayout.EAST, autostartCheckbox, -PADDING_INNER, SpringLayout.EAST,
                content);
        content.add(autostartCheckbox);

        JButton accept = new JButton("Save");
        accept.addActionListener((e) -> this.onSave());
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener((e) -> this.close());
        JPanel confirmButtons = new JPanel(new GridLayout(1, 2));
        layout.putConstraint(SpringLayout.WEST, confirmButtons, PADDING_INNER, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.EAST, confirmButtons, -PADDING_INNER, SpringLayout.EAST, content);
        layout.putConstraint(SpringLayout.SOUTH, confirmButtons, -PADDING_INNER, SpringLayout.SOUTH, content);

        confirmButtons.add(accept, BorderLayout.WEST);
        confirmButtons.add(cancel, BorderLayout.EAST);
        content.add(confirmButtons);

        this.add(new JScrollPane(content));
    }

    public void onSave() {
        Configuration config = ArcdpsDownloaderGW2.getConfig();
        if (gamePathValue.getText() == null || gamePathValue.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select the GW2 executable", getTitle(),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        File file = new File(gamePathValue.getText());
        if (!Utils.isValidGW2Location(file)) {
            JOptionPane.showMessageDialog(this, "Game path seems not valid", getTitle(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        config.setGW2Path(file.isDirectory() ? file.getAbsolutePath() : file.getParentFile().getAbsolutePath());
        config.setDirectXVersion((Integer) directXModel.getSelectedItem());
        config.setAutostart(autostartCheckbox.isSelected());
        config.save();

        close();
    }

    private void close() {
        this.setVisible(false);
        this.dispose();

        this.autostartCheckbox = null;
        this.directXModel = null;
        this.gamePathValue = null;
    }

    public static void main(String[] args) {
        Settings s = new Settings(null, false);
        s.setDefaultCloseOperation(Settings.DISPOSE_ON_CLOSE);
        s.setVisible(true);
    }

}
