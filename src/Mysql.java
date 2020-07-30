import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.AbstractDocument.Content;

public class Mysql {
	static String selectsql;
	static String DB_URL = "jdbc:mysql://localhost:3306/xianrenzhang?useSSL=false&serverTimezone=UTC";
	static Statement stmt;
	static String USER = "root";
	static String PASS = "123456";
	static String sql = "insert into user values (null,?,?,?,?,?)";
	static Connection conn = null;// SQL
	public static boolean flag = false;
	public static String user;
	public static String login_psd;
	public static String Command;
	public static String User;
	public static String school;
	public static String email;
	public static String phone;

	public Mysql(String user, String school, String login_psd) {
		Mysql.user = user;
		Mysql.school = school;
		Mysql.login_psd = login_psd;
	}

	public static void linkDataBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("link database...please watting");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean selectSql(String user, String school, String login_psd)
			throws SQLException, ClassNotFoundException {
		selectsql = "SELECT * FROM user";
		linkDataBase();
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(selectsql);
		String suser = null;
		String sschool = null;
		String slogin_psd = null;
		while (rs.next()) {
			suser = rs.getString("user");
			sschool = rs.getString("name");
			slogin_psd = rs.getString("psd");

			System.out.print("select user: " + suser);
			System.out.println();
			System.out.print("select school: " + sschool);
			System.out.println();
			System.out.println("select login_psd: " + slogin_psd);
			System.out.println();
			System.out.print("\n");
		}
		suser = suser.trim();
		school = school.trim();
		slogin_psd = slogin_psd.trim();

		if (user.equals(suser) && school.equals(sschool)
				&& login_psd.equals(slogin_psd) == true) {
			return true;
		} else {
			return false;
		}
	}

	public static void insertData(String user, String school, String login_psd) {
		String insertsql = "insert into user(user,name,psd) values('" + user
				+ "','" + school + "','" + login_psd + "')";
		System.out.println(insertsql);
		try {
			linkDataBase();
			stmt = (Statement) conn.createStatement();
			stmt.execute(insertsql);
			System.out.println(user + "insert successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
