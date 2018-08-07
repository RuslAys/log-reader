package log_reader.controller;

import javafx.scene.control.ListView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import log_reader.core.FileHolder;

import java.util.ArrayList;
import java.util.List;

public class TabController {
    private int i = 0;
    public void scrollUpAndSelectItem(ListView<String> listView, FileHolder holder){
        scrollAndSelectItem(listView, holder);
        if(i > 0) --i;
        else i = 0;
    }

    public void scrollDownAndSelectItem(ListView<String> listView, FileHolder holder){
        scrollAndSelectItem(listView, holder);
        if(i < holder.getIndexes().size()) i++;
        else i = holder.getIndexes().size() - 1;
    }

    private void scrollAndSelectItem(ListView<String> listView, FileHolder holder){
        int pos = holder.getIndexes().get(i);
        listView.getSelectionModel().clearSelection();
        listView.getSelectionModel().select(pos);
        listView.scrollTo(pos);
    }

    public void selectAll(ListView<String> listView){
        listView.getSelectionModel().selectAll();
    }

    public void copySelectedFiles(ListView<String> listView){
        List<String> copiedStrings = new ArrayList<>(
                listView.getSelectionModel().getSelectedItems()
        );
        StringBuilder stringBuilder = new StringBuilder();
        for (String item: copiedStrings){
            stringBuilder.append(item);
            stringBuilder.append("\n");
        }
        ClipboardContent content = new ClipboardContent();
        content.putString(stringBuilder.toString());
        Clipboard.getSystemClipboard().setContent(content);
    }
}
