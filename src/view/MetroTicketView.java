package view;

import controller.MetroTicketViewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MetroFacade;

import java.util.ArrayList;

public class MetroTicketView {
	private Stage stage = new Stage();
	private MetroTicketViewController metroTicketViewController;
		
	public MetroTicketView(MetroFacade metro){
		this.metroTicketViewController = new MetroTicketViewController(metro, this);
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 350);			
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void updateMetrocardIDList(ArrayList<Integer> metroCardIds){
		metroTicketViewController.update();
	}
}
