package log_reader.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import log_reader.core.FileHolder;

import java.io.File;
import java.io.IOException;

public class MainApp extends Application{
    private static final int MAX_TABS_NUMBER = 5;

    private BorderPane pane = new BorderPane();
    HBox box = new HBox();
    private TreeView<FileHolder> directoryTree = new TreeView<>();
    private TabPane tabPane = new TabPane();
    private Button chooseDirectory = new Button();
    private Button openItem = new Button();
    private Button searchButton = new Button();
    private TextField textToSearch = new TextField();
    private TextField extension = new TextField();
    private File choice;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
//        String resource = "/fxml/start.fxml";
//        FXMLLoader loader = new FXMLLoader();
//        pane = loader.load(getClass().getResourceAsStream(resource));
        chooseDirectory.setText("Choose directory");
        chooseDirectory.setOnAction(event->
                showDirectories(primaryStage));
        openItem.setText("Open item");
        openItem.setOnAction(event -> readFile());
        searchButton.setText("Search");
        searchButton.setOnAction(event -> startSearch());
        textToSearch.setPromptText("Text to search");
        extension.setText("log");
        extension.setPromptText("Extension");
        box.getChildren().addAll(chooseDirectory,
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

    private void showDirectories(Stage stage){
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File(System.getProperty("user.home")));
        File choice = dc.showDialog(stage);
        if(choice != null){
            if(!choice.isDirectory()){
                AlertMaker.showAlert("Could not open directory");
            }else {
                this.choice = choice;
            }
        }
    }

    private void readFile(){
        if(tabPane.getTabs().size() < MAX_TABS_NUMBER){
            TabMaker tabMaker = new TabMaker();
            tabMaker.openTab(tabPane, directoryTree.getSelectionModel().getSelectedItem());
        }else {
            AlertMaker.showAlert("Max tabs` number is " + MAX_TABS_NUMBER);
        }
    }

    private void startSearch(){
        TreeMaker maker = new TreeMaker();
        if(choice == null){
            AlertMaker.showAlert("Could not open directory");
        }
        directoryTree.setRoot(maker.getNodesForDirectory(choice,
                textToSearch.getText(),
                extension.getText()));
    }
}