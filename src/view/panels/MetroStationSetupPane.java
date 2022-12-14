package view.panels;

import controller.MetroStationSetupPaneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MetroFacade;
import model.database.MetrocardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.MetroCardsExcelLoadSaveStrategy;
import model.database.loadSaveStrategies.MetrocardsTekstLoadSaveStrategy;

import java.io.IOException;

public class MetroStationSetupPane extends GridPane {
    private MetroStationSetupPaneController metroStationSetupPaneController;


    public MetroStationSetupPane(MetroFacade metro) {
        this.metroStationSetupPaneController = new MetroStationSetupPaneController(metro, this);
        VBox root = new VBox();
        root.setPadding(new Insets(5, 5, 5, 5));
        RadioButton button1 = new RadioButton("excel");
        button1.setUserData(new MetroCardsExcelLoadSaveStrategy());
        RadioButton button2 = new RadioButton("txt");
        button2.setUserData(new MetrocardsTekstLoadSaveStrategy());
        Button saveButton = new Button("save");
        this.add(saveButton, 50, 50, 1, 1);
        ToggleGroup group = new ToggleGroup();
        button1.setToggleGroup(group);
        button2.setToggleGroup(group);

        button1.setSelected(true);

        root.getChildren().addAll(button1,button2);
        saveButton.setOnAction(event -> {
            try {
                metroStationSetupPaneController.save((LoadSaveStrategy) group.getSelectedToggle().getUserData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.add(root,1,1);
    }



}
