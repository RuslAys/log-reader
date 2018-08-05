package log_reader.view;

import javafx.scene.control.Alert;

class AlertMaker {
    static void showAlert(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(text);
        alert.showAndWait();
    }
}
