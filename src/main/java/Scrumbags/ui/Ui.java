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
    private Dao database = new DatabaseFake();
    private String komento;

    /**
     * Käyttöliittymän parametriton konstruktori,joka luo käyttjän syötteitä
     * lukevan olion KonsoliIO, joka toteuttaa rajapinnan IO
     */
    public Ui() {
        this.io = new KonsoliIO();
        this.service = new Service(database);
        this.komento = "";
    }
    
    //Riippuvuuksien injektointia varten, jotta testaus onnistuu
    public Ui(IO io, Service service) {
        this.io = io;
        this.service = service;
        this.komento = "";
    }

    /**
     * Käyttöliittymän käynnistys
     */
    public void run(boolean go) {
        io.print("Tervetuloa!");
        while (go) {
            io.print("\nkomennot:\n"
                    + "q\n"
                    + "add book\n"
                    + "add link\n"
                    + "search\n\n"
                    + "Anna komento:");
            komento = io.nextLine();
            /**
             * lukuvinkkien lisääminen käyttäjän syötteestä
             */
            if (komento.equals("q")) {
                go = false;
            } else if (komento.equals("add book")) {
                addBook();
            } else if (komento.equals("add link")) {
                addLink();
            } else if (komento.equals("search")) {
                search();
            }
        }
    }

    private void search() {
        System.out.println("Minkä kirjailijan kirjat etsitään?");
        String author = io.nextLine();
        this.service.getBooksByAuthor(author);
    }

    private void addBook() {
        String nimi;
        String kirjailija;
        String ISBN;
        int sivumaara;
        int julkaisuvuosi;

        io.print("Anna kirjan nimi.");
        nimi = io.nextLine();

        io.print("Anna kirjailijan nimi.");
        kirjailija = io.nextLine();
        if (kirjailija.equals("q")) {
            kirjailija = "---";
        }
        
        io.print("Anna ISBN.");
        ISBN = io.nextLine();
        if (ISBN.equals("q")) {
            ISBN = "---";
        }

        io.print("Anna kirjain sivumäärä");
        komento = io.nextLine();
        while (!checkIfNumber(komento) && !checkIfQ(komento)) {
            komento = io.nextLine();
        }
        if (checkIfQ(komento)) {
            sivumaara = -1;
        } else {
            sivumaara = Integer.parseInt(komento);
        }

        io.print("Anna kirjain julkaisuvuosi");
        komento = io.nextLine();
        while (!checkIfNumber(komento) && !checkIfQ(komento)) {
            komento = io.nextLine();
        }
        if (checkIfQ(komento)) {
            julkaisuvuosi = -1;
        } else {
            julkaisuvuosi = Integer.parseInt(komento);
        }

        io.print("LISÄTÄÄN KIRJA: \n"
                + "NIMI: " + nimi + "\n"
                + "KIRJAILIJA: " + kirjailija + "\n"
                + "ISBN: " + ISBN + "\n"
                + "SIVUMÄÄRÄ: " + sivumaara + "\n"
                + "JULKAISUVUOSI: " + julkaisuvuosi + "\n"
                + "ONKO OK? [y/n]");
         if (yesNo()) {
            if(service.addBook(nimi, kirjailija, ISBN, sivumaara, julkaisuvuosi)) {
                io.print("Kirja lisätty onnistuneesti.");
            } else {
            io.print("Kirjaa ei onnistuttu lisäämään.");
            }
         }
    }

    private void addLink() {
        String nimi;
        String URL;
        io.print("Anna Linkin nimi.");
        nimi = io.nextLine();

        io.print("Anna URL.");
        URL = io.nextLine();
        io.print("LISÄTÄÄN URL: \n"
                + "NIMI: " + nimi + "\n"
                + "URL: " + URL + "\n"
                + "ONKO OK? [y/n]");
        if (yesNo()) {
            service.addLink(nimi, URL);
        }
    }

    private boolean checkIfNumber(String sana) {
        if (sana == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(sana);
        } catch (NumberFormatException nfe) {
            io.print("Anna numero tai kirjoita \"q\"");
            return false;
        }
        return true;
    }

    private boolean checkIfQ(String sana) {
        if (sana.equals("q")) {
            return true;
        }
        return false;
    }

    private boolean yesNo() {
        while (true) {
            komento = io.nextLine();
            if (komento.equals("y")) {
                return true;
            } else if (komento.equals("n")) {
                return false;
            }
        }
    }
}
