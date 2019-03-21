/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logika;





/*******************************************************************************
 * Instance třídy {@code PrikazVypisObsahBatohu} představují ...
 * The {@code PrikazVypisObsahBatohu} class instances represent ...
 *
 * @author Tereza Kloboučková
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class PrikazVypisObsahBatohu implements IPrikaz
{
    private static final String NAZEV = "batoh";
    private HerniPlan plan;
    public PrikazVypisObsahBatohu(HerniPlan plan)
    {
        this.plan = plan;
    }
    //příkaz pro vypsání obsahu batohu
    @Override
    public String provedPrikaz(String... parametry) 
    {
            Batoh batoh = plan.getBatoh();
            String text = "V Batohu jsou věci: ";
            for(String nazev : batoh.getSeznamVeci().keySet())
            {
                text += " " + nazev;
            }
        
            return text;
    }
    
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
