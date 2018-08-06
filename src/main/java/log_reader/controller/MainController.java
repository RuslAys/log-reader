package log_reader.controller;

import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import log_reader.core.FileExplorer;
import log_reader.core.FileHolder;
import log_reader.view.AlertMaker;
import log_reader.view.TabMaker;

import java.io.File;

public class MainController {
    private static final int MAX_TABS_NUMBER = 5;

    public void readFile(TreeView<FileHolder> directoryTree, TabPane tabPane){
        if(tabPane.getTabs().size() < MAX_TABS_NUMBER){
            TabMaker tabMaker = new TabMaker();
            tabMaker.openTab(tabPane,
                    directoryTree.getSelectionModel().getSelectedItem());
        }else {
            AlertMaker.showAlert("Max tabs` number is " + MAX_TABS_NUMBER);
        }
    }

    public void startSearch(File choice,
                             TreeView<FileHolder> directoryTree,
                             TextField textToSearch,
                             TextField extension){
        if(choice == null){
            AlertMaker.showAlert("Could not open directory");
        }else {
            directoryTree.setRoot(getNodesForDirectory(choice,
                    textToSearch.getText(),
                    extension.getText()));
        }
    }

    public File openFolder(Stage stage,
                           TextField pathToFolder){
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File(System.getProperty("user.home")));
        File choice = dc.showDialog(stage);
        if(choice != null){
            if(!choice.isDirectory()){
                AlertMaker.showAlert("Could not open directory");
            }else {
                pathToFolder.setText(choice.getAbsolutePath());
            }
        }
        return choice;
    }

    private TreeItem<FileHolder> getNodesForDirectory(File directory,
                                                     String text,
                                                     String ext){
        FileHolder holder = new FileHolder(directory);
        TreeItem<FileHolder> root = new TreeItem<>(holder);
        File[] fileList = directory.listFiles();
        if(fileList != null){
            for(File f: fileList){
                if(f.isDirectory()){
                    root.getChildren().add(getNodesForDirectory(f, text, ext));
                }else {
                    if(isRightExtension(f, ext)){
                        FileExplorer explorer = new FileExplorer();
                        FileHolder fileHolder = explorer.findTextInFile(f, text);
                        if(fileHolder.getIndexes().size() != 0){
                            root.getChildren().add(new TreeItem<>(fileHolder));
                        }
                    }
                }
            }
        }
        return root;
    }

    private boolean isRightExtension(File file, String ext){
        return file.getName().endsWith(ext);
    }
}
