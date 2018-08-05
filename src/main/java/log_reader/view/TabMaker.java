package log_reader.view;

import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
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
//        StyleClassedTextArea bigTextArea = new StyleClassedTextArea();
        ListView<String> listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setCellFactory(TextFieldListCell.forListView());
        listView.setOnEditCommit(event -> {
            listView.getItems().set(event.getIndex(), event.getNewValue());
        });
        listView.setEditable(true);
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
//            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null){
//                sb.append(s);
//                sb.append("\n");
                listView.getItems().add(s);
            }
//            bigTextArea.appendText(sb.toString());
//            bigTextArea.setShowCaret(Caret.CaretVisibility.ON);

//            for(int i = 0; i < 10; i+=3){
//                bigTextArea.setStyleClass(i, i+1, "red");
//            }

        }catch (IOException e){
            e.printStackTrace();
        }
//        tab.setContent(bigTextArea);
        tab.setContent(listView);
        pane.getTabs().add(tab);
    }
}