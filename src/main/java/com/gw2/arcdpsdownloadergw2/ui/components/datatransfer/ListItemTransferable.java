package com.gw2.arcdpsdownloadergw2.ui.components.datatransfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ListItemTransferable implements Transferable {

    private final ListMoveTransferData data;

    public ListItemTransferable(ListMoveTransferData data) {
        this.data = data;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { new ListItemTansferFlavor(data.getModel()) };
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return ListItemTansferFlavor.match(data.getModel(), flavor);
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (!isDataFlavorSupported(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        return data;
    }

}
