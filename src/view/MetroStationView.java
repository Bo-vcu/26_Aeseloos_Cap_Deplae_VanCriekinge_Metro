package view;

import controller.MetroStationViewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MetroFacade;

public class MetroStationView {
	private MetroFacade metro;
	private Stage stage = new Stage();		
	private MetroStationViewController metroStationViewController;

	public MetroStationView(MetroFacade metro){
		this.metro = metro;
		this.metroStationViewController = new MetroStationViewController(metro, this);
		stage.setTitle("METRO STATION VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(390);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 300);			
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
