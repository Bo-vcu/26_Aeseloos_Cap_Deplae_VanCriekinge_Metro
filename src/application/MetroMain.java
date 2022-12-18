package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.MetroFacade;
import model.TicketPriceDecorator.TicketPriceFactory;
import view.*;



public class MetroMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		MetroFacade metro = new MetroFacade();
		AdminView adminView = new AdminView(metro);
		MetroTicketView metroTicketView = new MetroTicketView(metro);
		MetroStationView metroStationView = new MetroStationView(metro);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
