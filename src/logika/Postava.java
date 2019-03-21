/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logika;





/*******************************************************************************
 * Instance třídy {@code Postava} představují ...
 * The {@code Postava} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Postava
{
    private String jmeno;
    private String text;

    public Postava(String jmeno,String text)
    {
        this.jmeno = jmeno;
        this.text = text;
    }

    
    public String getJmeno()
    {
        return this.jmeno;
    }
    
    public String getText()
    {
        return this.text;
    }
}
