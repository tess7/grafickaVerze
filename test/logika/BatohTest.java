/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logika;



import logika.Batoh;
import logika.Vec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code BatohTest} slouží ke komplexnímu otestování
 * třídy {@link BatohTest}.
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class BatohTest
{
    private Batoh batoh;
    private Vec vec1;
    private Vec vec2;
    private Vec vec3;
    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        batoh = new Batoh();
        vec1 = new Vec("a",true);
        vec2 = new Vec("b",true);
        vec3 = new Vec("c",true);
    }


    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }


    /***************************************************************************
     * Test of the {@link #setUp()} method preparing the test fixture.
     */
    @Test
    public void testKapacity()
    {
        assertEquals(0, batoh.getSeznamVeci().size());
        batoh.vlozVec(vec1);
        assertEquals(1, batoh.getSeznamVeci().size());
        batoh.vlozVec(vec2);
        assertEquals(2, batoh.getSeznamVeci().size());
        batoh.vlozVec(vec3);
        assertEquals(2, batoh.getSeznamVeci().size());
    }

}
