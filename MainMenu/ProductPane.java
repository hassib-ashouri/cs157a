import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

class ProductPane
{
	public static Pane getProductPane() {
		Button  
        registeredUsersBtn = new Button("Registered Users"),
        newUserBtn = new Button("Insert New User"),
        usersDontbuy = new Button("Users That Don't buy"),
        backToMainMenuBtn = new Button(" <-- Main Menu");
        
        VBox menuPane = new VBox(10);
        menuPane.setPadding(new Insets(10,25,10,25));
        menuPane.setAlignment(Pos.TOP_CENTER);

        menuPane.getChildren().addAll(new Pane(backToMainMenuBtn), registeredUsersBtn, newUserBtn,usersDontbuy);
        //buttons styling.
        backToMainMenuBtn.setAlignment(Pos.TOP_LEFT);
        registeredUsersBtn.setStyle("-fx-font-size: 3em;");
        newUserBtn.setStyle("-fx-font-size: 3em;");
        usersDontbuy.setStyle("-fx-font-size: 3em;");

        //adding listeners.
        backToMainMenuBtn.setOnAction(MainMenu.backToMainMenu());

        return menuPane;
	}
}