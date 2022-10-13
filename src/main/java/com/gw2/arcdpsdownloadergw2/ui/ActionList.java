package com.gw2.arcdpsdownloadergw2.ui;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.gw2.arcdpsdownloadergw2.ArcdpsDownloaderGW2;
import com.gw2.arcdpsdownloadergw2.ui.components.datatransfer.ListTransferHandler;

public class ActionList extends JPanel {

    public ActionList() {
        this.add(new JScrollPane(createList(createModel())));
    }

    private DefaultListModel<String> createModel() {
        DefaultListModel<String> model = new DefaultListModel<String>();
        ArcdpsDownloaderGW2.getActionManager().getActions().forEach(e -> model.addElement(e));
        return model;
    }

    private JList<String> createList(DefaultListModel<String> model) {
        final JList<String> list = new JList<String>(model);
        list.setDragEnabled(true);
        list.setDropMode(DropMode.INSERT);
        list.setTransferHandler(new ListTransferHandler());
        list.setPrototypeCellValue("WWWWWWWWWWWWWWWWWW");
        return list;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new ActionList());
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
