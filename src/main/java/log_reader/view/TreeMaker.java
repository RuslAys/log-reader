package log_reader.view;

import javafx.scene.control.TreeItem;
import log_reader.core.FileExplorer;
import log_reader.core.FileHolder;

import java.io.File;

public class TreeMaker {
    TreeItem<FileHolder> getNodesForDirectory(File directory,
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