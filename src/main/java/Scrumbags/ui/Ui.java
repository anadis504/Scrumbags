package Scrumbags.ui;

// Tästä pitänee tehdä injektio
import java.util.Scanner;
import Scrumbags.database.*;
import Scrumbags.logic.*;

    /**
     * Lukuvinkkipalvelun tekstipohjainen käyttöliittymä
     */
public class Ui {
    
    private IO io;
    private Service service;
    private String[] str;
    // private DatabaseFake database = new DatabaseFake();
    private Database database;
    
    public Ui() throws Exception {
        this.database = new Database("jdbc:sqlite:library.db");
        
        this.io = new KonsoliIO();
        this.service = new Service(database);
    }
    
    /**
     * Käyttöliittymän käynnistys
     */
    public void run(boolean go){
        String komento = "";
        System.out.println("Tervetuloa!");
        while(go){
            System.out.println("\nkomennot:\n"
                            +"q\n"
                            +"add book\n"
                            +"add link\n\n"
                            +"Anna komento:");
            komento = io.nextLine();
            /**
             * lukuvinkkien lisääminen käyttäjän syötteestä
             */
            if(komento.equals("q")){
                go = false;
            } else if(komento.equals("add book")){
                System.out.println("Anna kirjan nimi ja kirjailija muodossa 'nimi/kirjailija'");
                komento = io.nextLine();
                str = komento.split("/");
                service.addBook(str[0], str[1]);
            } else if(komento.equals("add link")){
                System.out.println("Anna linkin nimi ja URL-osoite muodossa 'nimi/URL'");
                komento = io.nextLine();
                str = komento.split("/");
                service.addLink(str[0], str[1]);
            }
        }
        
    }
}


    

