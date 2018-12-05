
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

class ApplySaleOnCategory
{
    public static Pane getApplySaleOnCatPane()
    {
        //choose category pair of fields
        HBox catPairPane = new HBox(10);
        catPairPane.setAlignment(Pos.CENTER_LEFT);
        catPairPane.getChildren().addAll(new Label("Choose category to apply discount on: "), categories);

        //disount pair of fields
        HBox discountPairPane = new HBox(10);
        discountPairPane.setAlignment(Pos.CENTER_LEFT);
        discountPairPane.getChildren().addAll(new Label("Enter the amount of discount percentage wise: "), discountField);
        //Note for assistance with the number inputed.
        Label discountAmount = new Label("For example: If you want to apply 50% discount, input 0.50 .");
        //main pane
        VBox mainPane = new VBox(10);
        mainPane.setAlignment(Pos.TOP_LEFT);
        mainPane.setPadding(new Insets(10,25,10,25));
        mainPane.getChildren().addAll(backToSalemenuBtn ,catPairPane, discountPairPane, discountAmount, submit);
        
        // add action handlers
        backToSalemenuBtn.setOnAction(MainMenu.backtoSalesMenu());
        submit.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event)
            {
                Connection con = ConnectionFactory.getConnection();
                try 
                {
                    String querytext = 
                    "UPDATE products_belong_category as cat "+
                    "LEFT JOIN products_has_options PHO ON(cat.product_id = PHO.product_id) "+
                    "SET price = price * "+ discountField.getText() +
                    " WHERE category_id = "+ categories.getValue().id;
                    PreparedStatement query = con.prepareStatement(querytext);
                    query.execute();
                    //close the connection
                    con.close();
                } 
                catch (SQLException e) 
                {
                    System.err.println("problem with executing the query");
                    e.printStackTrace();
                }

            }
        });


        return mainPane;

    }
    private static ObservableList<Category> populateWithCategories()
    {
        ObservableList<Category> data = FXCollections.observableArrayList();
        Connection con = ConnectionFactory.getConnection();
        try 
        {
            PreparedStatement query = con.prepareStatement("Select * From category;");
            if(query.execute())
            {
                ResultSet results = query.getResultSet();
                while(results.next())
                {
                    data.add(new Category(results.getInt(1), results.getString(2)));
                }
                //close the connecetion
                con.close();
            }
            
        } catch (SQLException e) 
        {
            System.out.println("There is a problem in populating the combo box: ");
            e.printStackTrace();
        }

        return data;
    }

    private static TextField discountField = new TextField();
    private static ComboBox<Category> categories = new ComboBox<>(populateWithCategories());
    private static Button   submit = new Button("submit discount"),
                            backToSalemenuBtn = new Button("<-- Back");
}

class Category
{
    public int id;
    public String name;
    public Category(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String toString()
    {
        return this.name;
    }
}