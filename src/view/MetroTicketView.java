package view;

import controller.MetroTicketViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroFacade;
import model.Metrocard;
import model.TicketPriceDecorator.TicketPrice;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
		Group request = new Group();

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


		Text text = new Text("Metro card price is €15 - 2 free rides included");
		text.setX(0);
		text.setY(50);


		Text selectMetroCardText = new Text("select metro card");
		selectMetroCardText.setX(60);
		selectMetroCardText.setY(20);

		Text numberOfRides = new Text("Number of rides");
		numberOfRides.setX(0);
		numberOfRides.setY(170);
		TextField numberOfRidesTextField = new TextField();
		numberOfRidesTextField.setLayoutX(150);
		numberOfRidesTextField.setLayoutY(150);
		CheckBox checkBox1 = new CheckBox("higher education student?");
		checkBox1.setLayoutX(0);
		checkBox1.setLayoutY(200);
		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton button1 = new RadioButton("younger than 24 years");
		button1.setLayoutX(0);
		button1.setLayoutY(250);
		RadioButton button2 = new RadioButton("older than 64 years");
		button2.setLayoutX(200);
		button2.setLayoutY(250);
		RadioButton button3 = new RadioButton("between 24 and 64 years");
		button3.setLayoutX(400);
		button3.setLayoutY(250);
		button1.setToggleGroup(toggleGroup);
		button2.setToggleGroup(toggleGroup);
		button3.setToggleGroup(toggleGroup);


		Button addExtraRides = new Button("add extra rides to metro card");
		addExtraRides.setLayoutX(30);
		addExtraRides.setLayoutY(320);
		Text explanationText = new Text();
		Text totalPriceText = new Text("Total price:");
		Text totalPriceValue = new Text();
		addExtraRides.setOnAction(event -> {
			int ritten = Integer.parseInt(numberOfRidesTextField.getText());
			//boolean is24Min = false;
			boolean is64Plus = false;
			boolean isStudent = false;
			boolean is24Min = false;
			String exptxt = "Basic price of ride is €2,10 ";
			if (toggleGroup.getSelectedToggle() == button2){
				is64Plus = true;
			}
			if (checkBox1.isSelected()){
				isStudent = true;
			}
			if (toggleGroup.getSelectedToggle() == button2){
				is24Min = true;
			}
			//TO DO ervoor zorgen dat toegepaste kortingen in tekstje komen te staan
//			for (String s: metroTicketViewController.getMetroTicketsDiscountList()){
//				exptxt += s;
//			}

			explanationText.setText(exptxt);
			int id = (int) choiceBox.getValue();
			double price = metroTicketViewController.calculatePrice(is24Min, is64Plus, isStudent, id)*ritten;
			totalPriceValue.setText("€" + String.valueOf(round(price, 2)));
		});

		totalPriceText.setLayoutX(30);
		totalPriceText.setLayoutY(380);

		totalPriceValue.setLayoutX(120);
		totalPriceValue.setLayoutY(380);

		explanationText.setLayoutX(30);
		explanationText.setLayoutY(420);
		Button confirmRequest = new Button("Confirm request");
		confirmRequest.setLayoutX(30);
		confirmRequest.setLayoutY(450);
		Button cancelRequest = new Button("Cancel request");
		cancelRequest.setLayoutX(180);
		cancelRequest.setLayoutY(450);

		selectCard.getChildren().addAll(selectMetroCardText, choiceBox);
		newCard.getChildren().addAll(text, button, numberOfRides, numberOfRidesTextField, checkBox1, button1, button2, button3);
		request.getChildren().addAll(addExtraRides, totalPriceText, totalPriceValue, explanationText, confirmRequest, cancelRequest);

		root.getChildren().addAll(newCard, selectCard, request);


		Scene scene = new Scene(root, 650, 550);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void updateMetrocardIDList(ArrayList<Integer> metroCardIds){
		this.metroIDs = FXCollections.observableArrayList(metroCardIds);
		choiceBox.setItems(metroIDs);
		choiceBox.setValue(metroIDs.get(0));
	}

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
