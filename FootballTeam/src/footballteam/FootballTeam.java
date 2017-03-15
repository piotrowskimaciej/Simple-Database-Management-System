package footballteam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FootballTeam
 * Glowna klasa programu rozszerzona przez klase Application,
 * w ktorej inicjcjowane jest okno programu za pomoca metody start(),
 * a nastepnie wywolana w metodzie main()
 * @author maciejpiotrowski
 */
public class FootballTeam extends Application {
    
    /**
     * start()
     * @param stage
     * @throws Exception 
     * Odziedziczona metoda, odpowiadajaca za wyswietlenie okna programu
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * main()
     * Metoda opdowiadajaca za wywolanie calego projeku poprez metode launch()
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
}
