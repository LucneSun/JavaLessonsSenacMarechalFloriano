package com.sun.collectiblestore.main.input;

import com.sun.collectiblestore.main.AppPanel;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseMotionListener, MouseInputListener {
    private AppPanel appPanel;

    public MouseInput(AppPanel appPanel){
        this.appPanel = appPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        appPanel.getApp().getNormal().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        appPanel.getApp().getNormal().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        appPanel.getApp().getNormal().mouseMoved(e);
    }
}
