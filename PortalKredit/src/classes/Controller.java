package classes;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public final class Controller {
	public static enum Type{
		client, banker, admin
	}

	public static boolean authenticate(String userID, String password, DataSource ds1, HttpSession session) {
		boolean st = false;
		
		if(userID.substring(userID.length() - 1).equals("C")){
			
			session.setAttribute("type", Type.client);
			return clientAuthenticate(userID,password,ds1);
		}
		else if(userID.substring(userID.length() - 1).equals("B")){
			session.setAttribute("type", Type.banker);
			return bankerAuthenticate(userID,password,ds1);
		}
		else
		{
			session.setAttribute("type", Type.admin);
			return adminAuthenticate(userID,password,ds1);
		}
		
	}
	
	
	//consider making these private??
	public static boolean clientAuthenticate(String clientID, String password, DataSource ds1){
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
	//maybe private
	public static boolean bankerAuthenticate(String bankerID, String password, DataSource ds1){
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
	
	//maybe private ??
	public static boolean adminAuthenticate(String adminID, String password, DataSource ds1){
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

	public static ArrayList<String> getList(String tableName, String columnName, int key, String resultColumn,
			DataSource ds1) {
		ArrayList<String> list = new ArrayList<>();
		Connection con;
		try {
			con = ds1.getConnection();

			// TODO: This works but needs to be sanitized to avoid SQL injections. Create whitelist
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"" + tableName.toUpperCase()
					+ "\" WHERE \"" + columnName.toUpperCase() + "\"=?");
			ps.setInt(1, key);
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

}
