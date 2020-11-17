package Scrumbags.ui;

// Tästä pitänee tehdä injektio
import java.util.Scanner;

    /**
     * Lukuvinkkipalvelun tekstipohjainen käyttöliittymä
     */
public class Ui {
    
    private IO io;
    
     /**
     * Käyttöliittymän parametriton konstruktori,joka luo käyttjän syötteitä
     * lukevan olion KonsoliIO, joka toteuttaa rajapinnan IO
     */
    public Ui(){
        this.io = new KonsoliIO();
    }
    
    /**
     * Käyttöliittymän käynnistys
     */
    public void run(boolean go){
        String komento = "";
        while(go){
            
            System.out.println("Anna komento (q tai add)");
            komento = io.nextLine();
            
            if(komento.equals("q")){
                go = false;
            } else if(komento.equals("add")){
                System.out.println("Lisäys");
            }
        }
        
    }
}


    

