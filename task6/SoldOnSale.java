import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

class SoldOnSale
{
    public static Pane getSoldOnSalePane()
    {
        TableView<Row> table = new TableView<>();

        TableColumn<Row, Integer> 
        col1 = new TableColumn<>("product ID"),
        col2 = new TableColumn<>("Option ID");
        TableColumn<Row, String> col3 = new TableColumn<Row, String>("Product Name");

        col1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("product_id"));
        col2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("option_id"));
        col3.setCellValueFactory(new PropertyValueFactory<Row, String>("name"));

        table.getColumns().addAll(col1, col2, col3);

        //add handlers to the buttons.
        backToSaleMenu.setOnAction(MainMenu.backtoSalesMenu());
        submitBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Connection con = ConnectionFactory.getConnection();
                try 
                {
                    String queryText = 
                    "SELECT P.product_id, OP.option_id, product_name " +
                    "FROM Orders_Has_Products OP "+
                    "INNER JOIN Products_Has_Options PHO ON ( OP.product_id = PHO.product_id and OP.option_id = PHO.option_id) "+
                    "INNER JOIN Product P ON (OP.product_id = P.product_id) "+
                    "WHERE PHO.on_sale = 1";
                    PreparedStatement query = con.prepareStatement(queryText);
                    if(query.execute())
                    {
                        ArrayList<Row> rowObjs = new ArrayList<>();
                        ResultSet rows = query.getResultSet();

                        while(rows.next())
                        {
                            rowObjs.add( new Row(rows.getInt(1),rows.getInt(2), rows.getString(3)));
                        }
                        ObservableList<Row> data = FXCollections.observableList(rowObjs);
                        table.setItems(data);
                    }
                    //close connection
                    con.close();
                } 
                catch (Exception e) 
                {
                    //TODO: handle exception
                }
            }
        });

        //main pane
        VBox mainPane = new VBox(10);
        mainPane.setPadding(new Insets(10,25,10,25));
        mainPane.setAlignment(Pos.TOP_LEFT);
        mainPane.setPrefWidth(500);
        mainPane.getChildren().addAll(backToSaleMenu,new Label("This shows the products that were sold on sale."), table, submitBtn);

        return mainPane;
    }

    private static Button   submitBtn = new Button("submit"),
                            backToSaleMenu = new Button("<-- Back");
}