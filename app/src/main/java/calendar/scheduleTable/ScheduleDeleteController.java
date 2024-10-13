package calendar.scheduleTable;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.CalendarMain;
import database.ScheduleConnection;
import database.ScheduleDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ScheduleDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane scheduleSelect;

    @FXML
    private ComboBox<String> month;

    @FXML
    private ComboBox<String> year;

    @FXML
    private ComboBox<String> name;

    @FXML
    private ComboBox<String> day;
    
    private ScheduleConnection scn = new ScheduleConnection();
	private ScheduleDAO dao = new ScheduleDAO(scn.getConnection());

    @FXML
    void delete(MouseEvent event) {
    	int year = Integer.parseInt(this.year.getValue());
    	int month = Integer.parseInt(this.month.getValue());
    	int day = Integer.parseInt(this.day.getValue());
    	String name = this.name.getValue();
    	dao.delete(LocalDate.of(year,month,day),name);
    	ScheduleEditController.stage.close();
    	AddDataAndLabel.stage.close();
    }

    @SuppressWarnings("static-access")
	@FXML
    void initialize() {
    	var ld = CalendarMain.cController.ld;
    	year.setValue(Integer.toString(ld.getYear()));
    	month.setValue(Integer.toString(ld.getMonthValue()));
    	day.setValue(Integer.toString(ld.getDayOfMonth()));
    	name.setValue(AddDataAndLabel.titleOfLabel.get());
    	System.out.println(year.getEditor());

    }
}
