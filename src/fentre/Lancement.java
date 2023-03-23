/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fentre;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Anthony.K
 */
public class Lancement {
    
    public static void main(String[] args) throws IOException {
        File file = new File("fenetre.bat");
        Desktop des = Desktop.getDesktop();
        des.open(file);
    }
    
}
