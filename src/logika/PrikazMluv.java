/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazSeber představují ...
 *
 * @author    Tereza Kloboučková
 * @version   0.00.000
 */
public class PrikazMluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
//     private Kosik kosik;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    //parametr - jméno postavy
    public PrikazMluv(HerniPlan plan)
    {
        this.plan = plan;
    }
    
    @Override
    public String provedPrikaz(String... parametry) 
    {
        if (parametry.length == 0) 
        {
            return "S kým mám mluvit ? Zadej jméno postavy";
        }

        String nazev = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor aktualniProstor = plan.getAktualniProstor();
        
        
        if(aktualniProstor.obsahujePostavu(nazev))
        {
            return aktualniProstor.getPostava(nazev).getText();
        }
        else
        {
          return "Taková postava zde není.";
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}

    //== Soukromé metody (instancí i třídy) ========================================


