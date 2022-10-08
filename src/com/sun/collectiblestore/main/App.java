package com.sun.collectiblestore.main;

import com.sun.collectiblestore.main.appstates.Normal;
import com.sun.collectiblestore.main.audio.AudioPlayer;
import com.sun.collectiblestore.main.utils.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

//A classe implementa Runnable para passar o método run pra thread, é nécessário uma thread pro game loop pra ele não rodar na thread principal e causar lag e outros problemas
public class App implements Runnable{

    public int charId = 0;
    public double money = 1000f;
    private BufferedImage testButton;
    private BufferedImage inventoryIcon;
    private BufferedImage storeoptionPanel;
    private BufferedImage store;
    private Thread appLoop;

    public AudioPlayer audioPlayer;

    // a vantagem de separar a contagem de fps (frames por segundo) e ups(updates por segundo) é que o player pode setar
    // um fps mais baixo se quiser, o ups precisa ser o mesmo pra qualquer usuário e precisa contornar possiveis lags

    private final int FPS_SET = 60;
    private final int UPS_SET = 60;

    private Window window;
    private AppPanel appPanel;

    private Normal normal;

    public App(){
        BufferedImage img = Load.getSpriteAtlas("buttons.png");
        testButton = img.getSubimage(0, 100, 100, 100);
        inventoryIcon = img.getSubimage(0, 200, 100, 100);
        storeoptionPanel = img.getSubimage(0, 400, 100, 100);
        store = img.getSubimage(0, 300, 100, 100);

        normal = new Normal(this);
        appPanel = new AppPanel(this);
        window = new Window(appPanel);
        //pede o foco pra escutar os inputs
        appPanel.requestFocus();

        audioPlayer = new AudioPlayer();
    }

    public void start(){
        Main.test();
        initClasses();

        appLoop = new Thread(this);
        //na thread principal usa o run
        appLoop.start();
    }

    public void initClasses(){

    }

    public void update(){
        appPanel.updateGame();
        normal.update();
    }

    public void render(Graphics g){

       normal.draw(g);

    }

    @Override
    public void run() {
        //quando você esta lidando com game loops é preciso ser preciso e usar nano segundos
        //É definido quantos nano segundos tem um segundo o resultado é divido por quantos frames ou updates tem um segundo
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime(); // responsavel por marcar o tempo de ínicio da contagem

        long lastCheck = System.currentTimeMillis(); // checa o tempo de agora em milisegundos pra iniciar a contagem de fps e ups por segundo
        int frames = 0;
        int updates = 0;

        //maneira de contornar o update ou frame perdido por lag ou problema
        double deltaU = 0;
        double deltaF = 0;

        while (true){
            //guarda o tempo no momento em nano segundos
            long currentTime = System.nanoTime();
            //pega o tempo que passou entre o ínicio da contagem e o momento e divide pelo intervalo de frames em um segundo
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime; // reseta o tempo inicial

            //o delta update será 1 ou maior que 1 quando a diferença do tempo do início da contagem for igual ou maior que o intervalo de tempo de frames em um segundo
            //é dessa forma para que não aconteça nenhuma perda de frame
            if(deltaU >= 1){
                update();
                updates++; // usado pra contagem
                deltaU --; // subtrai um update depois de o processar
            }
            if(deltaF >= 1){
                appPanel.repaint();
                frames++; // usado pra contagem
                deltaF--;
            }

            //checa se o tempo da última checagem de fps e ups em milisegundos é maior que um segundo
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis(); // marca agora como ínicio do contador do próximo loop de segundo
                System.out.println("FPS: " + frames + "| UPS: " + updates);
                // reseta a contagem depois de um segundo
                frames = 0;
                updates = 0;
            }
        }
    }

    public Normal getNormal(){return this.normal;}
}
