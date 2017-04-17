package GUI;

import java.io.File;

import Main.WordCount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
	
	@FXML
	private Button upload;
	
	@FXML
	private Button sort;
	
	@FXML
	private Button count;
	
    @FXML
    private TextField typeword;
    
    @FXML
    private TextField filename;
    
    @FXML
    private TextField result;
    
    @FXML
    private TextArea frequency;
    
    @FXML
    private TextArea text;
    
    Main_FX start = new Main_FX();
    Stage stage = start.returnStage();
    File textFile;
    WordCount pgm;
    
    @FXML
    void fileUpload(ActionEvent event) {
    	 FileChooser fileChooser = new FileChooser();
         FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
         fileChooser.getExtensionFilters().add(extFilter);
         textFile = fileChooser.showOpenDialog(stage);
         filename.setText(textFile.getName());
         pgm = new WordCount(textFile);
         String texts = pgm.read();
         text.setText(texts);
    }

    @FXML
    void sortWords(ActionEvent event) {
    	if(textFile!=null){
    		String freq = pgm.sort();
    		frequency.setText(freq);
    	}
    	else{
    		throw new RuntimeException("No file Selected!");
    	}
    }
    
    @FXML
    void countFreq(ActionEvent event) {
    	if(textFile!=null&&typeword!=null||
    			!typeword.equals("Type Word for Count")){
    		int freq = pgm.count(typeword.getText());
    		result.setText(Integer.toString(freq));
    	}
    	else{
    		throw new RuntimeException("Error, fields not filled!");
    	}
    }
}