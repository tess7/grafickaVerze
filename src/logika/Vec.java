/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.Map;
import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/*******************************************************************************
 * Instance třídy Vec představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Vec extends ImageView
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private Map<String,Vec>seznamVeci;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelnost)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.seznamVeci = new HashMap<>();
        Image obrazek = new Image(main.AdventuraZakladni.class.getResourceAsStream("/zdroje/"+nazev+".png"), 100, 100, false, false);
        this.setImage(obrazek);
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev(){
        return nazev;
    }
    
    public boolean jePrenositelna(){
        return prenositelnost;
    }
    //vypsání názvů věcí dostupných v místnostech
    public String getSeznamVeci() {
        String nazvy = " ";

            for (String nazevVeci : seznamVeci.keySet()){
                nazvy += nazevVeci + " ";
            }
        
        return nazvy;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
