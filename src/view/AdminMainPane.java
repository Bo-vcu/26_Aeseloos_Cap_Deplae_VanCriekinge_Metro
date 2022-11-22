package view;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.*;

public class AdminMainPane extends BorderPane {
	public AdminMainPane(){		
	    TabPane tabPane = new TabPane(); 	    
        MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane();
        Tab metroCardOverviewTab = new Tab("Metro cards overview",metroCardOverviewPane);
        Tab controlCenterTab = new Tab("Control Center", null);
        Tab setupTab = new Tab("Setup");
        tabPane.getTabs().add(controlCenterTab);
        tabPane.getTabs().add(metroCardOverviewTab);
        tabPane.getTabs().add(setupTab);
        this.setCenter(tabPane);
	}
}
