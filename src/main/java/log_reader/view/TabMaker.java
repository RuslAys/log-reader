package log_reader.view;

import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import log_reader.controller.TabController;
import log_reader.core.FileExplorer;
import log_reader.core.FileHolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class TabMaker {
    private static final int MAX_TABS_NUMBER = 5;

    private FileHolder holder;
    private ListView<String> listView = new ListView<>();
    private TabController controller = new TabController();

    public void openTab(TabPane pane, TreeItem<FileHolder> item) throws FileNotFoundException {
        if(pane.getTabs().size() < MAX_TABS_NUMBER){
            Tab tab = new Tab();
            holder = item.getValue();
            File file = holder.getFile();

            tab.setText(file.getName());
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            listView.setCellFactory(TextFieldListCell.forListView());
            listView.setOnEditCommit(event ->
                listView.getItems().set(event.getIndex(), event.getNewValue()));
            listView.setEditable(true);

            FileExplorer fileExplorer = new FileExplorer();
            InputStreamReader isr = new InputStreamReader(
                    new FileInputStream(file)
            );
            fileExplorer.addTextToListView(isr, listView);

            Button down = new Button();
            down.setText("Down");
            down.setOnAction(event ->
                controller.scrollDownAndSelectItem(listView, holder));

            Button up = new Button();
            up.setText("Up");
            up.setOnAction(event ->
                controller.scrollUpAndSelectItem(listView, holder));

            Button selectAll = new Button();
            selectAll.setText("Select all");
            selectAll.setOnAction(event ->
                controller.selectAll(listView));
            VBox vBox = new VBox();
            HBox hBox = new HBox();
            hBox.getChildren().addAll(up, down, selectAll);
            vBox.getChildren().addAll(hBox, listView);

            tab.setContent(vBox);
            pane.getTabs().add(tab);
        }else {
            AlertMaker.showAlert("Max tabs` number is " + MAX_TABS_NUMBER);
        }
    }
}