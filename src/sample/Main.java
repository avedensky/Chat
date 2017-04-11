package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*

If you want to use chat on network computers, you need:
1. Add the service.policy file to the application's start directory.
2. Run program with option (for java VM): -Djava.security.policy = server.policy

Content service.policy file:
grant {
    permission java.security.AllPermission;
};

Additional:
https://ru.wikipedia.org/wiki/RMI
*/

/**
 * Created by Alexey Vedensky on 10.04.2017.
 * MAIL: avedensky@gmail.com
 */
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
