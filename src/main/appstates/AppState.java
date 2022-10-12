package main.appstates;

import main.App;
import main.ui.Button;

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
