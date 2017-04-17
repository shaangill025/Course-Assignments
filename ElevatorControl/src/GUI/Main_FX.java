package GUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main_FX extends Application {
		Stage stage;
		AnchorPane mainpane;
		
		public static void main(String[] args) {
		    launch(args);
		}
		
	    @Override
	    public void start(Stage primaryStage) {
	        	        
	        try{
	        	mainpane = (AnchorPane) FXMLLoader.load(Main_FX.class.getResource("mainGUI.fxml"));
	        }catch (IOException ioe){
	            ioe.printStackTrace();
	        }

	        primaryStage.setTitle("Elevator Control Panel");
	        primaryStage.setWidth(600);
	        primaryStage.setHeight(600);
	        stage = primaryStage;
	        primaryStage.show();
	    }

		public Stage returnStage() {
			// TODO Auto-generated method stub
			return stage;
		}
	    
}

