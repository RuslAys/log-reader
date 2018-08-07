package log_reader.controller;

import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import log_reader.core.FileExplorer;
import log_reader.core.FileHolder;
import log_reader.core.StreamCreator;
import log_reader.view.AlertMaker;
import log_reader.view.TabMaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

public class MainController {


    public void readFile(TreeView<FileHolder> directoryTree, TabPane tabPane){
        TabMaker tabMaker = new TabMaker();
        tabMaker.openTab(tabPane,
                directoryTree.getSelectionModel().getSelectedItem());
    }

    public void startSearch(File choice,
                             TreeView<FileHolder> directoryTree,
                             TextField textToSearch,
                             TextField extension) {
        if(choice == null){
            AlertMaker.showAlert("Could not open directory");
        }else {
            try {
                directoryTree.setRoot(getNodesForDirectory(choice,
                        textToSearch.getText(),
                        extension.getText()));
            }catch (FileNotFoundException e){
                e.printStackTrace();
                AlertMaker.showAlert("Could not open file");
            }
        }
    }

    public void startSearchSharedFolder(String ipAddress,
                                        String folderName,
                                        String domain,
                                        String username,
                                        String password,
                                        String textToSearch,
                                        String extension,
                                        TreeView<FileHolder> directoryTree){
        FileExplorer explorer = new FileExplorer();
        SmbFile file = explorer.readSharedFolder(
                ipAddress,
                folderName,
                domain,
                username,
                password
        );
        try {
            directoryTree.setRoot(
                    getNodesForSmbDirectory(
                            file, textToSearch, extension));
        }catch (Exception e){
            e.printStackTrace();
            AlertMaker.showAlert("Something wrong with shared folder");
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
                                                     String ext) throws FileNotFoundException {
        FileHolder holder = new FileHolder();
        holder.setFile(directory);
        TreeItem<FileHolder> root = new TreeItem<>(holder);
        File[] fileList = directory.listFiles();
        if(fileList != null){
            for(File f: fileList){
                if(f.isDirectory()){
                    root.getChildren().add(getNodesForDirectory(f, text, ext));
                }else {
                    if(isRightExtension(f, ext)){
                        FileExplorer explorer = new FileExplorer();
                        InputStreamReader isr = new InputStreamReader(
                                StreamCreator.getStream(f)
                        );
                        FileHolder fileHolder = explorer.findTextInFile(isr, text, f);
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

    public TreeItem<FileHolder> getNodesForSmbDirectory(SmbFile directory,
                                                        String text,
                                                        String ext) throws SmbException, MalformedURLException, UnknownHostException {
        FileHolder holder = new FileHolder();
        holder.setSmbFile(directory);
        TreeItem<FileHolder> root = new TreeItem<>(holder);
        SmbFile[] fileList = directory.listFiles();
        if(fileList != null){
            for(SmbFile f: fileList){
                if(f.isDirectory()){
                    root.getChildren().add(getNodesForSmbDirectory(f, text, ext));
                }else {
                    if(isRightSmbExtension(f, ext)){
                        FileExplorer explorer = new FileExplorer();
                        InputStreamReader isr = new InputStreamReader(
                                StreamCreator.getStream(f)
                        );
                        FileHolder fileHolder = explorer.findTextInFile(isr, text, f);
                        if(fileHolder.getIndexes().size() != 0){
                            root.getChildren().add(new TreeItem<>(fileHolder));
                        }
                    }
                }
            }
        }
        return root;
    }

    private boolean isRightSmbExtension(SmbFile file, String ext){
        return file.getName().endsWith(ext);
    }
}
