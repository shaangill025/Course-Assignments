package GUI;

import control.Elevator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
	
	@FXML
	private Button first2Level;
	
	@FXML
	private Button first3Level;
	
	@FXML
	private Button second3Level;
	
	@FXML
	private Button second1Level;
	
	@FXML
	private Button third1Level;
	
	@FXML
	private Button third2Level;

	Elevator elevator;
	Button[] first = new Button[]{first2Level,first3Level};
	Button[] second = new Button[]{second3Level,second1Level};
	Button[] third = new Button[]{third1Level,third2Level};

	
	public Controller(Elevator input){
		elevator = input;
		update();
	}
	
	void update(){
		if(elevator.currentFloor()==1){
			for(Button temp : first){
				temp.setDisable(false);
			}
			for(Button temp : second){
				temp.isDisabled();
			}
			for(Button temp : third){
				temp.isDisabled();
			}
		}
		else if(elevator.currentFloor()==2){
			for(Button temp : second){
				temp.setDisable(false);
			}
			for(Button temp : first){
				temp.isDisabled();
			}
			for(Button temp : third){
				temp.isDisabled();
			}
		}
		else{
			for(Button temp : third){
				temp.setDisable(false);
			}
			for(Button temp : second){
				temp.isDisabled();
			}
			for(Button temp : first){
				temp.isDisabled();
			}
		}
	}
    @FXML
    void sortWords(ActionEvent event) {
    	
    }
    
}