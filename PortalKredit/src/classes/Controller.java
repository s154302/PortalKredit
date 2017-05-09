package classes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public final class Controller {
	public static enum Type {
		client, banker, admin
	}

	public static boolean authenticate(String userID, String password, DataSource ds1, HttpSession session) {
		boolean st = false;

		if (userID.substring(userID.length() - 1).equals("C")) {

			session.setAttribute("type", Type.client);
			return clientAuthenticate(userID, password, ds1);
		} else if (userID.substring(userID.length() - 1).equals("B")) {
			session.setAttribute("type", Type.banker);
			return bankerAuthenticate(userID, password, ds1);
		} else {
			session.setAttribute("type", Type.admin);
			return adminAuthenticate(userID, password, ds1);
		}

	}

	// consider making these private??
	public static boolean clientAuthenticate(String clientID, String password, DataSource ds1) {
		boolean st = false;
		try {
			Connection con;
			con = ds1.getConnection();

			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM \"DTUGRP16\".\"CLIENT\" WHERE \"CLIENTID\"=? AND \"PASSWORD\"=?");

			ps.setString(1, clientID);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			st = rs.next();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return st;
	}

	// maybe private
	public static boolean bankerAuthenticate(String bankerID, String password, DataSource ds1) {
		boolean st = false;
		try {
			Connection con;
			con = ds1.getConnection();

			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM \"DTUGRP16\".\"BANKER\" WHERE \"BANKERID\"=? AND \"PASSWORD\"=?");

			ps.setString(1, bankerID);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			st = rs.next();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return st;
	}

	// maybe private ??
	public static boolean adminAuthenticate(String adminID, String password, DataSource ds1) {
		boolean st = false;
		try {
			Connection con;
			con = ds1.getConnection();

			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ADMIN\" WHERE \"ADMINID\"=? AND \"PASSWORD\"=?");

			ps.setString(1, adminID);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			st = rs.next();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return st;
	}

	public static Banker getBankerInfo(String userID, DataSource ds1) {
		Banker banker = new Banker();
		Connection con;

		try {
			con = ds1.getConnection();

			// TODO: Edit ps to correct table
			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"USER\" WHERE \"USERID\"=?");

			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();

			// TODO: Set all banker's data (Requires database to be set up)

			rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return banker;
	}

	public static Client getClientInfo(String clientID, DataSource ds1) {
		Client client = new Client();
		Connection con;

		try {
			con = ds1.getConnection();

			// TODO: Edit ps to correct table
			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"CLIENT\" WHERE \"CLIENTID\"=?");

			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();

			// TODO: Set all client's data (Requires database to be set up)

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return client;
	}

	public static void createClient(String firstName, String lastName, String password, BigDecimal CPR, String email, String mobile,
			String street, String bankerID, int postal, String country, DataSource ds1) {
		Connection con;

		try {
			con = ds1.getConnection();

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO \"DTUGRP16\".\"CLIENT\" (CLIENTID, PASSWORD, CPR, FIRST_NAME, LAST_NAME, EMAIL, MOBILE, STREET, BANKERID, POSTAL, COUNTRY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, generateClientID(ds1));
			ps.setString(2, password);
			ps.setBigDecimal(3, CPR);
			ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setString(6, email);
			ps.setString(7, mobile);
			ps.setString(8, street);
			ps.setString(9, bankerID);
			ps.setInt(10, postal);
			ps.setString(11, country);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getList(String tableName, String columnName, String key, String resultColumn,
			DataSource ds1) {
		ArrayList<String> list = new ArrayList<>();
		Connection con;
		try {
			con = ds1.getConnection();

			// TODO: This works but needs to be sanitized to avoid SQL
			// injections. Create whitelist
			// "SELECT * FROM \"DTUGRP16\".\"USER\" WHERE \"USERID\"=?"
			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"" + tableName.toUpperCase()
					+ "\" WHERE \"" + columnName.toUpperCase() + "\"=?");
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(resultColumn));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static String generateClientID(DataSource ds1) {
		String ID = null;
		int intID = 0;
		Connection con;

		try {
			con = ds1.getConnection();

			// Select the latest ID, and extract only the ID number as an
			// integer
			PreparedStatement ps = con.prepareStatement(
					"(SELECT INTEGER(SUBSTR(clientID, 1, 8)) FROM \"DTUGRP16\". \"CLIENT\" ORDER BY clientID DESC FETCH FIRST 1 ROWS ONLY)");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				intID = rs.getInt(1);
			}

			if (intID > 0) {
				ID = String.format("%08d", intID + 1) + "C";
			} else {
				ID = "00000001C";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ID;
	}

}
