package log_reader.view;

import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import log_reader.core.FileExplorer;
import log_reader.core.FileHolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TabMaker {

    void openTab(TabPane pane, TreeItem<FileHolder> item){
        Tab tab = new Tab();
        FileHolder holder = item.getValue();
        File file = holder.getFile();

        tab.setText(file.getName());
        ListView<String> listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setCellFactory(TextFieldListCell.forListView());
        listView.setOnEditCommit(event ->
            listView.getItems().set(event.getIndex(), event.getNewValue()));
        listView.setEditable(true);

        FileExplorer fileExplorer = new FileExplorer();
        fileExplorer.addTextToListView(file, listView);

        tab.setContent(listView);
        pane.getTabs().add(tab);
    }
}