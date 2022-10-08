package com.sun.collectiblestore.main.appstates;

import com.sun.collectiblestore.main.App;
import com.sun.collectiblestore.main.ui.Button;

import java.awt.event.MouseEvent;

public class AppState {
    private App app;
    public AppState(App app){
        this.app = app;

    }

    public boolean isIn(MouseEvent e, Button b){
        return b.getBounds().contains(e.getX(), e.getY());
    }

    public App getApp(){return  this.app;}
}
