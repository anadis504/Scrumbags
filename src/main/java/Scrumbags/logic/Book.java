package Scrumbags.logic;


public class Book {
    String name;
    String author;
    String isbn;
    int year;
    int pages;
    
    public Book(String name, String author) {
        this.name = name;
        this.author = author;
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
    
    
}
