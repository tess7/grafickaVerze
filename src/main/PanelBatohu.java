/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import logika.HerniPlan;
import logika.Vec;
import util.Observer;


public class PanelBatohu implements Observer{
    
    private HerniPlan plan;
    ListView<Vec> list;
    ObservableList<Vec> data;
    
    private TextArea centralText;

    public PanelBatohu(HerniPlan plan,TextArea text) {
       this.plan = plan;
       plan.zaregistrujPozorovatele(this);
       centralText = text;
        init();
    }

    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(125);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
                @Override
                public void handle(MouseEvent click)
                {
                    if(!plan.getHra().konecHry())
                    {
                            Vec kliknuta = list.getSelectionModel().getSelectedItem();
                            
                            if(!kliknuta.getNazev().equals(""))
                            {
                            String vstupniPrikaz = "vyhod "+kliknuta.getNazev();
                            String odpovedHry = plan.getHra().zpracujPrikaz("vyhod "+kliknuta.getNazev());


                            centralText.appendText("\n" + vstupniPrikaz + "\n");
                            centralText.appendText("\n" + odpovedHry + "\n");

                            plan.upozorniPozorovatele();
                            }
                    }
                }
            
        });
        aktualizuj();
    }
    
    /*
    * Metoda vrací list.
    */
    public ListView<Vec> getList() {
        return list;
    }

    @Override 
    public void aktualizuj() 
    {        
        Map<String,Vec> seznam;
        seznam = plan.getBatoh().getSeznamVeci();
        data.clear();
        
        for (String pomocny : seznam.keySet()) 
        {
        Vec pomocna = seznam.get(pomocny);
        data.add(pomocna);
        }
    }
    
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        this.aktualizuj();
    }



}
