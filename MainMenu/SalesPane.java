import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

class SalesPane
{
    public static Pane getSalesPane()
    {
        Button  totalSalesBtn = new Button("Total Sales"),
                applySaleOnCategoryBtn = new Button("Apply Sale On Category"),
                soldOnSale = new Button("Apply Sale"),
                backToMainMenuBtn = new Button("<-- Main Menu");
        
        VBox menuPane = new VBox(10);
        menuPane.setPadding(new Insets(10,25,10,25));
        menuPane.setAlignment(Pos.TOP_CENTER);

        menuPane.getChildren().addAll(new Pane(backToMainMenuBtn), totalSalesBtn, applySaleOnCategoryBtn,soldOnSale);
        //buttons styling.
        backToMainMenuBtn.setAlignment(Pos.TOP_LEFT);
        totalSalesBtn.setStyle("-fx-font-size: 3em;");
        applySaleOnCategoryBtn.setStyle("-fx-font-size: 3em;");
        soldOnSale.setStyle("-fx-font-size: 3em;");

        //adding listeners.
        backToMainMenuBtn.setOnAction(MainMenu.backToMainMenu());
        totalSalesBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override public void handle(ActionEvent event)
            {
                MainMenu.replaceMenuWith(TotalSales.getTotalSalesPane());
            }
        });

        return menuPane;
    }

}