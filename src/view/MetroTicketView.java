package view;

import controller.MetroTicketViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroFacade;
import javafx.scene.control.Button;
import model.Metrocard;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MetroTicketView extends GridPane {
	private Stage stage = new Stage();
	private  MetroFacade metro;
	private MetroTicketViewController metroTicketViewController;

	private ChoiceBox choiceBox = new ChoiceBox();
	private ObservableList<Integer> metroIDs;
		
	public MetroTicketView(MetroFacade metro){
		this.metro = metro;
		this.metroTicketViewController = new MetroTicketViewController(metro, this);
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		this.setVgap(5);
		this.setHgap(5);


		Group root = new Group();
		Group newCard = new Group();
		Group selectCard = new Group();

		newCard.setLayoutX(30);
		newCard.setLayoutY(20);

		//sCard meer naar onder zetten
		selectCard.setLayoutX(30);
		selectCard.setLayoutY(100);


		Button button = new Button("new metro card");

		button.setOnAction(event -> {
			try {
				metroTicketViewController.addMetroCard();
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		});


		Text text = new Text();

		text.setText("Metro card price is €15 - 2 free rides included");
		text.setX(0);
		text.setY(50);


		Text selectMetroCardText = new Text();

		selectMetroCardText.setText("select metro card");
		selectMetroCardText.setX(0);
		selectMetroCardText.setY(50);

		selectCard.getChildren().addAll(selectMetroCardText, choiceBox);
		newCard.getChildren().addAll(text, button);

		root.getChildren().addAll(newCard, selectCard);


		Scene scene = new Scene(root, 650, 350);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void updateMetrocardIDList(ArrayList<Integer> metroCardIds){
		this.metroIDs = FXCollections.observableArrayList(metroCardIds);
		choiceBox.setItems(metroIDs);
		choiceBox.setValue(metroIDs.get(0));
	}
}
