package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Chat");

        // Устанавливаем иконку приложения.
        //this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
