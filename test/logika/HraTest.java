package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import logika.Hra;
import logika.Vec;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testVyhry() 
    {
        assertEquals(false, hra1.konecHry());
        Vec klic_1 = new Vec("klic_1",true);
        Vec klic_2 = new Vec("klic_2",true);
        
        hra1.getHerniPlan().getBatoh().vlozVec(klic_1);
        hra1.getHerniPlan().getBatoh().vlozVec(klic_2);
        
        
        hra1.zpracujPrikaz("jdi tajnáChodba");
        hra1.zpracujPrikaz("odemkni brána");
        assertEquals(true, hra1.konecHry());
    }
    @Test
    //test smrti vstoupením do lokace okno
    public void testProhry1() 
    {
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi okno");
        assertEquals(true, hra1.konecHry());
    }
    //test smrti vstoupením do lokace mrazírna
    @Test
    public void testProhry2() 
    {
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi tajnáChodba");
        hra1.zpracujPrikaz("jdi kuchyn");
        hra1.zpracujPrikaz("jdi mrazírna");
        assertEquals(true, hra1.konecHry());
    }
    //test smrti vstoupením do lokace tajná chodba bez pochodně v batohu
    @Test 
    public void testProhry3() 
    {
      
    }
    //test smrti sebráním otráveného jablka
    @Test 
    public void testProhry4() 
    {
        
    }
    //test, že Random rand opravdu hází náhodná čísla od 1 do 8
    @Test 
    public void testNahodnosti()
    {
         Random rand = new Random();
        int  cislo = rand.nextInt(8) + 1;
        
        
        assertTrue(cislo >= 1); 
        assertTrue(cislo <= 8);
    }
    //test, že opravdu počítání věcí v batohu funguje a že batoh nedovolí sebrat více než 2 věci, nelze sebrat nesebratelné věci
    @Test 
    public void testSbiraniVyhazovani()
    {
       
    }
    @Test
    public void testPrikazMluv()
    {
        hra1.zpracujPrikaz("jdi tajnáChodba");
        hra1.zpracujPrikaz("jdi kuchyn");
        hra1.zpracujPrikaz("jdi vinárna");
        hra1.zpracujPrikaz("jdi věž");        
        //hra1.zpracujPrikaz("mluv Fura");
        assertEquals("Ale ale, kdopak to přišel? Nejsi-li ty ten člověk, který byl uspán a unesen sem na hrad? Přeji hodně štěstí v hledání cesty ven.", hra1.zpracujPrikaz("mluv Fura"));
}
 @Test 
    public void testNesmyslnyPrikaz()
    {
       hra1.zpracujPrikaz("jdi tajnáChodba");   
       assertEquals("Nevím co tím myslíš? Tento příkaz neznám. ", hra1.zpracujPrikaz("běž kuchyn"));
    }

}
