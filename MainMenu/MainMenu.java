import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainMenu extends Application
{
    @Override public void start(Stage primaryStage)
    {
        mainMenuPane = MainMenu.prepMainMenu();
        root = new Pane(mainMenuPane);
        root.setPrefSize(500, 1000);
        primaryStage.setScene(new Scene(root, 500, 1000));
        primaryStage.show();
    }
    /**
     * prepare the main menu to be used.
     * @return
     */
    private static Pane prepMainMenu()
    {
        Button  
        salesFuncBtn = new Button("Sales funcs"),
        productFuncBtn = new Button("Products funcs"),
        usersFuncBtn = new Button("Users funcs");
        
        //creating the container for the mainmenu
        VBox MMPane = new VBox(10);
        MMPane.setPadding(new Insets(10,25,10,25));
        MMPane.setAlignment(Pos.TOP_CENTER);
        //add the buttons to the mainmenu.
        MMPane.getChildren().add(salesFuncBtn);
        MMPane.getChildren().add(productFuncBtn);
        MMPane.getChildren().add(usersFuncBtn);
        
        //sizing buttons by changing font
        for(Node btn : MMPane.getChildren())
            ((Button) btn).setStyle("-fx-font-size: 3em;");
        
        
        //add action listeners.
        salesFuncBtn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event) 
            {
                replaceMenuWith(salesfuncsPane);
            }
        });
        //transition to user functionality menu.
        usersFuncBtn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override public void handle(ActionEvent event)
            {
                replaceMenuWith(userFuncsPane);
            }
        });
        //transition to the product funtinality.
        productFuncBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event)
            {
                replaceMenuWith(productFuncsPane);
            }
        });

        return MMPane;
    }

    /**
     * creates the eventhandler for the go back button.
     * it is implemented here because it is a common functionality accross all the 
     * return buttons. plus, the <code>replaceMenuWith()</code> is private.
     * @return
     */
    public static EventHandler<ActionEvent> backToMainMenu()
    {
        return new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event)
            {
                replaceMenuWith(mainMenuPane);
            }
        };
    }

    /**
     * This method will provide the handler to the return buttons to go
     * back to the sales menu.
     * @return
     */
    public static EventHandler<ActionEvent> backtoSalesMenu()
    {
        return new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event)
            {
                replaceMenuWith(salesfuncsPane);
            }
        };
    }

    static void replaceMenuWith(Pane withThis)
    {
        root.getChildren().removeAll(root.getChildren());
        root.getChildren().add(withThis);
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    private static Pane mainMenuPane = null;
    private static Pane salesfuncsPane = SalesPane.getSalesPane();
    private static Pane userFuncsPane = UserPane.getUserPane();
    private static Pane productFuncsPane = ProductPane.getProductPane();
    private static Pane root = null;
}