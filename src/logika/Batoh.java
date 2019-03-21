/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logika;
import java.util.Map;
import java.util.HashMap;




/*******************************************************************************
 * Instance třídy {@code Batoh} představují ...
 * The {@code Batoh} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */

public class Batoh
{
     //== Datové atributy (statické i )==========================================
     // nastavení kapacity batohu na 2, nebude možné v batohu mít více věcí
    private static final int KAPACITA = 2 ;
    private Map<String, Vec> seznamVeci ;   

    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    /***************************************************************************
     * Konstruktor seznam věci v batohu
     */
    public Batoh()
    {
        seznamVeci = new HashMap<>();
    }

    //== Nesoukromé metody=====================================================
    /**
     * Vloží věc do batohu, pokud se tam vejde
     * @return true když se věc vloží, false, pokud se nevložila
     * @param objekt třídy Vec
     */
    public boolean vlozVec (Vec vec) {
        if (seznamVeci.size() < KAPACITA ) 
        {
            seznamVeci.put(vec.getNazev(), vec);
            return true;
        }
        return false;
    }

    /**
     * Napíše info o věcech v batohu
     */
    public boolean obsahujeVec (String jmenoVeci) {
        return seznamVeci.containsKey(jmenoVeci);
    }

    /**
     * Vrátí věc z batohu
     */
    public Vec vyberVec (String jmenoVeci) {
        Vec nalezenaVec;
        if (seznamVeci.containsKey(jmenoVeci)) {
            nalezenaVec = seznamVeci.get(jmenoVeci);
            seznamVeci.remove(jmenoVeci);
            return nalezenaVec;
        }
        return null;
    }

    /**
     * Vypíše věci, které jsou v batohu
     */
    public String nazvyVeciVBatohu() {
        String nazvy = "věci v batohu: ";
        for (String jmenoVeci : seznamVeci.keySet()){
            nazvy += jmenoVeci + " ";
        }
        return nazvy;
    }

    /**
     * Vypíše kapacitu batohu
     */
    public int getKapacita() {
        return KAPACITA;
    }
    
    public Map<String, Vec> getSeznamVeci()
    {
        return this.seznamVeci;
    }
}