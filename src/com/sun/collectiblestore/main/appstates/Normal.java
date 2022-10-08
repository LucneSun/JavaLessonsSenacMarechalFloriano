package com.sun.collectiblestore.main.appstates;

import com.sun.collectiblestore.main.App;
import com.sun.collectiblestore.main.ui.Button;
import com.sun.collectiblestore.main.utils.ButtonType;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Normal extends  AppState implements IAppState{
    private Button[] buttons = new Button[5];
    public Normal(App app) {
        super(app);
        loadButtons();
    }

    private void loadButtons(){
        //g.drawImage(testButton, 5, 5, null);
        // g.drawImage(storeoptionPanel, 790, 500, null);
        // g.drawImage(inventoryIcon, 5, 500, null);
        // g.drawImage(store, 400, 500, null);

        buttons[0] = new Button(ButtonType.OPENCHARMANAGER, 5, 5, 1, super.getApp());
        buttons[1] = new Button(ButtonType.OPENSTOREMANAGER, 790, 500, 4, super.getApp());
        buttons[2] = new Button(ButtonType.OPENINVENTORY, 5, 500, 2, super.getApp());
        buttons[3] = new Button(ButtonType.OPENSTORE, 400, 500, 3, super.getApp());
        buttons[4] = new Button(ButtonType.ADDMONEY, 640, 5, 0, super.getApp());
    }

    @Override
    public void update() {
        for(Button button : buttons){
            button.update();
        }
    }


    public void draw(Graphics g) {
        for(Button button : buttons) {
            button.draw(g);
        }
        g.fillRect(735, 35, 160, 30);
        g.setColor(Color.white);
        g.setFont(new Font("", 0, 25));
        g.drawString(String.valueOf(getApp().money), 740, 60);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(Button button : buttons){
            if(isIn(e, button)){
                button.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(Button button : buttons){
            if(isIn(e, button)){
                if(button.getMousePressed())
                    button.openMenu();
                break;
            }
        }
        resetButtons();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(Button button : buttons)
            button.setMouseOver(false);

        for (Button button : buttons)
            if(isIn(e, button)) {
                button.setMouseOver(true);
                break;
            }
    }


    public void resetButtons(){
        for(Button button : buttons)
                button.resetBools();


    }
}
