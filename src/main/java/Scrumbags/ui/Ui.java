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
    private DatabaseFake database = new DatabaseFake();
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

    /**
     * Käyttöliittymän käynnistys
     */
    public void run(boolean go) {
        System.out.println("Tervetuloa!");
        while (go) {
            System.out.println("\nkomennot:\n"
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
        String author = io.nextLine();
        this.service.getBooks(author);
    }

    private void addBook() {
        String nimi;
        String kirjailija;
        String ISBN;
        int sivumaara;
        int julkaisuvuosi;

        System.out.println("Anna kirjan nimi.");
        nimi = io.nextLine();

        System.out.println("Anna kirjailijan nimi.");
        kirjailija = io.nextLine();
        if (kirjailija.equals("q")) {
            kirjailija = "---";
        }

        System.out.println("Anna ISBN.");
        ISBN = io.nextLine();
        if (ISBN.equals("q")) {
            ISBN = "---";
        }

        System.out.println("Anna kirjain sivumäärä");
        komento = io.nextLine();
        while (!checkIfNumber(komento) && !checkIfQ(komento)) {
            komento = io.nextLine();
        }
        if (checkIfQ(komento)) {
            sivumaara = -1;
        } else {
            sivumaara = Integer.parseInt(komento);
        }

        System.out.println("Anna kirjain julkaisuvuosi");
        komento = io.nextLine();
        while (!checkIfNumber(komento) && !checkIfQ(komento)) {
            komento = io.nextLine();
        }
        if (checkIfQ(komento)) {
            julkaisuvuosi = -1;
        } else {
            julkaisuvuosi = Integer.parseInt(komento);
        }

        System.out.println("LISÄTÄÄN KIRJA: \n"
                + "NIMI: " + nimi + "\n"
                + "KIRJAILIJA: " + kirjailija + "\n"
                + "ISBN: " + ISBN + "\n"
                + "SIVUMÄÄRÄ: " + sivumaara + "\n"
                + "JULKAISUVUOSI: " + julkaisuvuosi + "\n"
                + "ONKO OK? [y/n]");
        if (yesNo()) {
            service.addBook(nimi, kirjailija, ISBN, sivumaara, julkaisuvuosi);
        }
    }

    private void addLink() {
        String nimi;
        String URL;
        System.out.println("Anna Linkin nimi.");
        nimi = io.nextLine();

        System.out.println("Anna URL.");
        URL = io.nextLine();

        System.out.println("LISÄTÄÄN URL: \n"
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
            System.out.println("Anna numero tai kirjoita \"q\"");
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
