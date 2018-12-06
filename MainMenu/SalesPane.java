import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
/**
 * this menu should cover tasks 4 total sales,5 apply sale on a category, 6 sold on sale.
 */
class SalesPane
{
    public static Pane getSalesPane()
    {
        Button  totalSalesBtn = new Button("Total Sales"),
                applySaleOnCategoryBtn = new Button("Apply Sale On Category"),
                soldOnSale = new Button("Products Sold In Sale"),
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

        applySaleOnCategoryBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event)
            {
                MainMenu.replaceMenuWith(ApplySaleOnCategory.getApplySaleOnCatPane());
            }
        });

        soldOnSale.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent evet)
            {
                MainMenu.replaceMenuWith(SoldOnSale.getSoldOnSalePane());
            }
        });

        return menuPane;
    }

}