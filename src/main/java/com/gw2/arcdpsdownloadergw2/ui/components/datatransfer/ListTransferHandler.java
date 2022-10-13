package com.gw2.arcdpsdownloadergw2.ui.components.datatransfer;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

public class ListTransferHandler extends TransferHandler {

    @Override
    public int getSourceActions(JComponent c) {
        if (!(c instanceof JList)) {
            return NONE;
        }
        return getSourceActions((JList<?>) c);
    }

    protected int getSourceActions(JList<?> list) {
        return list.getModel() instanceof DefaultListModel ? MOVE : NONE;
    }

    @Override
    public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
        if (!(comp instanceof JList)
                || !(((JList<?>) comp).getModel() instanceof DefaultListModel)) {
            System.out.println("Cannot import: No list");
            return false;
        }

        final DefaultListModel<?> model = (DefaultListModel<?>) ((JList<?>) comp).getModel();
        for (DataFlavor f : transferFlavors) {
            if (ListItemTansferFlavor.match(model, f)) {
                return true;
            }
        }
        System.out.println("Cannot import: Flavour not supported");
        return false;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        final JList<?> list = (JList<?>) c;
        final int[] selectedIndices = list.getSelectedIndices();
        return new ListItemTransferable(new ListMoveTransferData(
                (DefaultListModel<?>) list.getModel(), selectedIndices));
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport info) {
        final Component comp = info.getComponent();
        if (!info.isDrop() || !(comp instanceof JList)) {
            System.out.println("Cannot import: No drop action");
            return false;
        }
        final JList<?> list = (JList<?>) comp;
        if (!(list.getModel() instanceof DefaultListModel)) {
            System.out.println("Cannot import: No default list model");
            return false;
        }

        final DefaultListModel<?> listModel = (DefaultListModel<?>) list.getModel();
        final DataFlavor flavor = new ListItemTansferFlavor(listModel);
        if (!info.isDataFlavorSupported(flavor)) {
            System.out.println("Cannot import: Not supported data flavor");
            return false;
        }

        final Transferable transferable = info.getTransferable();
        try {
            return transfer(transferable, transferable.getTransferData(flavor), list);
        } catch (UnsupportedFlavorException ex) {
            System.out.println("Cannot import: Not supported");
            return false;
        } catch (Throwable ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private <T> boolean transfer(final Transferable transferable, final Object object, final JList<T> list) {
        final ListMoveTransferData data = (ListMoveTransferData) object;
        if (!(list.getModel() instanceof DefaultListModel)) {
            return false;
        }
        final DefaultListModel<T> listModel = (DefaultListModel<T>) list.getModel();

        // get the initial insertion index
        final JList.DropLocation dropLocation = list.getDropLocation();
        int insertAt = dropLocation.getIndex();

        // get the indices sorted (we use them in reverse order, below)
        final int[] indices = data.getIndices();
        Arrays.sort(indices);

        // remove old elements from model, store them on stack
        final Stack<T> elements = new Stack<>();
        int shift = 0;
        for (int i = indices.length - 1; i >= 0; i--) {
            final int index = indices[i];
            if (index < insertAt) {
                shift--;
            }
            elements.push(listModel.remove(index));
        }
        insertAt += shift;

        // insert stored elements from stack to model
        final ListSelectionModel sm = list.getSelectionModel();
        try {
            sm.setValueIsAdjusting(true);
            sm.clearSelection();
            final int anchor = insertAt;
            while (!elements.isEmpty()) {
                listModel.insertElementAt(elements.pop(), insertAt);
                sm.addSelectionInterval(insertAt, insertAt++);
            }
            final int lead = insertAt - 1;
            if (!sm.isSelectionEmpty()) {
                sm.setAnchorSelectionIndex(anchor);
                sm.setLeadSelectionIndex(lead);
            }
        } finally {
            sm.setValueIsAdjusting(false);
        }
        return true;
    }

}
