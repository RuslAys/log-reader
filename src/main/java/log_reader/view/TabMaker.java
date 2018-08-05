package log_reader.view;

import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import log_reader.core.FileExplorer;
import log_reader.core.FileHolder;

import java.io.File;

public class TabMaker {

    private int i = 0;
    private FileHolder holder;
    ListView<String> listView = new ListView<>();

    void openTab(TabPane pane, TreeItem<FileHolder> item){
        Tab tab = new Tab();
        holder = item.getValue();
        File file = holder.getFile();

        tab.setText(file.getName());
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setCellFactory(TextFieldListCell.forListView());
        listView.setOnEditCommit(event ->
            listView.getItems().set(event.getIndex(), event.getNewValue()));
//        listView.setEditable(true);

        FileExplorer fileExplorer = new FileExplorer();
        fileExplorer.addTextToListView(file, listView);

        Button down = new Button();
        down.setText("Down");
        down.setOnAction(event -> {
            if(i < holder.getIndexes().size()){
                selectItem();
                i++;
            }
        });

        Button up = new Button();
        up.setText("Up");
        up.setOnAction(event -> {
            if(i > 0){
                selectItem();
                i--;
            }
        });

        Button selectAll = new Button();
        selectAll.setText("Select all");
        selectAll.setOnAction(event ->
            listView.getSelectionModel().selectAll());
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(up, down, selectAll);
        vBox.getChildren().addAll(hBox, listView);

        tab.setContent(vBox);
        pane.getTabs().add(tab);
    }

    private void selectItem(){
        int pos = holder.getIndexes().get(i).getRow();
        listView.getSelectionModel().clearSelection();
        listView.getSelectionModel().select(pos);
        listView.scrollTo(pos);
    }
}