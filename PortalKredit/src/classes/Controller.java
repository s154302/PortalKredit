package classes;

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

	public static Banker getBankerInfo(String userId, DataSource ds1) {
		Banker banker = new Banker();
		Connection con;

		try {
			con = ds1.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"BANKER\" WHERE \"BANKERID\"=?");

			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			rs.next();
			
			banker.setBankerID(rs.getString(userId));
			banker.setFirstName(rs.getString("FIRSTNAME"));
			banker.setLastName(rs.getString("LASTNAME"));
			banker.setRegNo(rs.getInt("REGNO"));
			banker.setEmail(rs.getString("EMAIL"));
			banker.setPhoneNo(rs.getInt("MOBILE"));
			ArrayList<String> clientsID  = getList("CLIENT", "BANKERID", userId, "CLIENTID", ds1);
			ArrayList<Client> clients = new ArrayList<Client>();
			for(String clientId : clientsID){
				clients.add(getClientInfo(clientId, ds1));
			}
			banker.setClients(clients);
			
			rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return banker;
	}

	public static Client getClientInfo(String userId, DataSource ds1) {
		Client client = new Client();
		Connection con;

		try {
			con = ds1.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"CLIENT\" WHERE \"CLIENTID\"=?");

			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			// TODO: Set all client's data (Requires database to be set up)
			rs.next();
			
			client.setClientID(rs.getString("CLIENTID"));
			client.setFirstName(rs.getString("FIRST_NAME"));
			client.setLastName(rs.getString("LAST_NAME"));
			client.setEmail(rs.getString("EMAIL"));
			client.setPhoneNo(rs.getInt("MOBILE"));
			client.setCPR(rs.getInt("CPR"));
			client.setCountry(rs.getString("COUNTRY"));
			client.setPostal(rs.getInt("POSTAL"));
			client.setStreet(rs.getString("STREET"));
			
			
			ps = con.prepareStatement("SELECT CITY FROM \"DTUGRP16\".\"CLIENT FULL OUTER JOIN \"DTUGRP16\".\"PLACE\" "
					+ "ON \"DTUGRP16\".\"CLIENT\".\"COUNTRY\" = \"DTUGRP16\".\"PLACE\".\"COUNTRY\" "
					+ "AND \"DTUGRP16\".\"CLIENT\".\"POSTAL\" = \"DTUGRP16\".\"PLACE\".\"POSTAL\" WHERE \"CLIENTID \"= ?");
			ResultSet city = ps.executeQuery();
			client.setCity(city.getString("CITY"));
			

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return client;
	}
	
	public static Account getAccountInfo(){
		Account account = new Account();
		return account;
	}
	
	public static Admin getAdminInfo(String userID, DataSource ds1){
		Admin admin = new Admin("", "");
		
		Connection con;

		try {
			con = ds1.getConnection();

			// TODO: Edit ps to correct table
//			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"USER\" WHERE \"USERID\"=?");
//
//			ps.setString(1, userID);
//			ResultSet rs = ps.executeQuery();

			// TODO: Set all banker's data (Requires database to be set up)

//			rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return admin;
	}

	public static void createClient(String firstName, String lastName, String password, String CPR, String email,
			String mobile, String street, String bankerID, Integer postal, String country, DataSource ds1) {
		Connection con;

		try {
			con = ds1.getConnection();

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO \"DTUGRP16\".\"CLIENT\" (CLIENTID, PASSWORD, CPR, FIRST_NAME, LAST_NAME, EMAIL, MOBILE, STREET, BANKERID, POSTAL, COUNTRY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, generateClientID(ds1));
			ps.setString(2, password);
			ps.setString(3, CPR);
			ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setString(6, email);
			ps.setString(7, mobile);
			ps.setString(8, street);
			ps.setString(9, bankerID);
			ps.setInt(10, postal);
			ps.setString(11, country.toUpperCase());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createAdmin(String username, String password, DataSource ds1) {
		Connection con;
		
		try {
			con = ds1.getConnection();
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO \"DTUGRP16\".\"ADMIN\" (ADMINID, password) VALUES(?, ?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
		} catch(SQLException e) {
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
	
	public static ArrayList<Admin> getAdminList(DataSource ds1){
		ArrayList<Admin> adminList = new ArrayList<>();
		Connection con;
		
		try{
			con = ds1.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ADMIN\"");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				adminList.add(new Admin(rs.getString(1),rs.getString(2)));
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return adminList;
	}

	public static void deleteAdmin(String adminID, DataSource ds1) {
		Connection con;
		try{
			con = ds1.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM \"DTUGRP16\".\"ADMIN\" WHERE \"ADMINID\"=?");
			ps.setString(1, adminID);
			ps.executeUpdate();
			

			
		
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
	}

	private static String generateBankerID(DataSource ds1) {
		String ID = null;
		int intID = 0;
		Connection con;

		try {
			con = ds1.getConnection();

			// Select the latest ID, and extract only the ID number as an
			// integer
			PreparedStatement ps = con.prepareStatement(
					"(SELECT INTEGER(SUBSTR(bankerID, 1, 6)) FROM \"DTUGRP16\". \"BANKER\" ORDER BY bankerID DESC FETCH FIRST 1 ROWS ONLY)");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				intID = rs.getInt(1);
			}

			if (intID > 0) {
				ID = String.format("%06d", intID + 1) + "B";
			} else {
				ID = "000001B";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ID;
	}

	public static void createBanker(String firstName, String lastName, String password, String email, String telephone,
			String regno, DataSource ds1) {
		Connection con;

		try {
			con = ds1.getConnection();

			PreparedStatement ps = con.prepareStatement("INSERT INTO \"DTUGRP16\".\"BANKER\" (BANKERID, PASSWORD, FIRSTNAME, LASTNAME, REGNO, EMAIL, MOBILE) VALUES(?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, generateBankerID(ds1));
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, regno);
			ps.setString(6, email);
			ps.setString(7, telephone);
			ps.executeUpdate(); 
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
