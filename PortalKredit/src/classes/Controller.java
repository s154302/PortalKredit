package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public final class Controller {

	public static boolean authenticate(String userID, String password, DataSource ds1) {
		boolean st = false;
		try {
			Connection con;
			con = ds1.getConnection();
			// TODO: Create separate prepared statements for bankers and
			// clients.
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM \"DTUGRP16\".\"USER\" WHERE \"USERID\"=? AND \"PASSWORD\"=?");

			ps.setString(1, userID);
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

	public static Client getClientInfo(String userID, DataSource ds1) {
		Client client = new Client();
		Connection con;

		try {
			con = ds1.getConnection();

			// TODO: Edit ps to correct table
			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"USER\" WHERE \"USERID\"=?");

			ps.setString(1, userID);
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
