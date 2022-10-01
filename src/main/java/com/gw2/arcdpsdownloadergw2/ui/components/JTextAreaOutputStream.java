package com.gw2.arcdpsdownloadergw2.ui.components;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

/**
 * Outputs data to a text area and a parent output stream.
 */
public class JTextAreaOutputStream extends OutputStream {
    private JTextArea textArea;
    private OutputStream parent;

    public JTextAreaOutputStream(OutputStream parent, JTextArea textArea) {
        this.textArea = textArea;
        this.parent = parent;
    }

    @Override
    public void write(int b) throws IOException {
        if (parent != null) {
            parent.write(b);
        }

        // redirects data to the text area
        textArea.append(String.valueOf((char) b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}