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
        this.setVgap(5);
        this.setHgap(5);

        RadioButton button1 = new RadioButton("excel");
        button1.setUserData(new MetroCardsExcelLoadSaveStrategy());
        button1.setPadding(new Insets(5, 0, 5, 0));
        RadioButton button2 = new RadioButton("txt");
        button2.setUserData(new MetrocardsTekstLoadSaveStrategy());
        button2.setPadding(new Insets(5, 0, 5, 0));
        ToggleGroup group = new ToggleGroup();
        button1.setToggleGroup(group);
        if (metroStationSetupPaneController.read("database").contains("MetroCardsExcelLoadSaveStrategy")) button1.setSelected(true);
        button2.setToggleGroup(group);
        if (metroStationSetupPaneController.read("database").contains("MetroCardsTekstLoadSaveStrategy")) button2.setSelected(true);

        CheckBox age64PlusDiscount = new CheckBox("64+ age discount");
        age64PlusDiscount.setPadding(new Insets(5, 0, 5, 0));
        if (metroStationSetupPaneController.read("discount").contains("age64PlusDiscount")) age64PlusDiscount.setSelected(true);
        CheckBox christmasLeaveDiscount = new CheckBox("Christmas leave discount");
        christmasLeaveDiscount.setPadding(new Insets(5, 0, 5, 0));
        if (metroStationSetupPaneController.read("discount").contains("christmasLeaveDiscount")) christmasLeaveDiscount.setSelected(true);
        CheckBox studentDiscount = new CheckBox("Student discount");
        studentDiscount.setPadding(new Insets(5, 0, 5, 0));
        if (metroStationSetupPaneController.read("discount").contains("studentDiscount")) studentDiscount.setSelected(true);
        CheckBox frequentTravellerDiscount = new CheckBox("Frequent traveller discount");
        frequentTravellerDiscount.setPadding(new Insets(5, 0, 5, 0));
        if (metroStationSetupPaneController.read("discount").contains("frequentTravellerDiscount")) frequentTravellerDiscount.setSelected(true);

        Button saveButton = new Button("save");
        saveButton.setOnAction(event -> {
            try {
                metroStationSetupPaneController.save((LoadSaveStrategy) group.getSelectedToggle().getUserData());
                metroStationSetupPaneController.saveDiscount(age64PlusDiscount.isSelected(), christmasLeaveDiscount.isSelected(), studentDiscount.isSelected(), frequentTravellerDiscount.isSelected());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        root.getChildren().addAll(button1, button2, age64PlusDiscount, christmasLeaveDiscount, studentDiscount, frequentTravellerDiscount, saveButton);
        this.add(root,1,1);
    }



}
