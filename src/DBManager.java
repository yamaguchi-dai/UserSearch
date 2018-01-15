
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class DBManager {
	public static Connection getConnection() throws Exception {
		final ResourceBundle DB_KEY_RB = ResourceBundle.getBundle("DB_key");
		final String USER = DB_KEY_RB.getString("user");
		final String PASS = DB_KEY_RB.getString("pass");
		final String IP = DB_KEY_RB.getString("ip");
		final String PORT = DB_KEY_RB.getString("port");

		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://"+IP+":"+PORT+"/Breking_db?useUnicode=true&characterEncoding=utf8", USER, PASS);
		return con;
	}
}
