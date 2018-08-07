package log_reader.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import log_reader.controller.MainController;
import log_reader.core.FileHolder;

import java.io.File;

public class MainApp extends Application{

    private BorderPane pane = new BorderPane();
    private TreeView<FileHolder> directoryTree = new TreeView<>();
    private TabPane tabPane = new TabPane();
    private Button chooseDirectory = new Button();
    private Button openItem = new Button();
    private Button searchButton = new Button();
    private Button searchSharedFolder = new Button();
    private TextField textToSearch = new TextField();
    private TextField extension = new TextField();
    private TextField pathToFolder = new TextField();
    private TextField ipAddress = new TextField();
    private TextField sharedFolder = new TextField();
    private TextField domain = new TextField();
    private TextField username = new TextField();
    private PasswordField password = new PasswordField();

    private File choice;
    private MainController controller = new MainController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        HBox boxTop = new HBox();
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
        boxTop.getChildren().addAll(chooseDirectory,
                pathToFolder,
                textToSearch,
                extension,
                searchButton);

        HBox boxBot = new HBox();
        ipAddress.setPromptText("IP Address");
        sharedFolder.setPromptText("Shared folder");
        domain.setPromptText("Domain");
        username.setPromptText("Username");
        password.setPromptText("Password");
        searchSharedFolder.setText("Search shared folder");
        searchSharedFolder.setOnAction(event -> controller.startSearchSharedFolder(
                ipAddress.getText(),
                sharedFolder.getText(),
                domain.getText(),
                username.getText(),
                password.getText(),
                textToSearch.getText(),
                extension.getText(),
                directoryTree
        ));
        boxBot.getChildren().addAll(
                ipAddress,
                sharedFolder,
                domain,
                username,
                password,
                searchSharedFolder
        );

        VBox topVBox = new VBox();
        topVBox.getChildren().addAll(boxTop, boxBot, openItem);

        pane.setTop(topVBox);
        pane.setLeft(directoryTree);
        pane.setCenter(tabPane);
        Scene scene = new Scene(pane, 800, 480);
        scene.getStylesheets().add("styles.css");
        primaryStage.setTitle("Log reader");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}