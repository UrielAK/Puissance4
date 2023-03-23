/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Anthony.K
 */
    public class Client{
    private static Socket client;
    private static String name;
    private static boolean attente;
    private static BufferedReader in;
    private static DataOutputStream out;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    
    
    public static void connexion() throws IOException {
        client = new Socket("127.0.0.1",1026);
        attente = true;
        System.out.println("Entrez vote nom");
        Scanner sc = new Scanner(System.in);
        String nom = sc.next();
        //envoie des donnees au serveur
        out = new DataOutputStream(client.getOutputStream());  
        out.writeUTF(nom); 
        //recuperation donnee envoyee par le serveur
        DataInputStream input = new DataInputStream(client.getInputStream());
        name = input.readUTF();
        System.out.println("Connecte a l'hote "+name);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        ois = new ObjectInputStream(input);
        OutputStream output = client.getOutputStream();
        oos = new ObjectOutputStream(output);
    }
    public static void attente() throws IOException{
            System.out.println(in.readLine());
    }
    
    public static void main(String[] args) throws IOException {
        try{
            connexion();
        }catch(IOException e){    System.out.println("Serveur non disponible");   }
        //attente();
    }
    
}
