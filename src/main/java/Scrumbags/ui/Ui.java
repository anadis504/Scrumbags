package Scrumbags.ui;

// Tästä pitänee tehdä injektio
import java.util.Scanner;
import Scrumbags.database.*;
import Scrumbags.logic.*;
import java.util.ArrayList;

/**
 * Lukuvinkkipalvelun tekstipohjainen käyttöliittymä
 */
public class Ui {

    private IO io;
    private Service service;
    private String[] str;
    private Dao database;
    private String komento;

    /**
     * Käyttöliittymän parametriton konstruktori,joka luo käyttjän syötteitä
     * lukevan olion KonsoliIO, joka toteuttaa rajapinnan IO
     */
    public Ui() throws Exception {
        this.database = new Database("jdbc:sqlite:library.db");
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
                    + "q) Poistu ohjelmasta\n"
                    + "1) Lisää kirja\n"
                    + "2) Lisää linkki\n"
                    + "3) Hae\n\n"
                    + "Anna komennon numero:");
            komento = io.nextLine();
            /**
             * lukuvinkkien lisääminen käyttäjän syötteestä
             */
            if (komento.equals("q")) {
                go = false;
            } else if (komento.equals("1")) {
                addBook();
            } else if (komento.equals("2")) {
                addLink();
            } else if (komento.equals("3")) {
                search();
            }
        }
    }

    private void search() {
        io.print("Haetaanko:");
        io.print("1) kirjaa");
        io.print("2) linkkiä?");
        komento = io.nextLine();
        if (komento.equals("1")) {
            searchBook();
        } else if (komento.equals("2")) {
            searchLink();
        } //virheellinen komento
    }

    private void searchBook() {
        io.print("Valitse hakuperuste:");
        io.print("1) kirjailijan nimi");
        io.print("2) kirjan nimi");
        io.print("3) julkaisuvuosi");
        io.print("4) ISBN");
        komento = io.nextLine();

        if (komento.equals("1") || komento.equals("2")) {
            io.print("Syötä haettava nimi:");
        } else if (komento.equals("3") || komento.equals("4")) {
            io.print("Syötä haettava luku:");
        } //virheellinen komento
        String search = io.nextLine();
        ArrayList<Book> booklist = this.service.getBooks(search, komento);
        if (booklist != null) {
            for (Book b : booklist) {
                io.print(b.toString());
            }
        } else {
            io.print("Ei tuloksia.");
        }
    }

    private void searchLink() {
        io.print("Minkä nimistä linkkiä etsitään?");
        String name = io.nextLine();
        ArrayList<Link> linklist = this.service.getLinksByName(name);
        if (linklist != null) {
            for (Link l : linklist) {
                io.print(l.toString());
            }
        } else {
            io.print("Ei tuloksia.");
        }
    }

    private void addBook() {
        String nimi;
        String kirjailija;
        String ISBN;
        int sivumaara;
        int julkaisuvuosi;

        io.print("Anna kirjan nimi (pakollinen tieto).");
        komento = io.nextLine();
        while (komento.isEmpty()) {
            io.print("Anna syöte (vähintään yhden merkin pituinen).");
            komento = io.nextLine();
        }
        if (komento.equals("q")) {
            nimi = "---";
        } else {
            nimi = komento;
        }

        io.print("Anna kirjailijan nimi (ohita syöttämällä \"q\").");
        komento = io.nextLine();
        while (komento.isEmpty() && !checkIfQ(komento)) {
            io.print("Anna syöte tai kirjoita \"q\"");
            komento = io.nextLine();
        }
        if (komento.equals("q")) {
            kirjailija = "---";
        } else {
            kirjailija = komento;
        }

        io.print("Anna ISBN (ohita syöttämällä \"q\").");
        komento = io.nextLine();
        while (komento.isEmpty() && !checkIfQ(komento)) {
            io.print("Anna syöte tai kirjoita \"q\"");
            komento = io.nextLine();
        }
        if (komento.equals("q")) {
            ISBN = "---";
        } else {
            ISBN = komento;
        }

        io.print("Anna kirjan sivumäärä (ohita syöttämällä \"q\")");
        komento = io.nextLine();
        while (!checkIfNumber(komento) && !checkIfQ(komento)) {
            komento = io.nextLine();
        }
        if (checkIfQ(komento)) {
            sivumaara = -1;
        } else {
            sivumaara = Integer.parseInt(komento);
        }

        io.print("Anna kirjan julkaisuvuosi (ohita syöttämällä \"q\")");
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
                + "ONKO OK? [k/e]");
        if (yesNo()) {
            if (service.addBook(nimi, kirjailija, ISBN, sivumaara, julkaisuvuosi)) {
                io.print("Kirja lisätty onnistuneesti.");
            } else {
                io.print("Kirjaa ei onnistuttu lisäämään.");
            }
        }
    }

    private void addLink() {
        String nimi;
        String URL;
        io.print("Anna Linkin nimi (pakollinen).");
        komento = io.nextLine();
        while (komento.isEmpty()) {
            io.print("Anna syöte (vähintään yhden merkin pituinen).");
            komento = io.nextLine();
        }
        nimi = komento;

        io.print("Anna URL (pakollinen).");
        komento = io.nextLine();
        while (komento.isEmpty()) {
            io.print("Anna syöte (vähintään yhden merkin pituinen).");
            komento=io.nextLine();
        }
        URL = komento;
        
        io.print("LISÄTÄÄN URL: \n"
                + "NIMI: " + nimi + "\n"
                + "URL: " + URL + "\n"
                + "ONKO OK? [k/e]");
        if (yesNo()) {
            if (service.addLink(nimi, URL)) {
                io.print("Linkki lisätty onnistuneesti.");
            } else {
                io.print("Linkkiä ei onnistuttu lisäämään.");
            }
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
            if (komento.equals("y") || komento.equals("k")) {
                return true;
            } else if (komento.equals("n") || komento.equals("e")) {
                return false;
            }
            io.print("Onko OK? Syötä k (kyllä, kirja lisätään) tai e (ei, lisäys peruutetaan).");
        }
    }
}
