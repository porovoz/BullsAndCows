package bullsandcows;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BullsAndCowsApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
    primaryStage.setTitle("Bulls and Cows Game");
    Pane root = FXMLLoader.load(getClass().getResource("/bullsandcows/main.fxml"));
    var scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
    }
}
