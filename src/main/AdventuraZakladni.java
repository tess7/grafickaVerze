/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 *
 * @author buchalc
 */
public class AdventuraZakladni extends Application {

    private BorderPane border;
    private BorderPane pravaCastObrazovky;
    private BorderPane veciVMistnosti;
    private BorderPane veciVBatohu;
    private TextArea centerTextArea;
    private FlowPane dolniFlowPane;
    private Label zadejPrikazLabel;
    private Label veci;
    private Label batoh;
    private TextField prikazTextField;
    private ListView<String> vychodyListView;
    private IHra hra;
    private PanelProstoru oknoProstoru;
    private PanelVychodu panelVychodu;
    private PanelBatohu panelBatohu;
    private PanelVeciMistnost panelVeci;
    private MenuBar menuBar;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        // Uložení odkazu na aktuální stage
        this.stage = primaryStage;
        // Vytvoření hry
        hra = new Hra();
        // Vytvoření základního okna
        border = new BorderPane();
        
        // Prostřední část okna - dialogové okno
        centerTextArea = nastavTextArea(hra);
        border.setCenter(centerTextArea);
        
        // Spodní část okna -  textbox pro ovládání hry
        nastavDolniPanel(hra);
        border.setBottom(dolniFlowPane);
        // Horní část okna - mapa a dialogové okno
        oknoProstoru = new PanelProstoru(hra.getHerniPlan());
        border.setTop(oknoProstoru.getPanel());
        
        // Vytvoření panelu pro východy a věci v místnosti a v batohu
        panelVychodu = new PanelVychodu(hra.getHerniPlan(),centerTextArea,prikazTextField);
        panelVeci = new PanelVeciMistnost(hra.getHerniPlan(),centerTextArea);
        panelBatohu = new PanelBatohu(hra.getHerniPlan(),centerTextArea);
        
        // Vytvoření pomocného borderPanu pro věci v místnosti
        // Do něj je vložen panel obrazovky a popiskový label a panel východů
        pravaCastObrazovky = new BorderPane();
        veciVMistnosti = new BorderPane();
        veci = new Label("Věci v místnosti");
        veciVMistnosti.setTop(veci);
        veciVMistnosti.setCenter(panelVeci.getList());
        pravaCastObrazovky.setLeft(veciVMistnosti);
        pravaCastObrazovky.setRight(panelVychodu.getList());
        
         // Totéž pro panel batohu
        border.setRight(pravaCastObrazovky);
        veciVBatohu = new BorderPane();
        batoh = new Label("Věci v batohu");
        veciVBatohu.setTop(batoh);
        veciVBatohu.setCenter(panelBatohu.getList());
        border.setLeft(veciVBatohu);
        // Vytvoření menu
        initMenu();
        // Definice velikosti okna
        Scene scene = new Scene(new VBox(), 1000, 850);
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, border);

        primaryStage.setTitle("Grafická adventura - Únik z hradu");
        primaryStage.setScene(scene);
        prikazTextField.requestFocus();
        primaryStage.show();
    }
    
    // Metoda pro vytvoření menu
    // Vytvoří se záložky a přidají se jim ikonky a metody
    private void initMenu() {
        menuBar = new MenuBar();
        // --- Menu Soubor
        Menu menuSoubor = new Menu("Menu");
        MenuItem novaHra = new MenuItem("Nová hra",
                new ImageView(new Image(AdventuraZakladni.class.getResourceAsStream("/zdroje/new.png"))));
        novaHra.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // Po kliknutí na novou hru se znovu zavolá metoda start
                // Tím dojde k přemazání současného stavu a vytvoření nové hry
                start(stage);
            }
        });

        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        MenuItem konec = new MenuItem("Konec",
                new ImageView(new Image(AdventuraZakladni.class.getResourceAsStream("/zdroje/end.png"))));
        konec.setMnemonicParsing(true);

        konec.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // Po zmáčknutí konce hry se okno vypne
                System.exit(0);
            }
        });

        menuSoubor.getItems().addAll(novaHra, new SeparatorMenuItem(), konec);

        // Část pro menu s nápovědou, která ukáže informace o programu
        Menu menuNapoveda = new Menu("Nápověda");
        MenuItem oProgramu = new MenuItem("Informace o programu");
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // obsluha události O programu
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Grafická adventura - Únik z hradu");
                alert.setHeaderText("Únik z hradu je jednoduchá grafická adventura");
                alert.setContentText("Verze 1.1.3");
                alert.showAndWait();
            }
        });
        // Ukázání nápovědy
        MenuItem napovedaKAplikaci = new MenuItem("Nápověda k aplikaci");
        napovedaKAplikaci.setAccelerator(KeyCombination.keyCombination("F1"));
        napovedaKAplikaci.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // obsluha události Nápověda k aplikaci
                // sekundární okno
                Stage stage = new Stage();
                stage.setTitle("Nápověda k adventuře");
                WebView webview = new WebView();
                webview.getEngine().load(
                        AdventuraZakladni.class.getResource("/zdroje/napoveda.html").toExternalForm()
                );
                stage.setScene(new Scene(webview, 500, 500));
                stage.show();
            }
        });

        menuNapoveda.getItems().addAll(oProgramu, new SeparatorMenuItem(), napovedaKAplikaci);

        menuBar.getMenus().addAll(menuSoubor, menuNapoveda);

    }

   
    // Vytvoření spodní části s textboxem pro ovládání hry
    public void nastavDolniPanel(IHra hra) {
        dolniFlowPane = new FlowPane();
        dolniFlowPane.setAlignment(Pos.CENTER);
        zadejPrikazLabel = new Label("Zadej příkaz: ");
        prikazTextField = new TextField();
        prikazTextField.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String radek = prikazTextField.getText();
                String text = hra.zpracujPrikaz(radek);
                hra.getHerniPlan().upozorniPozorovatele();
                centerTextArea.appendText("\n\n" + radek + "\n");
                centerTextArea.appendText("\n" + text + "\n");
                prikazTextField.setText("");
                if (hra.konecHry()) {
                    prikazTextField.setEditable(false);
                }
            }

        });

        dolniFlowPane.getChildren().addAll(zadejPrikazLabel, prikazTextField);
    }

    public TextArea nastavTextArea(IHra hra) {
        TextArea centerTextArea = new TextArea();
        centerTextArea.setText(hra.vratUvitani());
        centerTextArea.setEditable(false);
        return centerTextArea;
    }


    /**
     * @param args the command line arguments
     */
    // Metoda kterou se hra spouští
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else if (args[0].equals("-text")) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } else {
            System.out.println("Neplatný parametr");
        }

    }

}
