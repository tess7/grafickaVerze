/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazSeber představují ...
 *
 * @author    Tereza Kloboučková
 * @version   0.00.000
 */
public class PrikazVyhod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vyhod";
    private HerniPlan plan;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    //parametr - název věci se kterou chceme provést příkaz vyhod
    //metoda vyhod vezme věc v batohu a uloží ji do aktuálního prostoru
    public PrikazVyhod(HerniPlan plan)
    {
        this.plan = plan;
    }
    
    @Override
    public String provedPrikaz(String... parametry) 
    {
        if (parametry.length == 0) 
        {
            return "Co mám vyhodit ? Zadej název věci.";
        }

        String nazev = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec vec = plan.getBatoh().vyberVec(nazev);
        
        
        if(vec != null)
        {
            aktualniProstor.vlozVec(vec);
            return "Do místnosti jsi vyhodil věc " + vec.getNazev();
        }
        else
        {
          return "Takovou věc v batohu nemáš.";
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


