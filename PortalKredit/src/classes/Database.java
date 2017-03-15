package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

public final class Database {
	
	@Resource(name = "jdbc/DB2")
	static Connection con;
	private static DataSource ds1;
	private static Statement stmt;
	private static ResultSet rs;
	
	public static void main(String[] args) {
		System.out.println(logIn("Alexander", "Armstrong7"));
	}
	
	private static void createConnection() {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			con = ds1.getConnection();
			con.setAutoCommit(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean logIn(String usr, String pass) {
		createConnection();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT password FROM User WHERE username = " + usr);
			if(rs.getString(0).equals(pass)) {
				con.close();
				return true;
			}
			con.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
