package log_reader.view;

public class TreeMaker {
    public TreeItem<String> getNodesForDirectory(File directory){
        TreeItem<String> root = new TreeItem(directory.getName());
    }

}
