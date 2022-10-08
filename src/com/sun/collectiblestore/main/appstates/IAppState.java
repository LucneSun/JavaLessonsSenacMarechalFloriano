package com.sun.collectiblestore.main.appstates;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface IAppState {
    public void update();
    public void draw(Graphics g);
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseMoved(MouseEvent e);
}
