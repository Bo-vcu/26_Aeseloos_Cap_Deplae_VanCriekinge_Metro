package view;

import controller.MetroTicketViewController;
import javafx.collections.FXCollections;
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
import model.MetroFacade;
import javafx.scene.control.Button;
import model.Metrocard;

import java.awt.*;
import java.util.ArrayList;

public class MetroTicketView extends GridPane {
	private Stage stage = new Stage();
	private  MetroFacade metro;
	private MetroTicketViewController metroTicketViewController;
		
	public MetroTicketView(MetroFacade metro){
		this.metro = metro;
		this.metroTicketViewController = new MetroTicketViewController(metro, this);
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		this.setVgap(5);
		this.setHgap(5);

		Button button = new Button("new metro card");
		this.setPadding(new Insets(50, 50, 50, 50));
		Text text = new Text();

		text.setText("Metro card price is €15 - 2 free rides included");
		text.setX(50);
		text.setY(50);


		Text selectMetroCardText = new Text();

		selectMetroCardText.setText("select metro card");
		selectMetroCardText.setX(50);
		selectMetroCardText.setY(50);

		ChoiceBox choiceBox = new ChoiceBox();

		choiceBox.setItems(metroTicketViewController.update());

		Group root = new Group(text);
		this.add(button, 0, 0, 1, 1);

		root.getChildren().addAll(button,choiceBox);
		Scene scene = new Scene(root, 650, 350);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void updateMetrocardIDList(ArrayList<Integer> metroCardIds){
		metroTicketViewController.update();
	}
}
