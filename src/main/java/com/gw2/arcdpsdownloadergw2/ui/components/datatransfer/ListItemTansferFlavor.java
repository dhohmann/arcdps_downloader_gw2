package com.gw2.arcdpsdownloadergw2.ui.components.datatransfer;

import java.awt.datatransfer.DataFlavor;

import javax.swing.DefaultListModel;

public class ListItemTansferFlavor extends DataFlavor {

    private static final String NAME = "List item";
    private DefaultListModel<?> model;

    public ListItemTansferFlavor(DefaultListModel<?> model) {
        super(ListItemTansferFlavor.class, NAME);
        this.model = model;
    }

    public DefaultListModel<?> getModel() {
        return model;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = prime * result + (model != null ? model.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(DataFlavor that) {
        if (this == that) {
            return true;
        }
        if (!super.equals(that) || getClass() != that.getClass()) {
            return false;
        }
        return match(model, that);
    }

    /**
     * Tests whether the given data flavor is a {@link ListMoveDataFlavor} and
     * matches the given model.
     * 
     * @param model  the model
     * @param flavor the flavor
     * @return {@code true} if matches
     */
    public static boolean match(DefaultListModel<?> model, DataFlavor flavor) {
        return flavor instanceof ListItemTansferFlavor
                && ((ListItemTansferFlavor) flavor).getModel() == model;
    }
}
