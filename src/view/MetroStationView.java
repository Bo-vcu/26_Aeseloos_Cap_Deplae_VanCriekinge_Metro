package view;

import controller.MetroStationViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jxl.read.biff.BiffException;
import model.MetroFacade;

import java.io.IOException;
import java.util.ArrayList;

public class MetroStationView {
	private Stage stage = new Stage();
	private MetroStationViewController metroStationViewController;
	private ChoiceBox choiceBox1 = new ChoiceBox();
	private ChoiceBox choiceBox2 = new ChoiceBox();
	private ChoiceBox choiceBox3 = new ChoiceBox();
	private ObservableList<Integer> metroIDs;

	public MetroStationView(MetroFacade metro){
		this.metroStationViewController = new MetroStationViewController(metro, this);
		stage.setTitle("METRO STATION VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(390);
		Group root = new Group();
		root.setLayoutY(50);

		Group gate1 = new Group();
		Group gate2 = new Group();
		Group gate3 = new Group();

		Text text1 = new Text("Gate 1");
		Text text2 = new Text("Gate 2");
		Text text3 = new Text("Gate 3");
		Text text4 = new Text("MetroCardID:");
		text4.setY(20);
		Text text5 = new Text("MetroCardID:");
		text5.setY(20);
		Text text6 = new Text("MetroCardID:");
		text6.setY(20);

		choiceBox1.setLayoutY(30);
		choiceBox2.setLayoutY(30);
		choiceBox3.setLayoutY(30);

		gate1.setLayoutX(30);
		gate2.setLayoutX(230);
		gate3.setLayoutX(430);

		Text text7 = new Text("");
		Text text8 = new Text("");
		Text text9 = new Text("");
		text7.setY(180);
		text8.setY(180);
		text9.setY(180);
		Button scanMetrocard1 = new Button("Scan metrocard");
		scanMetrocard1.setOnAction(event -> {
			String result = metroStationViewController.scanMetrocard((Integer) choiceBox1.getValue());
			text7.setText(result);
		});
		Button scanMetrocard2 = new Button("Scan metrocard");
		scanMetrocard2.setOnAction(event -> {
			String result = metroStationViewController.scanMetrocard((Integer) choiceBox2.getValue());
			text8.setText(result);
		});
		Button scanMetrocard3 = new Button("Scan metrocard");
		scanMetrocard3.setOnAction(event -> {
			String result = metroStationViewController.scanMetrocard((Integer) choiceBox3.getValue());
			text9.setText(result);
		});
		scanMetrocard1.setLayoutY(70);
		scanMetrocard2.setLayoutY(70);
		scanMetrocard3.setLayoutY(70);

		Button walkThroughGate1 = new Button("Walk through gate");
		Button walkThroughGate2 = new Button("Walk through gate");
		Button walkThroughGate3 = new Button("Walk through gate");
		walkThroughGate1.setLayoutY(120);
		walkThroughGate2.setLayoutY(120);
		walkThroughGate3.setLayoutY(120);

		gate1.getChildren().addAll(text1, text4, choiceBox1, scanMetrocard1, walkThroughGate1, text7);
		gate2.getChildren().addAll(text2, text5, choiceBox2, scanMetrocard2, walkThroughGate2, text8);
		gate3.getChildren().addAll(text3, text6, choiceBox3, scanMetrocard3, walkThroughGate3, text9);

		root.getChildren().addAll(gate1, gate2, gate3);

		Scene scene = new Scene(root, 650, 300);			
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();
	}

	public void updateMetrocardIDList(ArrayList<Integer> metroCardIds){
		this.metroIDs = FXCollections.observableArrayList(metroCardIds);
		choiceBox1.setItems(metroIDs);
		choiceBox2.setItems(metroIDs);
		choiceBox3.setItems(metroIDs);
		choiceBox1.setValue(metroIDs.get(0));
		choiceBox2.setValue(metroIDs.get(0));
		choiceBox3.setValue(metroIDs.get(0));
	}
}
