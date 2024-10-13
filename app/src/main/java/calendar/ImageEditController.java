package calendar;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class ImageEditController {
	
	public ImageEditController() throws URISyntaxException {
		sURI = this.getClass().getResource("../res/scheduleImage.txt").toURI();
		cURI = this.getClass().getResource("../res/calendarImage.txt").toURI();
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane calendar;

    @FXML
    private AnchorPane schedule;
    
    URI sURI;
    URI cURI;

    @FXML
    void sChoice(MouseEvent event) {
    	 var fc = new FileChooser();
    	 fc.setTitle("写真を選択");
    	 var file = fc.showOpenDialog(CalendarController.imageStage);
    	 String str = file.toString();
    	 str = str.replace("\\", "/");
    	 try(var writer = Files.newBufferedWriter(Paths.get(sURI))){
    
    		 writer.write(str);
    		 writer.newLine();
    	 } catch (IOException e) {
    		// TODO 自動生成された catch ブロック
    		e.printStackTrace();
    	}
    }

    @FXML
    void cChoise(MouseEvent event) {
   	 var fc = new FileChooser();
	 fc.setTitle("写真を選択");
	 var file = fc.showOpenDialog(CalendarController.imageStage);
	 String str = file.toString();
	 str = str.replace("\\", "/");
	 try(var writer = Files.newBufferedWriter(Paths.get(cURI))){
		 writer.write(str);
		 writer.newLine();
	 } catch (IOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
    }

    @FXML
    void initialize() {
    	
    }
}