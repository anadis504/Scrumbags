package Scrumbags.logic;

import java.util.Objects;

/**
     * Kirja on olio, joka sisältää kaiken tiedon tietokantaan 
     * lisättävästi kirjasta.
     * Riippuvuus luokkaan Service.
     */
public class Book {
    /**Kirjan nimi*/
    String name;
    /**Kirjan kirjailija*/
    String author;
    /**Kirjan isbn-tunnus*/
    String isbn;
    /**Kirjan sivunumero*/
    int pages;
    /**Kirjan julkaisuvuosi*/
    int year;
    
    /**
     * Kirjan konstruktori, joka määrittää ainoastaan kirjan nimen
     * ja kirjailijan nimen. Lopuille arvoille annetaan placeholder-
     * arvo.
     * @param name = kirjan nimi
     * @param author = kirjan kirjailija
     */
    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        this.isbn = "---";
        this.pages = -1;
        this.year = -1;
    }
    
    /**
     * Kirjan konstruktori, joka määrittää kirjalle kaikki parametrit
     * @param name = kirjan nimi
     * @param author = kirjan kirjailija
     * @param isbn = kirjan isbn-tunnus. (null-arvo = "---")
     * @param pages = kirjan sivunumero. (null-arvo = -1)
     * @param year
     */
    public Book(String name, String author, String isbn, int pages, int year) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
        this.year = year;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }
    
    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "\nNimi: "+getName()+"\nKirjailija: "+getAuthor()
                +"\nISBN: "+getIsbn()+"\nSivumäärä: "+getPages()
                +"\nJulkaisuvuosi: " + getYear();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        return true;
    }
    
}
