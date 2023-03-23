/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnaire;


import fentre.Lancement;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import music.Sound;
import network.Client;
import network.Host;
import puisssance4.Partie;

/**
 *
 * @author Anthony.K
 */
public class GestionnairePartie {
    private static Partie p;
    
    public static Partie getP() {
        return p;
    }
    
    
    public static void historique(){
        try{
            GestionnairePartie.p.historique();
        }catch(IOException e){
            System.out.println("Probleme pour ouvrir le fichier ");
            System.exit(-1);
        }
    }
    
    /*
    public void initReseau(Partie p) throws IOException{
        System.out.println("1. Heberger\n2. Rejoindre un salon");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        while(!s.equals("1") && !s.equals("2")){
                System.out.println("Choix non valide ");
                System.out.println("Reessaye ");
                System.out.println("Entrez l'un des nombres proposes ");
                s = sc.next();
            }
    }
    */
    
    public static void launch(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException{
        Sound.main(args);
        System.out.println("1. Reseau\n2. Local");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        while(!s.equals("1") && !s.equals("2")){
                System.out.println("Choix non valide ");
                System.out.println("Reessaye ");
                System.out.println("Entrez l'un des nombres proposes ");
                s = sc.next();
            }
        if (s.equals("1")){
            System.out.println("1. Heberger la partie\n2. Rejoindre un salon");
            String mode = sc.next();
            while(!mode.equals("1") && !mode.equals("2")){
                System.out.println("Choix non valide ");
                System.out.println("Reessaye ");
                System.out.println("Entrez l'un des nombres proposes ");
                mode = sc.next();
            }
            if (mode.equals("2"))
                Client.main(args);
            else{
                Host.main(args);
            }
            
        }else{
            GestionnairePartie.p = new Partie();
            p.debuter();
            p.initializePartie();
            p.attenteVictoire();
            p.historique();
            p.load();
        }
    }
    public static void launch() throws IOException, InterruptedException{
            GestionnairePartie.p = new Partie();
            p.debuter();
            p.initializePartie();
            p.attenteVictoire();
            p.historique();
            p.load();
            System.exit(0);
        }
    
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        //Lancement.main(args);
        launch();
    }
}
