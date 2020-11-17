
package Scrumbags.ui;
import java.util.Scanner;
    /**
     * Olio, jolla toteutetaan Scanner-luokka käyttöliittymään
     * injektoimalla, jotta käyttöliittymän automaattinen testaaminen 
     * on mahdollista
     */
public class KonsoliIO implements IO {
    private Scanner lukija;
    /**
     * KonsoliIO:n parametriton konstruktori
     */
    public KonsoliIO() {
        lukija = new Scanner(System.in);
    }    
    /**
     * Metodilla voidaan skannerilta int-arvoja
     */
    public int nextInt() {
        return lukija.nextInt();
    }
    /**
     * Metodilla voidaan skannerilta String-arvoja
     */
    public String nextLine() {
        return lukija.nextLine();
    }
    /**
     * Metodilla voidaan tulostaa tekstiä
     *
     * @param m = tulostettava teksti.
     */
    public void print(String m) {
        System.out.println(m);
    }
    
}
