package application;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TableSample extends Application {

  public static class Row {
    SimpleStringProperty name;
    SimpleStringProperty address;
    Row(String name, String address) {
      this.name = new SimpleStringProperty(name);
      this.address = new SimpleStringProperty(address);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void start(Stage stage) throws Exception {

    TableView<Row> tv = new TableView<>();

    TableColumn<Row, String>nameCol = new TableColumn<>("名前");
    nameCol.setCellValueFactory(t->t.getValue().name);    
    TableColumn<Row, String>addressCol = new TableColumn<>("住所");
    addressCol.setCellValueFactory(t->t.getValue().address);    
    tv.getColumns().addAll(nameCol, addressCol);

    Row row0, row1;
    tv.getItems().addAll(
      row0 = new Row("小林", "東京都"),
      row1 = new Row("鈴木", "神奈川")
    );

    Button button = new Button("訂正");
    button.setOnAction(e -> {
      row0.name.set("中村");
    });


    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(tv);
    borderPane.setBottom(button);

    stage.setScene(new Scene(borderPane));
    stage.show();
  }

  public static void main(String[]args) {
    Application.launch( args);
  }
}
