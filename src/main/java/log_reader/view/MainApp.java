package log_reader.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import log_reader.controller.MainController;
import log_reader.core.FileHolder;

import java.io.File;

public class MainApp extends Application{

    private BorderPane pane = new BorderPane();
    HBox box = new HBox();
    private TreeView<FileHolder> directoryTree = new TreeView<>();
    private TabPane tabPane = new TabPane();
    private Button chooseDirectory = new Button();
    private Button openItem = new Button();
    private Button searchButton = new Button();
    private TextField textToSearch = new TextField();
    private TextField extension = new TextField();
    private TextField pathToFolder = new TextField();
    private File choice;
    private MainController controller = new MainController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        chooseDirectory.setText("Choose directory");
        chooseDirectory.setOnAction(event->
                choice = controller.openFolder(
                        primaryStage,
                        pathToFolder));
        openItem.setText("Open item");
        openItem.setOnAction(event ->
                controller.readFile(directoryTree, tabPane));
        searchButton.setText("Search");
        searchButton.setOnAction(event ->
                controller.startSearch(
                        choice,
                        directoryTree,
                        textToSearch,
                        extension));
        textToSearch.setPromptText("Text to search");
        extension.setText("log");
        extension.setPromptText("Extension");
        box.getChildren().addAll(chooseDirectory,
                pathToFolder,
                textToSearch,
                extension,
                searchButton,
                openItem);
        pane.setTop(box);
        pane.setLeft(directoryTree);
        pane.setCenter(tabPane);
        Scene scene = new Scene(pane, 800, 480);
        scene.getStylesheets().add("styles.css");
        primaryStage.setTitle("Log reader");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}