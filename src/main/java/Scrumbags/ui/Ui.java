package Scrumbags.ui;

// Tästä pitänee tehdä injektio
import java.util.Scanner;

    /**
     * Lukuvinkkipalvelun tekstipohjainen käyttöliittymä
     */
public class Ui {
    
    private Scanner reader;
    
     /**
     * Käyttöliittymän parametriton konstruktori
     */
    public Ui(){
        reader = new Scanner(System.in);
    }
    
    /**
     * Käyttöliittymän käynnistys
     */
    public void run(boolean go){
        String komento = "";
        while(go){
            
            System.out.println("Anna komento (q tai add)");
            komento = reader.nextLine();
            
            if(komento.equals("q")){
                go = false;
            } else if(komento.equals("add")){
                System.out.println("Lisäys");
            }
        }
        
    }
}


    

