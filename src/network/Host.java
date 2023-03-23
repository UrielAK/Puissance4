/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;

import gestionnaire.GestionnairePartie;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import joueur.Joueur;
import puisssance4.Partie;

/**
 *
 * @author Anthony.K
 */
public class Host{
  
    private static ServerSocket host;
    private static Socket s;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    private static String name;
    private static String nameCli;

    public Host() throws IOException {
        host = new ServerSocket(1026);
    }
    

    /*
    public Host(Socket s) throws IOException {
        super();
        this.host = new ServerSocket(1026);
        this.s = s;
        this.ois = new ObjectInputStream(s.getInputStream());
        this.oos = new ObjectOutputStream(s.getOutputStream());
    }
    */

    public ServerSocket getHost() {
        return this.host;
    }
    
    
    public static void menu(){
        try{
            host = new ServerSocket(1026);
            System.out.println("Entrer votre nom");
            Scanner sc = new Scanner(System.in);
            name = sc.next();
            System.out.println("Attente de connexion");
            host.setSoTimeout(15000);
            s = host.accept();
            //on recupere la donnee envoyee par le client
            in = new DataInputStream(s.getInputStream());
            nameCli = in.readUTF();
            System.out.println("Connexion etablie avec "+nameCli);
            //On effectue un traitement
            //Envoie de la donnee au client
            out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(name);
        }catch(Exception e){System.exit(0);}
    }
    public static void main(String[] args) throws IOException {
        
        Partie p = new Partie();
        //p.setType(4);
        menu();
        p.setJ1(new Joueur(1,name));
        p.setJ2(new Joueur(2,nameCli));
        p.debuter(s);
        p.attenteVictoire(s,out);
        p.historique();
        p.load();
    }
    /*@Override
    public int place(char[][] c) throws IOException{
        Scanner input = new Scanner(System.in);
        int n;
        System.out.println("A quelle colonne voulez vous jouer "+this.nom);
        String s  = input.next();
        if(s.equals("sortir")){
            do{
            try{
                System.out.println("Etes vous sur de vouloir arreter?");
                System.out.println("1. oui\t\t\t2. non");
                input = new Scanner(System.in);
                n = input.nextInt();
            }catch(Exception e){
                System.out.println("Vous devez entrez 1 ou 2");
                input = new Scanner(System.in);
                n = input.nextInt();
                
            }}while(n!=1 && n!=2);
            if (n==1){
                GestionnairePartie.getP().getList().add(this.nom+" a arrete la partie");
                GestionnairePartie.historique();
                GestionnairePartie.launch();
            }else
                n = 1+place(c);
        }else{
            try{
                n = Integer.parseInt(s);
            }catch(NumberFormatException e){
                System.out.println("Vous devez entrer un nombre entre 1 et "+c[0].length);
                n = input.nextInt();
            }
        while (n>c[0].length || n<1){
            System.out.println("Choix non valide");
            System.out.println("A quelle colonne voulez vous jouer "+this.nom);
            n  = input.nextInt();
        }
        }
        return n-1;
    }*/
}
