package com.gw2.arcdpsdownloadergw2.ui.components.datatransfer;

import java.io.Serializable;

import javax.swing.DefaultListModel;

class ListMoveTransferData implements Serializable {

    private final DefaultListModel<?> model;
    private final int[] indices;

    ListMoveTransferData(DefaultListModel<?> model, int[] indices) {
        this.model = model;
        this.indices = indices;
    }

    /**
     * Retrieves the indices for the selected items
     * 
     * @return indices for the move operation
     */
    public int[] getIndices() {
        return indices;
    }

    /**
     * Retrieves the model containing the moved items.
     * 
     * @return list model instance
     */
    public DefaultListModel<?> getModel() {
        return model;
    }
}