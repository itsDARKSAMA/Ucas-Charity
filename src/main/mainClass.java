package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Abdelrahman Al-Majayda
 * @version 1.0
 * @since   2020-04-3
 * @link https://github.com/itsdarksama
 *
 */

public class mainClass extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Login/loginPanel.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Ucas Chairty");
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        stage.setAlwaysOnTop(true);

        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
