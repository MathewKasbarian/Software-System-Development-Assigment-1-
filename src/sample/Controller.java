package sample;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;

public class Controller {
    @FXML
    public TextField Accuracy;
    @FXML
    public TextField Precision;
    @FXML
    public TableColumn SpamProbability;
    @FXML
    public TableColumn ActualClass;
    @FXML
    public TableColumn File;
    @FXML
    public TableView tableView;
    @FXML public void initialize() throws FileNotFoundException {
        File.setCellValueFactory(new PropertyValueFactory<>("filename"));
        ActualClass.setCellValueFactory(new PropertyValueFactory<>("actualClass"));
        SpamProbability.setCellValueFactory(new PropertyValueFactory<>("spamProbRounded"));
        tableView.setItems(Testing.getAllFiles());
        Accuracy.setText(Float.toString(Testing.getAccuracy()));
        Precision.setText(Float.toString(Testing.getPrecision()));
    }
}
