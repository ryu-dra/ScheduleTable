package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class PackageEditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField sinki;

    @FXML
    private ListView<String> list;
    
 
    @FXML
    private TextField ato;

    @FXML
    private Label moto;
    
    


    
    private ObservableList<String> oList = FXCollections.observableArrayList();    
    private ScheduleConnection scn = new ScheduleConnection();
   	private PackagesDAO pdao = new PackagesDAO(scn.getConnection());


    @FXML
    void tuika(MouseEvent event) {
    	pdao.insert(sinki.getText());
    	oList.add(sinki.getText());
    }
    
    @FXML
    void execute(MouseEvent event) {
    	if(!ato.getText().equals(null)) {
    		pdao.update(moto.getText(), ato.getText());
    		oList.removeAll(moto.getText());
    		oList.addAll(ato.getText());
    	}
    }
    


    @FXML
    void initialize() {
    	list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> arg0) {
				return new ButtonCell();
			}
		});
    	var datas = pdao.find();  	
    	for(var data :datas) {
    		oList.add(data);
    	}
    	list.itemsProperty().setValue(oList);
    	
    	
    }
    
    public class ButtonCell extends ListCell<String> {
    	
    	private Button b;
    	
    	public ButtonCell() {
    		initCompornent();
    	}
    	
    	private void initCompornent() {
    		b= new Button();
    	}
    	
    	@Override
		public void  updateItem(String item,boolean empty){
			super.updateItem(item, empty);
			if(empty) {
				setText(null);
				setGraphic(null);
			}else {
				b.setText(item);
				b.setOnAction(n -> {
					var value = getListView().getItems().get(getIndex());
					moto.setText(value);
				});
				setGraphic(b);
			}
		}
    	
    }
    
}
