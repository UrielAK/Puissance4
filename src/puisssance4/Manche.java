/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package puisssance4;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import joueur.Joueur;
import network.Client;

/**
 *
 * @author Anthony.K
 */
public class Manche {

    private char[][] grille;
    private boolean done;
    private int numero;

    public Manche() {
    }

    public Manche(char[][] grille) {
        this.grille = grille;
        this.done = false;
        this.numero = 1;
    }

    public char[][] getGrille() {
        return this.grille;
    }

    public void setGrille(char[][] grille) {
        this.grille = grille;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public void nouveauPlateau(){
        for (char[] tab : this.grille){
            for (int j=0;j<this.grille[0].length;j++){
                tab[j]='.';
            }
        }
        
    }
    public void affichage(){
        for (char[] tab : this.grille){
            System.out.print("\t\t\t\t|");
            for (char c : tab){
                System.out.print(" " + c + " ");
            }
            System.out.print("|\n");
        }
        System.out.print("\t\t\t\t ");
        for (int i=1;i<=this.grille[0].length;i++)
            if (i<10)
                System.out.print(" "+i+" ");
            else
                System.out.print(" "+i);
        
        System.out.println();
    }
    public String affich(){
        String string = "";
        for (char[] tab : this.grille){
            string+="\t\t\t\t|";
            for (char c : tab){
                string+=" " + c + " ";
            }
            string+="|\n";
        }
        string+="\t\t\t\t ";
        for (int i=1;i<=this.grille[0].length;i++)
            if (i<10)
                string+=" "+i+" ";
            else
                string+=" "+i;
        
        string+="\n";
        return string;
    }
    
    public int tour(Joueur j,int typepartie) throws IOException{
        int i=0;
        int n=j.place(this.grille);
        int num=j.getQ();
        while (i<this.grille.length && this.grille[i][n]=='.'){
            i++;
        }
        if(i-1>=0){
            this.grille[i-1][n]=(num%2==1 ? 'X' : 'O');
            this.affichage();
            this.detectionVictoire(i-1, n, num,j);
        }else{
            if (typepartie == 1){
                System.out.println("il n'y a plus de place");
                System.out.println("Inserez dans une nouvelle colonne");
                this.tour(j, typepartie);
            }else if(typepartie == 2 && num%2==0){
                this.tour(j, typepartie);
            }else{
                this.tour(j, typepartie);
            }
        }
        return n+1;
    }
    /*public int tour(Joueur j,Socket s) throws IOException{
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        int i=0;
        int num=j.getQ();
        int n;
        if (num%2==1)
            n = j.place(this.grille);
        else{
            out.writeUTF("A quelle colonne voulez vous jouer?");
            n = Integer.parseInt(in.readUTF());
        }
        while (i<this.grille.length && this.grille[i][n]=='.'){
            i++;
        }
        if(i-1>=0){
            this.grille[i-1][n]=(num%2==1 ? 'X' : 'O');
            this.affichage();
            this.detectionVictoire(i-1, n, num,j);
        }else{
            if (this.numero%2==1){
                if(num%2==1){
                    System.out.println("il n'y a plus de place");
                    System.out.println("Inserez dans une nouvelle colonne");
                    this.tour(j, s);
                }else{
                    
                }
            }else{
                
            }
        }
        return n+1;
    }*/
    public void detectionVictoire(int x,int y,int tour,Joueur joueur){
        char caractere = (tour%2==1 ? 'X' : 'O');
        int countx = 0;
        int county = 0;
        boolean countdiagm = false;
        boolean countdiagd = false;
        //Verification horizontale
            for (int i=0;i<this.grille[0].length;i++){
                if (this.grille[x][i]==caractere){
                countx++;
            }else{
                countx=0;
            }
                if (countx == 4)
                    break;
            }
                

        //verification verticale
        for (int i=0;i<this.grille.length;i++){
            if (this.grille[i][y]==caractere){
                county++;
            }else{
                county=0;
            }
                if (county == 4)
                    break;
                     
        }
        //verification oblique montante
        
        for (int i=this.grille.length-1;i>this.grille.length-4;i--)
                for (int j=0;j<this.grille[0].length-3;j++)
                        if (this.grille[i][j]==caractere && this.grille[i-1][j+1]==caractere && this.grille[i-2][j+2]==caractere && this.grille[i-3][j+3]==caractere)
                            countdiagm=true;
        
        //verification diagonale descendante
        
           for (int i=this.grille.length-4;i>=0;i--)
                for (int j=0;j<this.grille[0].length-3;j++)
                        if (this.grille[i][j]==caractere && this.grille[i+1][j+1]==caractere && this.grille[i+2][j+2]==caractere && this.grille[i+3][j+3]==caractere)
                               countdiagd=true;
        
        
        //Affectation du resultat
        if(countx==4 || county==4 || countdiagm ||countdiagd){
            this.done=true;
            joueur.setVictoire(joueur.getVictoire()+1);
            System.out.println("Manche "+this.numero+" gagnee par "+joueur.getNom());
            this.numero++;
        }else
            this.done=false;
        
    }
    public void tailleGrille(){
        System.out.println("Combien de ligne voulez vous? (minimum 4)");
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        System.out.println("Combien de colonne voulez vous? (minimum 4)");
        int y = input.nextInt();
        if ((x*y)%2==1 || x*y<8){
            System.out.println("Le produit des deux doit etre pair et superieur a 8");
            this.tailleGrille();
        }
        else
            this.setGrille(new char[x][y]);
    }
    
    public boolean full(){
        boolean b = true;
        for(char[] tab : this.grille)
            for(char c : tab){
                if (c=='.'){
                    b = false;
                    break;
                }
            }
        return b;
    }
    
    
}

