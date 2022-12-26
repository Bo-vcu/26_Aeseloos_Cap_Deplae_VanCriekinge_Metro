package view.panels;

import controller.ControlCenterPaneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroFacade;
import model.MetroGate;
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

        Group group = new Group();
        Button openMetroStationButton = new Button("Open Metrostation");
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
        closeMetroStationButton.setLayoutY(40);
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
        group.getChildren().addAll(openMetroStationButton, closeMetroStationButton);
        this.add(group,0,1,1,1);

        int i = 0;
        for (MetroGate metroGate : controlCenterPaneController.getAllGates()) {
            Group root = new Group();
            Text gate = new Text("Gate " + (i + 1) + " / Active");
            gate.setLayoutX(20 + 10*i);
            gate.setLayoutY(60);
            Button activate = new Button("Activate");
            int finalI = i;
            activate.setOnAction(event -> {
                metroGate.setMetroGateState(metroGate.getClosed());
                gate.setText("Gate " + (finalI + 1) + " / Active");
            });
            activate.setLayoutX(20 + 10*i);
            activate.setLayoutY(70);
            Button deactivate = new Button("Deactivate");
            int finalI1 = i;
            deactivate.setOnAction(event -> {
                metroGate.setMetroGateState(metroGate.getInactive());
                gate.setText("Gate " + (finalI1 + 1) + " / Inactive");
            });
            deactivate.setLayoutX(20 + 10*i);
            deactivate.setLayoutY(110);
            Text aantalScannedCards = new Text("# scanned cards");
            aantalScannedCards.setLayoutX(20 + 10*i);
            aantalScannedCards.setLayoutY(160);
            Text aantalScannedCardsValue = new Text(String.valueOf(metroGate.getAantalScannedCards()));
            aantalScannedCardsValue.setLayoutX(20 + 10*i);
            aantalScannedCardsValue.setLayoutY(190);
            root.getChildren().addAll(gate, activate, deactivate, aantalScannedCards, aantalScannedCardsValue);
            this.add(root, i, 5);
            i++;
        }
    }
}