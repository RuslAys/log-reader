package log_reader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application{
    public static void main(String[] args) {
//        DirectoryExplorer explorer = new DirectoryExplorer();
//        List<FileHolder> holder =
//                explorer.getAllFilesWithText(
//                        "/home/ruslan/Desktop/test",
//                        ".log",
//                        "te");
//
//        System.out.println(holder);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "/fxml/start.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
        primaryStage.setTitle("Log reader");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
