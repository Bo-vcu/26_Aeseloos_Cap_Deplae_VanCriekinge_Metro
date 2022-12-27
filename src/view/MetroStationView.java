package view;

import controller.MetroStationViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jxl.read.biff.BiffException;
import model.MetroFacade;
import model.MetroGate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MetroStationView {
	private Stage stage = new Stage();
	private Scene scene;
	private MetroStationViewController metroStationViewController;
	private ArrayList<ChoiceBox> choiceBoxes = new ArrayList<>();
	private ObservableList<Integer> metroIDs;

	public MetroStationView(MetroFacade metro){

		this.metroStationViewController = new MetroStationViewController(metro, this);
		stage.setTitle("METRO STATION VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(600);
		Group root = new Group();
		root.setLayoutY(50);

		int layoutX = 30;
		int gateNr = 1;
		int gates = 3;

		for (int i=0;i<gates;i++){
			choiceBoxes.add(new ChoiceBox());
		}


		for (MetroGate mg:metroStationViewController.getAllGates()){

			ChoiceBox c = mg.getChoiceBox();
			//MetroGate metroGate = new MetroGate();
			//metroGate.setMetroGateState(metroGate.getClosed());
			VBox gate = new VBox();
			gate.setId(String.valueOf(gateNr));
			Text title = new Text("Gate "+gateNr);


			Text ids = new Text("MetroCardID:");
			ids.setY(20);

			c.setLayoutY(30);

			gate.setLayoutX(layoutX);
			layoutX+=200;

			Text notification = new Text("");

			notification.setY(180);

			Button scanMetrocard = new Button("Scan metrocard");

			int finalGateNr = gateNr;
			scanMetrocard.setOnAction(event -> {
				//String result = metroGate.getMetroGateState().scanMetroCard();
				String result = metroStationViewController.scanMetrocard((Integer) c.getValue(), finalGateNr);
				notification.setText(result);
			});

			scanMetrocard.setLayoutY(70);


			Button walkThroughGate = new Button("Walk through gate");
			int finalGateNr1 = gateNr;
			walkThroughGate.setOnAction(event -> {
				//String result = mg.getMetroGateState().walkTroughGate();
				String result = metroStationViewController.walkThroughGate(finalGateNr1);
				//metroStationViewController.walkThroughGate();
				notification.setText(result);
			});

			walkThroughGate.setLayoutY(120);


			gate.getChildren().addAll(title, ids, c, scanMetrocard, walkThroughGate, notification);


			root.getChildren().add(gate);
//			ColorInput ci = new ColorInput(gate.getLayoutX(),
//					gate.getLayoutY(),
//					gate.getLayoutBounds().getWidth(),
//					gate.getLayoutBounds().getHeight(),
//					Color.ORANGE);
//			gate.setEffect(ci);
			gateNr++;

		}
		Scene scene = new Scene(root, 650, 300);
		this.scene = scene;
//		scene.setFill(Color.web("#81c483"));
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();
	}

	public void updateMetrocardIDList(ArrayList<Integer> metroCardIds){
		this.metroIDs = FXCollections.observableArrayList(metroCardIds);
		for (MetroGate mg:metroStationViewController.getAllGates()){
			mg.getChoiceBox().setItems(metroIDs);
			mg.getChoiceBox().setValue(metroIDs.get(0));
			if (mg.getMetroGateState() == mg.getInactive()){
				setColorInactive(String.valueOf(mg.getId()));
			}
			if (mg.getMetroGateState() != mg.getInactive()){
				setColorActive(String.valueOf(mg.getId()));
			}
		}
	}

	private void setColorActive(String id) {
		VBox box = (VBox) scene.lookup("#" + id);
		box.setStyle("-fx-background-color: white;");
	}

	public void setColorInactive(String id){
		VBox box = (VBox) scene.lookup("#" + id);
		box.setStyle("-fx-background-color: orange;");

	}
}
