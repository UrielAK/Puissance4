/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joueur;

import gestionnaire.GestionnairePartie;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Anthony.K
 */
public class Joueur {
    protected int victoire;
    protected String nom;
    protected int q;
    

    public Joueur() {
        this.victoire = 0;
    }

    public Joueur(int q) {
        this.victoire = 0;
        this.q = q;
    }
    public Joueur(int q,String s){
        this.nom = s;
        this.q = q;
        this.victoire = 0;
    }
    //getters
    public int getVictoire() {
        return this.victoire;
    }
    public String getNom() {
        return this.nom;
    }
    public int getQ() {
        return this.q;
    }
    //setters

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public void setVictoire(int victoire) {
        this.victoire = victoire;
    }
    
    /*public void arreterPartie(char[][] c){
        System.out.println("Etes vous sur de vouloir arreter?");
        System.out.println("1. oui\t\t\t2. non");
        n = input.nextInt();
        if (n==1)
            System.exit(0);
            else
                place(c);
    }*/
    
    public int place(char[][] c) throws IOException{
        Scanner input = new Scanner(System.in);
        String m;
        int n=-1;
        System.out.println("A quelle colonne voulez vous jouer "+this.nom);
        String s  = input.next();
        if(s.equalsIgnoreCase("sortir")){
            System.out.println("Etes vous sur de vouloir arreter?");
            System.out.println("1. oui\t\t\t2. non");
            input = new Scanner(System.in);
            m = input.next();
            while(!m.equals("1") && !m.equals("2")){
                System.out.println("Vous devez entrez 1 ou 2");
                input = new Scanner(System.in);
                m = input.next();
            }
            n = Integer.parseInt(m);
            if (n==1){
                GestionnairePartie.getP().getList().add(this.nom+" a arrete la partie");
                GestionnairePartie.getP().getM().setDone(true);
                GestionnairePartie.historique();
                GestionnairePartie.launch();
            }else
                n = 1+place(c);
        }else{
            do{
            try{
                n = Integer.parseInt(s);
            }catch(NumberFormatException e){
                System.out.println("Vous devez entrer un nombre entre 1 et "+c[0].length);
                s = input.next();
            }}while(n==-1);
        while (n>c[0].length || n<1){
            System.out.println("Choix non valide");
            System.out.println("A quelle colonne voulez vous jouer "+this.nom);
            n  = input.nextInt();
        }
        }
        return n-1;
    }
    
    
    
}
