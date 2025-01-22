package model;

import javafx.scene.control.TextField;

public class validateTextField {
    public void validateTextInt(TextField txt) {
        txt.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.isEmpty()) {
                    Double.parseDouble(newValue);
                }
            } catch (NumberFormatException e) {
                txt.setText(oldValue);
            }
        });
    }
}
