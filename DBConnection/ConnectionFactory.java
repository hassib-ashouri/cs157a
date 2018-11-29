import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory
{
    /**
     * get a connection to the local database.
     * @return
     */
    public static Connection getConnection()
    {
        Connection myCon = null;
        try 
        {
			Class.forName("com.mysql.cj.jdbc.Driver");
			myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/homework", "root", "Root291.");
        } 
        catch (SQLException e) 
        {
			System.out.println("connection problem");
		}
        catch(ClassNotFoundException e) 
        {
			System.out.println("problem finding the mysql driver.");
		}
		
		return myCon;
    }
}