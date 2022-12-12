package view.panels;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.MetroFacade;
import view.panels.*;

public class AdminMainPane extends BorderPane {
	public AdminMainPane(MetroFacade metro){
	    TabPane tabPane = new TabPane(); 	    
        MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane(metro);
        ControlCenterPane controlCenterPane = new ControlCenterPane(metro);
        MetroStationSetupPane metroStationSetupPane = new MetroStationSetupPane(metro);
        Tab metroCardOverviewTab = new Tab("Metro cards overview",metroCardOverviewPane);
        Tab controlCenterTab = new Tab("Control Center", controlCenterPane);
        Tab setupTab = new Tab("Setup", metroStationSetupPane);
        tabPane.getTabs().add(controlCenterTab);
        tabPane.getTabs().add(metroCardOverviewTab);
        tabPane.getTabs().add(setupTab);
        this.setCenter(tabPane);
	}
}
