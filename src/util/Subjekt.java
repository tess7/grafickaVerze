/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author buchalc
 */
public interface Subjekt
{

    /**
     * Metoda slouží k zaregistrování pozorovatele, musí to být instance třídy, která implementuje
     * rozhraní ObserverZmenyProstoru.
     *
     * @param pozorovatel registrovaný objekt
     */
    public void zaregistrujPozorovatele(Observer pozorovatel);

    /**
     * Metoda slouží k zrušení registrace pozorovatele, musí to být instance třídy, která
     * implementuje rozhraní ObserverZmenyProstoru.
     *
     * @param pozorovatel objekt, který již nechce být informován o změnách
     */
    public void odregistrujPozorovatele(Observer pozorovatel);

    /**
     * Metoda, která se volá vždy, když dojde ke změně této instance. Upozorní všechny pozorovatele,
     * že došlo ke změně tak, že zavolá jejich metodu aktualizuj.
     */
    public void upozorniPozorovatele();

}
