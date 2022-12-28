package view.panels;

import controller.ControlCenterPaneController;
import controller.MetroStationViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
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
import view.MetroStationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ControlCenterPane extends GridPane {
    private ControlCenterPaneController controlCenterPaneController;
    private Map<Integer, Integer> scannedGatesValues = new TreeMap<>();
    private VBox alertBox;

    public ControlCenterPane(MetroFacade metro) {
        this.controlCenterPaneController = new ControlCenterPaneController(metro, this);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        Group group = new Group();
        Group alertsGroup = new Group();
        Button openMetroStationButton = new Button("Open Metrostation");
        openMetroStationButton.setOnAction(event -> {
            try {
                controlCenterPaneController.openMetroStation();
                //controlCenterPaneController.setMetroOpenOpTrue();
                controlCenterPaneController.toggleMetroOpen();
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button closeMetroStationButton = new Button("Close Metrostation");
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
            //controlCenterPaneController.setMetroOpenOpFalse();
            controlCenterPaneController.toggleMetroOpen();
        });
        group.getChildren().addAll(openMetroStationButton, closeMetroStationButton);
        this.add(group,0,1,1,1);
        this.add(alertsGroup, 0,9,2,5);

        int i = 0;
        for (MetroGate metroGate : controlCenterPaneController.getAllGates()) {
            VBox gateBox = new VBox();
            gateBox.setId(String.valueOf(i+1));
            Text gate = new Text("Gate " + (i + 1) + " / Active");
            gate.setLayoutX(20 + 10*i);
            gate.setLayoutY(60);
            Button activate = new Button("Activate");
            int finalI = i;
            activate.setOnAction(event -> {
                metroGate.setMetroGateState(metroGate.getClosed());
                controlCenterPaneController.updateGates();
                setColorActive(String.valueOf(finalI+1));
                gate.setText("Gate " + (finalI + 1) + " / Active");
            });
            activate.setLayoutX(20 + 10*i);
            activate.setLayoutY(70);
            Button deactivate = new Button("Deactivate");
            int finalI1 = i;
            deactivate.setOnAction(event -> {
                metroGate.setMetroGateState(metroGate.getInactive());
                controlCenterPaneController.updateGates();
                setColorInactive(String.valueOf(finalI1+1));
                gate.setText("Gate " + (finalI1 + 1) + " / Inactive");
            });
            deactivate.setLayoutX(20 + 10*i);
            deactivate.setLayoutY(110);
            Text aantalScannedCards = new Text("# scanned cards");
            aantalScannedCards.setLayoutX(20 + 10*i);
            aantalScannedCards.setLayoutY(160);
            Text aantalScannedCardsValue = new Text(String.valueOf(metroGate.getAantalScannedCards()));
            aantalScannedCardsValue.setId(String.valueOf(metroGate.getId()));
            aantalScannedCardsValue.setLayoutX(20 + 10*i);
            aantalScannedCardsValue.setLayoutY(190);
            gateBox.getChildren().addAll(gate, activate, deactivate, aantalScannedCards, aantalScannedCardsValue);
//            group.getChildren().addAll(gateBox);
            this.add(gateBox, i, 5);
            i++;
        }
        alertBox = new VBox();
        Text alerts = new Text("Alerts:");
        alertBox.getChildren().add(alerts);
        alertsGroup.getChildren().add(alertBox);

    }
    private void setColorActive(String id) {
        VBox box = (VBox) this.lookup("#" + id);
        box.setStyle("-fx-background-color: white;");
    }

    public void setColorInactive(String id){
        VBox box = (VBox) this.lookup("#" + id);
        box.setStyle("-fx-background-color: orange;");

    }

    public void setAantalScannedCardsValues(){
        ArrayList<MetroGate> gates =controlCenterPaneController.getAllGates();
        for (int i=0;i<controlCenterPaneController.getAllGates().size();i++){
            scannedGatesValues.put(i,gates.get(i).getAantalScannedCards());
        }
    }

    public void updateScannedCards(){
        setAantalScannedCardsValues();
        for(MetroGate metroGate:controlCenterPaneController.getAllGates()){
            VBox box = (VBox) this.lookup("#" + metroGate.getId());
            for (Node node: box.getChildren()){
                if (node instanceof Text && ((Text) node).getText().matches("[0-9]+")){
                    ((Text) node).setText((String.valueOf(scannedGatesValues.get(metroGate.getId()-1))));
                }
            }
        }
    }

    public void updateAlerts(ArrayList<String> alerts){
        alertBox.getChildren().clear();
        alertBox.getChildren().add(new Text("Alerts:"));
        for (String alert : alerts){
            alertBox.getChildren().add(new Text(alert));
        }
    }
}