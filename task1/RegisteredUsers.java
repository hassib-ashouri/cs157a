import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

class RegisteredUsers
{
    public static void getRegisteredUsersPane()
    {
        TableView<User> table = new TableView<>();
        TableColumn col1 = new TableColumn<>("id"),
                    col2 = new TableColumn<>("Username"),
                    col3 = new TableColumn<>("password"),
                    col4 = new TableColumn<>("Full Name"),
                    col5 = new TableColumn<>("Address"),
                    col6 = new TableColumn<>("email"),
                    col7 = new TableColumn<>("phone");
        
        col1.setCellValueFactory(new PropertyValueFactory("id"));
        col2.setCellValueFactory(new PropertyValueFactory("username"));
        col3.setCellValueFactory(new PropertyValueFactory("password"));
    }

    class User
    {
        private String username, password, fullName, add, email, phone;
    }
}