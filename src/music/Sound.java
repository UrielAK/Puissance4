/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package music;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 *
 * @author Anthony.K
 */
public class Sound {
    
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        System.out.println("Choisir un son\n1. Simple $NOT\n2. In Da Club 50 Cent\n3. none");
        Scanner sc = new Scanner(System.in);
        String rep = sc.next();
        File file;
        Clip clip = AudioSystem.getClip();
        if(rep.equals("1")){
            file = new File("C:\\Users\\Anthony.K\\Documents\\NetBeansProjects\\Puisssance4\\SIMPLE.wav");AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip.open(audioStream);
            clip.loop(5);
        }else if(rep.equals("2")){
            file = new File("C:\\Users\\Anthony.K\\Documents\\NetBeansProjects\\Puisssance4\\InDaClub.wav");AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip.open(audioStream);
            clip.loop(5);
        }else{}
        
    }

    
}
