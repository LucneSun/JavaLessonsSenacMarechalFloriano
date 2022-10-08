package com.sun.collectiblestore.main.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.net.URL;

public class AudioPlayer {
    public static int LOOP = 0;
    public static int LOOP2 = 1;
    public static int INTERACT = 2;

    private Clip[] songs, effects;
    private int currentSongId;
    private float volume = 1f;


    public AudioPlayer(){
        loadSongs();
        loadEffects();
        playSong(LOOP);
    }

    public void playSong(int song){
        if(songs[currentSongId].isActive())
            songs[currentSongId].stop();

        currentSongId = song;
        songs[currentSongId].setMicrosecondPosition(0);
        songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playEffect(int effect){
        effects[effect].setMicrosecondPosition(0);
        effects[effect].start();
    }

    private void loadSongs(){
        String[] names = {"LOOP"};
        songs = new Clip[names.length];
        for(int i = 0; i < songs.length; i++){
            songs[i] = getClip(names[i]);
        }
    }

    private void loadEffects(){
        String[] names = {"INTERACT", "GOLD"};
        effects = new Clip[names.length];
        for(int i = 0; i < effects.length; i++){
            effects[i] = getClip(names[i]);
        }
    }

    private Clip getClip(String name){
        URL url = getClass().getResource("/audio/" + name + ".wav");
        AudioInputStream inputStream;
        try {
            inputStream = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(inputStream);
            return c;
        }catch (UnsupportedAudioFileException | LineUnavailableException | IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
