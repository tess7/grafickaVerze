/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazSeber představují ...
 *
 * @author    Tereza Kloboučková
 * @version   0.00.000
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Batoh batoh;
//     private Kosik kosik;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazSeber(HerniPlan plan)
    {
        this.plan = plan;
        this.batoh = batoh;
//         kosik = plan.getKosik();
    }
    


    //== Nesoukromé metody (instancí i třídy) ======================================
    
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Co mám sebrat? Musíš zadat název věci";
        }

        String nazevVeci = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec sbirana = aktualniProstor.najdiVec(nazevVeci);
        
        if(sbirana != null)
        {
            //kontrola, zda vůbec můžeme předmět zvednout a dát si ho do batohu
            if(sbirana.jePrenositelna())
            {
                //ošetření smrti pouhým zvednutím jablka
                if(sbirana.getNazev().equals("jablko"))
                {
                    plan.getHra().setKonecHry(true);
                    return "Otrávil jsi se jablkem. Umřel jsi. Hra končí.";
                }
                //sebrání věci do batohu
                if (plan.getBatoh().vlozVec(sbirana)){
               
                    
                    return "Věc "+ nazevVeci+ " mizí z aktuálního prostoru do tvého baťůžku.";
                }
                //ošetření kapacity batohu - pokud chceme sebrat více věcí než je kapacita batohu, vyhodí "error"
                else
                {
                    return "V batohu je málo místa - vyhoď něco."; 
                }
            }
            else
            {
                return "Tato věc sebrat nejde.";
            }   
        }
        else
        {
        return "To tu není";
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


