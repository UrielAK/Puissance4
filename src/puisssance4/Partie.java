/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package puisssance4;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import joueur.IA;
import joueur.Joueur;
import network.Host;

/**
 *
 * @author Anthony.K
 */
public class Partie {
    
    protected Joueur j1;
    protected Joueur j2;
    protected Manche m;
    protected int nbm;
    protected int type;
    protected ArrayList<String> list = new ArrayList<>();
    protected ArrayList<String> previous = new ArrayList<>();
    
    
    public Partie() {
        this.m = new Manche(new char[6][7]);
        this.nbm = 3;
    }

    public Partie(Manche m,int n) {
        this.m = m;
        this.nbm = n;
    }

    public Joueur getJ1() {
        return j1;
    }

    public void setJ1(Joueur j1) {
        this.j1 = j1;
    }
    

    public Joueur getJ2() {
        return j2;
    }

    public void setJ2(Joueur j2) {
        this.j2 = j2;
    }
    
    public Manche getM() {
        return m;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<String> getList() {
        return this.list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
    
    
    
    public void nomJ(){
        Scanner input = new Scanner(System.in);
        System.out.println("Entrez votre nom Joueur 1");
        String s = input.next();
        this.j1.setNom(s);
        System.out.println("Entrez votre nom Joueur 2");
        s = input.next();
        this.j2.setNom(s);  
    }
    public void nomJIA(){
        Scanner input = new Scanner(System.in);
        System.out.println("\t\tEntrez votre nom Joueur 1");
        String s = input.next();
        this.j1.setNom(s); 
    }
    public void nomIA(){
        this.j1.setNom("IA_RANDOM");
        this.j2.setNom("IA_DARK_VADOR"); 
    }
    public void menu(){
        Scanner input = new Scanner(System.in);
        String  n;
        System.out.println("\t\t****************Choisissez le mode de jeu: ***************");
        System.out.println("\t\t1- Joueur vs Joueur");
        System.out.println("\t\t2- Joueur vs IA ");
        System.out.println("\t\t3- IA VS IA ");
        n  = input.next();
        while (!n.equals("1") && !n.equals("2") && !n.equals("3")){
            System.out.println("Choix non valide ");
            System.out.println("Reessaye ");
            System.out.println("Entrez l'un des nombres proposes ");
            n  = input.next();
        }
        this.type=Integer.parseInt(n);
    }
    
    public void initializePartie(){
        if(this.type==1){
            this.j1 = new Joueur(1);
            this.j2 = new Joueur(2);
            this.nomJ();
        }else if(this.type==2){
            System.out.println("Jouer contre \n1. random(Easy)\t\t2. Dark Vador(Medium)");
            Scanner sc = new Scanner(System.in);
            String n  = sc.next();
            while (!n.equals("1") && !n.equals("2")){
                System.out.println("Choix non valide ");
                System.out.println("Reessaye ");
                System.out.println("Entrez l'un des nombres proposes ");
                n  = sc.next();
            }
            if(n.equals("1")){
                this.j1 = new Joueur(1);
                this.j2 = new IA(1,2);
                this.j2.setNom("IA_RANDOM"); 
            }else{
                this.j1 = new Joueur(1);
                this.j2 = new IA( 2,2);
                this.j2.setNom("IA_DARK_VADOR"); 
            }
            this.nomJIA();
        }else{
            this.j1 = new IA(1,1);
            this.j2 = new IA(2,2);
            this.nomIA();
        }
    }
    public void initReseau(Socket socket){
            this.type=1;
        }
    

    public void rappelScore(){
        System.out.println("\t\t\t-----------Rappel score:-------------");
        System.out.println("\t\t\t\t"+this.j1.getNom()+" ("+this.j1.getVictoire()+" - "+this.j2.getVictoire()+") "+this.j2.getNom()+"\n\n");        this.list.add("Rappel score \n"+this.j1.getNom()+" ("+this.j1.getVictoire()+" - "+this.j2.getVictoire()+") "+this.j2.getNom()+"\n\n");
    }


    public void attenteVictoire() throws IOException, InterruptedException{
        System.out.println("\t\t-----------------Debut de la manche "+m.getNumero()+"----------------\n");
        this.previous.add(this.j1.getNom());
        this.previous.add(this.j2.getNom());
        this.list.add("Debut de la manche "+m.getNumero()+"\n");
        System.out.println((this.m.getNumero()%2==1 ? j1.getNom() : j2.getNom())+" commence avec X\n");
        this.list.add((this.m.getNumero()%2==1 ? j1.getNom() : j2.getNom())+" commence avec X\n");
        System.out.println((this.m.getNumero()%2==0 ? j1.getNom() : j2.getNom())+" jouera avec O\n");
        this.list.add((this.m.getNumero()%2==0 ? j1.getNom() : j2.getNom())+" jouera avec O\n");
                //if(this.type==1){
        this.m.nouveauPlateau();
        this.m.affichage();
        int i = 1;
            while (!this.m.isDone() && i<(this.m.getGrille().length*this.m.getGrille()[0].length)){
                if(this.type==3)
                        Thread.sleep(500);
                this.list.add((this.m.getNumero()%2==1 ? j1.getNom() : j2.getNom())+" joue "+this.m.tour((this.m.getNumero()%2==1 ? this.j1 : this.j2),this.getType())+"\n");
                if(!this.m.isDone()){
                    if(this.type==3)
                        Thread.sleep(500);
                    this.list.add((this.m.getNumero()%2==0 ? j1.getNom() : j2.getNom())+" joue "+this.m.tour((this.m.getNumero()%2==0 ? this.j1 : this.j2),this.getType())+"\n");
                }
                i++;
                if(i==(this.m.getGrille().length*this.m.getGrille()[0].length)){
                    this.m.setDone(true);
                    System.out.println("\t\tMatch nul");
                    this.list.add("Match nul\n");
                }
            }
                //}
        this.j1.setQ(this.m.getNumero()+2);
        this.j2.setQ(this.m.getNumero()+1);
        this.rappelScore();
        this.m.setDone(false);
        if(this.j1.getVictoire()<this.nbm && this.j2.getVictoire()<this.nbm)
            this.attenteVictoire();
        else{
            if(this.j1.getVictoire()==this.nbm){
                System.out.println("\t\t***************************************");
                System.out.println("\t\t----------Victoire de "+this.j1.getNom()+"----------");
                this.list.add("Victoire de "+this.j1.getNom());
                System.out.println("\t\t***************************************");
            }else{
                System.out.println("\t\t***************************************");
                System.out.println("\t\t----------Victoire de "+this.j2.getNom()+"-----------");
                this.list.add("Victoire de "+this.j2.getNom());
                System.out.println("\t\t***************************************");
            }
        }
        
    }
    public void attenteVictoire(Socket s,DataOutputStream out) throws IOException, InterruptedException{
        System.out.println("\t\t-----------------Debut de la manche "+m.getNumero()+"----------------\n");
        out.writeUTF("\t\t-----------------Debut de la manche "+m.getNumero()+"----------------\n");
        out.flush();
        this.previous.add(this.j1.getNom());
        this.previous.add(this.j2.getNom());
        this.list.add("Debut de la manche "+m.getNumero()+"\n");
        System.out.println((this.m.getNumero()%2==1 ? j1.getNom() : j2.getNom())+" commence avec X\n");
        out.writeUTF((this.m.getNumero()%2==1 ? j1.getNom() : j2.getNom())+" commence avec X\n");
        this.list.add((this.m.getNumero()%2==1 ? j1.getNom() : j2.getNom())+" commence avec X\n");
        System.out.println((this.m.getNumero()%2==0 ? j1.getNom() : j2.getNom())+" jouera avec O\n");
        out.writeUTF((this.m.getNumero()%2==0 ? j1.getNom() : j2.getNom())+" jouera avec O\n");
        this.list.add((this.m.getNumero()%2==0 ? j1.getNom() : j2.getNom())+" jouera avec O\n");
                //if(this.type==1){
        this.m.nouveauPlateau();
        this.m.affichage();
        int i = 1;
        while (!this.m.isDone() && i<(this.m.getGrille().length*this.m.getGrille()[0].length)){
            this.list.add((this.m.getNumero()%2==1 ? j1.getNom() : j2.getNom())+" joue "+this.m.tour((this.m.getNumero()%2==1 ? this.j1 : this.j2),this.getType())+"\n");
            if(!this.m.isDone())
                this.list.add((this.m.getNumero()%2==0 ? j1.getNom() : j2.getNom())+" joue "+this.m.tour((this.m.getNumero()%2==0 ? this.j1 : this.j2),this.getType())+"\n");
            i++;
            if(i==(this.m.getGrille().length*this.m.getGrille()[0].length)-2){
                this.m.setDone(true);
                System.out.println("\t\tMatch nul");
                this.list.add("Match nul\n");
            }
        }
                //}
        this.j1.setQ(this.m.getNumero()+2);
        this.j2.setQ(this.m.getNumero()+1);
        this.rappelScore();
        this.m.setDone(false);
        if(this.j1.getVictoire()<this.nbm && this.j2.getVictoire()<this.nbm)
            this.attenteVictoire();
        else{
            if(this.j1.getVictoire()==this.nbm){
                System.out.println("\t\t***************************************");
                System.out.println("\t\t----------Victoire de "+this.j1.getNom()+"----------");
                this.list.add("Victoire de "+this.j1.getNom());
                System.out.println("\t\t***************************************");
            }else{
                System.out.println("\t\t***************************************");
                System.out.println("\t\t----------Victoire de "+this.j2.getNom()+"-----------");
                this.list.add("Victoire de "+this.j2.getNom());
                System.out.println("\t\t***************************************");
            }
        }
        
    }
    public int nbManche(){
        System.out.println("\tCombien de manche a gagner?");
        System.out.println("1- une manche\n2- trois manches\n3- cinq manches\n4- autre");
        Scanner input = new Scanner(System.in);
        String n = input.next();
        while(!n.equals("1") && !n.equals("2") && !n.equals("3") && !n.equals("4")){
            System.out.println("Erreur : choix non valide reesaye");
            n = input.next();
        }
        if(n.equals("1"))
            return 1;
        else if(n.equals("2"))
            return 3;
        else if(n.equals("3"))
            return 5;
        else{
            System.out.println("Combien de manches voulez vous exactement avant une victoire?");
            n = input.next();
            return Integer.parseInt(n);
        }
  
    }
    public void debuter(){
        System.out.println("\t\t\t1. Nouvelle partie");
        System.out.println("\t\t\t2. Parametre partie");
        System.out.println("\t\t\t3. Quitter");
        Scanner input = new Scanner(System.in);
        String s = input.next();
        while (!s.equals("1") && !s.equals("2") && !s.equals("3")){
            System.out.println("Choix non valide ");
            System.out.println("Reessaye ");
            System.out.println("Entrez l'un des nombres proposes ");
            s = input.next();
        }
        if (s.equals("1")){
                this.menu();
            
        }
        else if(s.equals("2")){
            System.out.println("1. Nombre de manche pour gagner: "+this.nbm);
            System.out.println("2. Taille de la grille: "+this.m.getGrille().length+" x "+this.m.getGrille()[0].length);
            System.out.println("3. Retour");
            String z = input.next();
            
            while (!z.equals("1")&& !z.equals("2")&& !z.equals("3")){
                System.out.println("Choix non valide ");
                System.out.println("Reessaye ");
                System.out.println("Entrez l'un des nombres proposes ");
                z = input.next();
            }
            if(z.equals("1")){
               this.nbm = this.nbManche();
               this.debuter();
            }
            else if(z.equals("2")){
                this.m.tailleGrille();
                this.debuter();
            }
            else
                this.debuter();
        }else if(s.equals("3")){
            System.exit(0);
        }else
            this.debuter();
    }
    public void debuter(Socket socket){
        System.out.println("\t\t\t1. Nouvelle partie");
        System.out.println("\t\t\t2. Parametre partie");
        System.out.println("\t\t\t3. Quitter");
        Scanner input = new Scanner(System.in);
        String s = input.next();
        while (!s.equals("1") && !s.equals("2") && !s.equals("3")){
            System.out.println("Choix non valide ");
            System.out.println("Reessaye ");
            System.out.println("Entrez l'un des nombres proposes ");
            s = input.next();
        }
        if (s.equals("1")){
                this.initReseau(socket);
            
        }
        else if(s.equals("2")){
            System.out.println("1. Nombre de manche pour gagner: "+this.nbm);
            System.out.println("2. Taille de la grille: "+this.m.getGrille().length+" x "+this.m.getGrille()[0].length);
            System.out.println("3. Retour");
            String z = input.next();
            
            while (!z.equals("1")&& !z.equals("2")&& !z.equals("3")){
                System.out.println("Choix non valide ");
                System.out.println("Reessaye ");
                System.out.println("Entrez l'un des nombres proposes ");
            }
            if(z.equals("1")){                                            
               this.nbm = this.nbManche();
               this.debuter(socket);
            }
            else if(z.equals("2")){
                this.m.tailleGrille();
                this.debuter(socket);
            }
            else
                this.debuter(socket);
        }else if(s.equals("3")){
            System.exit(0);
        }else
            this.debuter(socket);
    }
    
    public void historique() throws IOException{
            File f = new File("log.txt");
            try (FileWriter fw = new FileWriter(f)) {
                for(String s : this.list){
                    fw.write(s+"\n");
                }
            }
        }
          
    public void load() throws IOException{
            File f = new File("C:\\Users\\Anthony.K\\Documents\\NetBeansProjects\\Puisssance4\\src\\gestionnaire\\load.txt");
            try (FileWriter fw = new FileWriter(f)) {
                for(String s : this.previous){
                    fw.write(s+"\n");
                }
            }
        }
        
}
