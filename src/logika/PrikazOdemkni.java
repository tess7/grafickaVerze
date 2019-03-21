/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logika;





/*******************************************************************************
 * Instance třídy {@code PrikazOdemkniBranu} představují ...
 * The {@code PrikazOdemkniBranu} class instances represent ...
 *
 * @author  Tereza Kloboučková
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class PrikazOdemkni implements IPrikaz
{    
    private static final String NAZEV = "odemkni";
    private HerniPlan plan;

    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    /***************************************************************************
     * Konstruktor
     * @param plan, herní plán, po kterém se ve hře prochází
     */
    public PrikazOdemkni(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
    
     *
     *@param parametry - název věci kterou chci odemknout, v této hře pouze brána
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
        if (parametry.length == 0) 
        {
            return "Co mám odemknout? Musíš zadat název ";
        }

        String nazev = parametry[0];
        //pokud se nacházím v tajné chodbě a chci odemknout bránu, musím mít v batohu 2 klíče
        
        if(nazev.equals("brana"))
        {
            if(plan.getAktualniProstor().getNazev().equals("tajnaChodba"))
            {
                boolean pomocna = true;
                
                for(Vec klic : plan.getSeznamKlicu())
                {
                    if(!plan.getBatoh().obsahujeVec(klic.getNazev()))
                    {
                        pomocna = false;
                    }
                }
                
                if(pomocna)
                {
                    plan.getHra().setKonecHry(true);
                    return "Vyhrál jsi. Konec hry.";
                }
                else
                {
                    return "Pro odemknutí brány potřebuješ všechny klíče.";
                }
            }
            else
            {
                return "Odemknout bránu lze jen z místnosti tajnáChodba.";
            }
        }
        else
        {
            return "Odemknout lze jen bránu.";
        }
    }
    
    @Override
    public String getNazev() {
        return NAZEV;
    }
}