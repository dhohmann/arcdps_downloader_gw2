package com.gw2.arcdpsdownloadergw2.ui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.Icon;
import javax.swing.JLabel;

public class JLink extends JLabel implements MouseListener {

    private String href;
    private Color activeColor;
    private Color resetColor;

    public JLink(String text, String href) {
        this(null, text, href);
    }

    public JLink(Icon image, String text, String href) {
        super(text, image, LEADING);
        this.href = href;
        this.addMouseListener(this);
    }

    /**
     * @param href the href to set
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * @return the href
     */
    public String getHref() {
        return href;
    }

    public void open() throws IOException {
        if (isSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(href));
            } catch (URISyntaxException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean isSupported() {
        return Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            open();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        resetColor = getForeground();
        setForeground(activeColor);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setForeground(resetColor);
        resetColor = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

}
