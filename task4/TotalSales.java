

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

class TotalSales extends VBox
{
    public TotalSales(int spacing)
    {
        super(spacing);
    }
    public static Pane getTotalSalesPane()
    {
        Label descriptionLabel = new Label("Get the total sales between two dates:\nDates have to be in the format: YYYY-MM-DD");
        HBox fromDateCombo = new HBox();
        fromDateCombo.setAlignment(Pos.CENTER_LEFT);
        fromDateCombo.getChildren().addAll(new Label("From: "), fDateField);

        HBox toDateCombo = new HBox();
        toDateCombo.setAlignment(Pos.CENTER_LEFT);
        toDateCombo.getChildren().addAll(new Label("To: "), tDateField);

        HBox salesCombo = new HBox();
        salesCombo.setAlignment(Pos.CENTER_LEFT);
        salesCombo.getChildren().addAll(new Label("Sales: "), salesField);

        //adding components to the totalsales pane.
        TotalSales getSalesView = new TotalSales(5);
        getSalesView.setPadding(new Insets(10,25,10,25));
        getSalesView.getChildren().addAll(backToSalesFunsMenuBtn, descriptionLabel, fromDateCombo, toDateCombo, salesCombo, submitBtn);

        //add event handler to the button.
        submitBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event)
            {
                //i wonder if i can set to null instead of using equals.
                // TODO : remove the true condition to test the date fields
                if(fDateField.getText().equals("") || tDateField.getText().equals("") || true)
                {
                    // TODO: display an error message that fields are empty.
                    Connection con =  ConnectionFactory.getConnection();
                    try 
                    {
                        PreparedStatement query =  con.prepareStatement(
                        "SELECT Sum(total_cost) as TotalCost FROM Orders WHERE order_date BETWEEN '"+fDateField.getText()+"' AND '"+tDateField.getText()+"';");
                        if(query.execute())
                        {
                            ResultSet results = query.getResultSet();
                            results.next();
                            salesField.setText("" + results.getDouble(1));
                            salesField.setEditable(false);
                        };

                        con.close();
                        
                        
                    } catch (Exception e) 
                    {
                        //TODO: handle exception
                        System.out.print("problem with connection or execution of query.");
                        e.printStackTrace();
                    }
                }
            }
        });

        backToSalesFunsMenuBtn.setOnAction(MainMenu.backtoSalesMenu());

        
        return getSalesView;
    }

    private static Button submitBtn = new Button("Submit");
    private static Button backToSalesFunsMenuBtn = new Button("<-- Back");
    private static TextField fDateField = new TextField();
    private static TextField tDateField = new TextField();
    private static TextField salesField = new TextField();

}