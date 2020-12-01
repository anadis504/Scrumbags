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
                    + "3) Hae tai poista\n"
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
            } /*else if (komento.equals("4")) {
                remove();
            }*/
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
            int i = 1;
            for (Book b : booklist) {
                io.print("nro: " + i + ". " + b.toString() + "\n");
                i++;
            }
            io.print("Haluatko poistaa jonkun kirjoista: [k/e]");
            komento = io.nextLine();
        
            if (komento.equals("k")) {
                removeBook(booklist);
            }
        } else {
            io.print("Ei tuloksia.");
        }
    }
    
    private void removeBook(ArrayList<Book> booklist) {
        io.print("Anna kirjan nro, jonka haluat poistaa");
        komento = io.nextLine();
        this.service.removeBook(booklist, komento);
    }

    private void searchLink() {
        io.print("Minkä nimistä linkkiä etsitään?");
        String name = io.nextLine();
        ArrayList<Link> linklist = this.service.getLinksByName(name);
        if (linklist != null) {
            int i = 1;
            for (Link l : linklist) {
                io.print("nro: " + i + ". " + l.toString() + "\n");
                i++;
            }
            io.print("Haluatko poistaa jonkun linkeistä: [k/e]");
            komento = io.nextLine();
        
            if (komento.equals("k")) {
                removeLink(linklist);
            }
        } else {
            io.print("Ei tuloksia.");
        }
    }
    
    private void removeLink(ArrayList<Link> linklist) {
        io.print("Anna linkin nro, jonka haluat poistaa");
        komento = io.nextLine();
        this.service.removeLink(linklist, komento);
    }

    private void addBook() {
        String nimi;
        String kirjailija;
        String ISBN;
        int sivumaara;
        int julkaisuvuosi;

        io.print("Anna kirjan nimi (pakollinen tieto).");
        nimi = promptTextInput("Anna kirjan nimi (vähintään yhden merkin pituinen).", false);

        io.print("Anna kirjailijan nimi (ohita syöttämällä \"q\").");
        kirjailija = promptTextInput("Anna kirjailijan nimi tai ohita syöttämällä \"q\"", true);

        io.print("Anna ISBN (ohita syöttämällä \"q\").");
        ISBN = promptTextInput("Anna ISBN tai ohita syöttämällä \"q\"", true);

        io.print("Anna kirjan sivumäärä (ohita syöttämällä \"q\")");
        sivumaara = promptNumberInput("Anna sivumäärä tai ohita syöttämällä \"q\"", true);

        io.print("Anna kirjan julkaisuvuosi (ohita syöttämällä \"q\")");
        julkaisuvuosi = promptNumberInput("Anna vuosiluku tai ohita syöttämällä \"q\"", true);

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
        nimi = promptTextInput("Anna Linkin nimi (vähintään yhden merkin pituinen).", false);

        io.print("Anna URL (pakollinen).");
        URL = promptTextInput("Anna URL (vähintään yhden merkin pituinen).", false);

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

    private String promptTextInput(String messageIfInvalid, boolean canSkipWithQ) {
        String input = io.nextLine();
        while (true) {
            if (canSkipWithQ && input.equals("q")) {
                return "---";
            }
            if (!input.isEmpty()) {
                return input;
            }
            io.print(messageIfInvalid);
            input = io.nextLine();
        }
    }

    private int promptNumberInput(String messageIfInvalid, boolean canSkipWithQ) {
        String input = io.nextLine();
        while (true) {
            if (canSkipWithQ && input.equals("q")) {
                return -1;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                io.print(messageIfInvalid);
                input = io.nextLine();
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
