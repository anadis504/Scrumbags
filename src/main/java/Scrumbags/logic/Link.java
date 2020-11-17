
package Scrumbags.logic;


public class Link {
    String adress;
    String name;
    
    public Link(String name, String adress) {
        this.adress = adress;
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
