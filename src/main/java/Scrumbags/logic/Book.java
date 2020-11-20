package Scrumbags.logic;

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
     * @param isbn = kirjan julkaisuvuosi. (null-arvo = -1)
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

    public int getPages() {
        return pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "\nNimi: "+getName()+"\nKirjailija: "+getAuthor()
                +"\nISBN: "+getIsbn()+"\nSivumäärä: "+getPages()
                +"Julkaisuvuosi: " + getYear();
    }
}
