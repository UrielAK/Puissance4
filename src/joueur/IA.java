/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joueur;

import java.util.Random;

/**
 *
 * @author Anthony.K
 */
public class IA extends Joueur {
    
    private int level;
    
    public IA(){
        super();
    }
    public IA(int lvl,int q){
        super(q);
        this.level = lvl;
    }

    public int getLevel() {
        return level;
    }
    
    
    
    @Override
    public int place(char[][] c){
            System.out.println("Tour de l'"+super.getNom());
            Random random = new Random();
            int retour = -1;
        if(this.level==1){
            retour = random.nextInt(c[0].length);
        }else if(level==2){
            char caractere = (this.q%2==0 ? 'X' : 'O');
            int countx = 0;
            int county = 0;
            //Verification horizontale
            for (int j=0;j<c.length;j++){
                for (int i=0;i<c[0].length-1;i++){
                    if (c[j][i]==caractere){
                    countx++;
                }else{
                    countx=0;
                }
                    if (countx > 1){
                        if(j<c.length && c[j][i+1]=='.')
                            if(j<c.length-1 && c[j+1][i+1]!='.')
                                retour = i+1;
                            else
                                retour = i+1;
                        
                        break;
                    }
                }
            }

            //verification verticale
            for (int j=0;j<c[0].length;j++)
                for (int i=c.length-1;i>0;i--){
                    if (c[i][j]==caractere){
                        county++;
                    }else{
                        county=0;
                    }
                        if (county > 1 && retour==-1){
                            if(c[i-1][j]=='.')
                                retour = j;
                            
                            break;
                        }
                }
            //verification oblique montante

            for (int i=c.length-1;i>c.length-4;i--)
                    for (int j=0;j<c[0].length-3;j++)
                            if (c[i][j]==caractere && c[i-1][j+1]==caractere && c[i-2][j+2]==caractere){
                                if(c[i-2][j+3]!='.' && retour==-1)
                                    retour = j+3;
                                
                                break;
                            }

            //verification diagonale descendante

               for (int i=c.length-4;i>=0;i--)
                    for (int j=0;j<c[0].length-3;j++)
                            if (c[i][j]==caractere && c[i+1][j+1]==caractere && c[i+2][j+2]==caractere){
                                   if(c[i+3][j+3]=='.' && retour == -1)
                                       retour = j;
                                   
                                   break;
                            }
            if(retour==-1)
                retour = random.nextInt(c[0].length);
        }
        return retour;
    }
}