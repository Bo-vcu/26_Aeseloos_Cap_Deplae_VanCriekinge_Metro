package view.panels;

import controller.ControlCenterPaneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroFacade;
import model.Metrocard;
import model.database.MetrocardDatabase;
import model.database.loadSaveStrategies.MetroCardsExcelLoadSaveStrategy;

import java.io.IOException;

public class ControlCenterPane extends GridPane {
    private ControlCenterPaneController controlCenterPaneController;

    public ControlCenterPane(MetroFacade metro) {
        this.controlCenterPaneController = new ControlCenterPaneController(metro, this);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        Button openMetroStationButton = new Button("Open Metrostation");
        this.add(openMetroStationButton, 50, 50, 1, 1);
        openMetroStationButton.setOnAction(event -> {
            try {
                controlCenterPaneController.openMetroStation();
                controlCenterPaneController.setMetroOpenOpTrue();
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button closeMetroStationButton = new Button("close Metrostation");
        this.add(closeMetroStationButton, 50, 60, 1, 1);
        closeMetroStationButton.setOnAction(event -> {
            try {
                controlCenterPaneController.closeMetroStation();
            } catch (BiffException e) {
                throw new RuntimeException(e);
            } catch (WriteException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            controlCenterPaneController.setMetroOpenOpFalse();
        });

    }

}
