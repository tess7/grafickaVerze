package logika;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import util.Observer;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Tereza Kloboučková
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan{
    
    private Prostor aktualniProstor;
    private Batoh batoh;
    private Hra hra;
    private ArrayList<Vec> seznamKlicu;
    private List<Observer> seznamPozorovatelu;
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví komnatu.
     */
    public HerniPlan(Hra hra) 
    {
        seznamKlicu = new ArrayList<Vec>();
        seznamPozorovatelu = new ArrayList<>();
        zalozProstoryHry();
        this.hra = hra;
        batoh = new Batoh();
    }
    
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví komnatu.
     */
    private void zalozProstoryHry() 
    {
        // vytvářejí se jednotlivé prostory
        Prostor okno = new Prostor("okno","okno s výhledem na krajinu",420,60);
        Prostor komnata = new Prostor("komnata", "komnata již nežijícího krále ",320,70);
        Prostor skrin = new Prostor("skrin","stará skříň",460,60);
        Prostor zasypanaChodba = new Prostor("zasypanaChodba","zasypaná chodba",230,60);
        Prostor tajnaChodba = new Prostor("tajnaChodba","tajná chodba, kterou se lze dostat do několika místností",300,180);
        Prostor kralovninaKomnata = new Prostor("kralovninaKomnata","komnata královny",230,180);
        Prostor kuchyn = new Prostor("kuchyn","kuchyň, kde se dříve vařívaly dobroty",320,280);
        Prostor mrazirna = new Prostor("mrazirna","zde se uchovávaly potraviny",420,280);
        Prostor vez = new Prostor("vez","věž, ve které sídlí otec Fura",50,60);
        Prostor vinarna = new Prostor("vinarna","vinárna plná prázdných i plných lahví",140,280);
        Prostor brana = new Prostor("brana","cesta ven",420,180);
        
        
         
        // přiřazují se průchody mezi prostory (sousedící prostory)
        komnata.setVychod(okno);
        komnata.setVychod(zasypanaChodba);
        zasypanaChodba.setVychod(komnata);
        komnata.setVychod(skrin);
        skrin.setVychod(komnata);
        komnata.setVychod(tajnaChodba);
        tajnaChodba.setVychod(komnata);
        kralovninaKomnata.setVychod(tajnaChodba);
        tajnaChodba.setVychod(kralovninaKomnata);
        tajnaChodba.setVychod(brana);
        brana.setVychod(tajnaChodba);
        kuchyn.setVychod(tajnaChodba);
        tajnaChodba.setVychod(kuchyn);
        kuchyn.setVychod(mrazirna);
        kuchyn.setVychod(vinarna);
        vinarna.setVychod(kuchyn);
        vinarna.setVychod(vez);
        vez.setVychod(vinarna);

        //věci pro sbírání, true - přenositelné, false - nepřenositelné
        Vec klic_1 = new Vec("klic_1",true);
        Vec klic_2 = new Vec("klic_2",true);
        Vec pochoden = new Vec("pochoden",true);
        Vec jablko = new Vec("jablko",true);
        Vec stul = new Vec("stul",false);
        Vec lahev = new Vec ("lahev", true);
        Vec plast = new Vec ("plast", true);
        Vec maso = new Vec ("maso", true);
        Vec postel = new Vec ("postel", false);

        
        kuchyn.vlozVec(jablko);
        komnata.vlozVec(pochoden);
        kuchyn.vlozVec(stul);
        vinarna.vlozVec(lahev);
        skrin.vlozVec(plast);
        kuchyn.vlozVec(maso);
        kralovninaKomnata.vlozVec(postel);
        
        
        Random rand = new Random();
        seznamKlicu.add(klic_1);
        seznamKlicu.add(klic_2);
        
        for( Vec klic : seznamKlicu )
        {
            int  cislo = rand.nextInt(8) + 1;
            switch (cislo)
            {
                case 1: 
                    komnata.vlozVec(klic);
                    break;
                case 2: 
                    kuchyn.vlozVec(klic);
                    break;
                case 3: 
                    vez.vlozVec(klic);
                    break;
                case 4: 
                    skrin.vlozVec(klic);
                    break;
                case 5: 
                    kralovninaKomnata.vlozVec(klic);
                    break;
                case 6: 
                    vinarna.vlozVec(klic);
                    break;
                case 7: 
                    zasypanaChodba.vlozVec(klic);
                    break;
                case 8: 
                    tajnaChodba.vlozVec(klic);
                    break;
            }
        }
        
        Postava fura = new Postava("Fura","Ale ale, kdopak to přišel? Nejsi-li ty ten člověk, který byl uspán a unesen sem na hrad? Přeji hodně štěstí v hledání cesty ven.");
        
        vez.vlozPostavu(fura);
        
        aktualniProstor = komnata;  // hra začíná v komnatě
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve kterém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    
    public Batoh getBatoh()
    {
        return this.batoh;
    }
    
    public Hra getHra()
    {
        return this.hra;
    }
    
    public ArrayList<Vec> getSeznamKlicu()
    {
        return this.seznamKlicu;
    }
    
    public void zaregistrujPozorovatele(Observer pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }

    public void odregistrujPozorovatele(Observer pozorovatel) {
        seznamPozorovatelu.remove(pozorovatel);
    }

    public void upozorniPozorovatele() 
    {
        for (Observer pozorovatel : seznamPozorovatelu) 
        {
            pozorovatel.aktualizuj();
        }
    }
    
}
