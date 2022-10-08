package com.sun.collectiblestore.main;

import com.sun.collectiblestore.main.input.MouseInput;
import com.sun.collectiblestore.main.utils.Load;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AppPanel extends JPanel {
//Pra dar load em imagens é preciso de um res folder configurado
    private BufferedImage panelImg;

    private BufferedImage[] idleAnim;
    private int animTick, animIndex, animSpeed = 10;

    private MouseInput mouseInput;
    private  App app;

    public AppPanel(App app){
        mouseInput = new MouseInput(this);
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
        BufferedImage bg = Load.getSpriteAtlas("bg.png");
        panelImg = bg.getSubimage(0, 0, 600, 400);
        this.app = app;
        loadVendorAnim();

        setPanelSize();
    }

    public void setPanelSize(){
        // O tamanho da janela é feito pelo panel porque pelo jframe ele conta as bordas e barra da janela.
        Dimension size = new Dimension(900, 600);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }

    private void loadVendorAnim(){
        BufferedImage img = Load.getSpriteAtlas("test.png");
        idleAnim = new BufferedImage[8];
        for(int i = 0; i < idleAnim.length; i++){
            idleAnim[i] = img.getSubimage(i * 147, 0, 147, 200);
        }
    }
    private void updateTick(){
        animTick++;
        if(animTick >= animSpeed){
            animTick = 0;
            animIndex++;
            if (animIndex >= idleAnim.length)
                animIndex = 0;
        }
    }

    public void updateGame(){
        updateTick();
    }

    public void paintComponent(Graphics g){
        //cuidado usar o getsubimage no paint por algum motivo causou um erro que aumentava o processamento da aplicação de forma exponencial
        super.paintComponent(g);
        //background, não fiz algo muito elaborado porque só vai ter essa tela mesmo então não vou gastar mais tempo pra reprovar nessa uc kajhdjkasd
        g.drawImage(panelImg, 0, 0, 900, 600, null);
        //renderiza o bonequinho animado
        g.drawImage(idleAnim[animIndex], 330, 165, 230, 300, null);
        app.render(g);
    }

    public App getApp(){return app;}
}
