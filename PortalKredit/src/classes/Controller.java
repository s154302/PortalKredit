package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

// REMEMBER st IS STATEMENT

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
		String hash = null;
		try {
			Connection con;
			con = ds1.getConnection();

			PreparedStatement ps = con
					.prepareStatement("SELECT PASSWORD FROM \"DTUGRP16\".\"CLIENT\" WHERE \"CLIENTID\"=?");

			ps.setString(1, clientID);

			ResultSet rs = ps.executeQuery();
			rs.next();
			hash = rs.getString("PASSWORD");
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BCrypt.checkpw(password, hash);
	}

	// maybe private
	public static boolean bankerAuthenticate(String bankerID, String password, DataSource ds1) {
		boolean st = false;
		String hash = null;
		try {
			Connection con;
			con = ds1.getConnection();

			PreparedStatement ps = con
					.prepareStatement("SELECT PASSWORD FROM \"DTUGRP16\".\"BANKER\" WHERE \"BANKERID\"=?");

			ps.setString(1, bankerID);

			ResultSet rs = ps.executeQuery();
			rs.next();
			hash = rs.getString("PASSWORD");
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BCrypt.checkpw(password, hash);
	}

	// maybe private ?? - I don't think you can when we say Controller.W/E ;)
	public static boolean adminAuthenticate(String adminID, String password, DataSource ds1) {
		boolean st = false;
		String hash = null;
		try {
			Connection con;
			con = ds1.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ADMIN\" WHERE \"ADMINID\"=?");

			ps.setString(1, adminID);

			ResultSet rs = ps.executeQuery();
			rs.next();
			hash = rs.getString("PASSWORD");
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BCrypt.checkpw(password, hash);
	}

	// Fills a banker client object with data and returns it
	public static Banker getBankerInfo(String userId, DataSource ds1) {
		Banker banker = new Banker();
		Connection con;

		try {
			con = ds1.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"BANKER\" WHERE \"BANKERID\"=?");

			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			rs.next();

			banker.setBankerID(rs.getString("BANKERID"));
			banker.setFirstName(rs.getString("FIRSTNAME"));
			banker.setLastName(rs.getString("LASTNAME"));
			banker.setRegNo(rs.getInt("REGNO"));
			banker.setEmail(rs.getString("EMAIL"));
			banker.setPhoneNo(rs.getString("MOBILE"));

			// TODO - Consider if this only should be called when the
			// information is needed
			banker.setClients(getClients(userId, ds1));

			rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return banker;
	}

	// Fills a single client object with data and returns it
	public static Client getClientInfo(String userId, DataSource ds1) {
		Client client = new Client();
		Connection con;

		try {
			con = ds1.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"CLIENT\" WHERE \"CLIENTID\"=?");

			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			rs.next();

			client.setClientID(rs.getString("CLIENTID"));
			client.setFirstName(rs.getString("FIRST_NAME"));
			client.setLastName(rs.getString("LAST_NAME"));
			client.setEmail(rs.getString("EMAIL"));
			client.setPhoneNo(rs.getString("MOBILE"));
			client.setCPR(rs.getString("CPR"));
			client.setCountry(rs.getString("COUNTRY"));
			client.setPostal(rs.getInt("POSTAL"));
			client.setStreet(rs.getString("STREET"));
			client.setCity(findCity(rs.getInt("POSTAL"), rs.getString("COUNTRY"), ds1));

			// Consider if we want to fill the Client with its account info
			// before it's used
			client.setAccounts(getAccounts(userId, ds1));

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return client;
	}

	// Returns all accounts, as an ArrayList, associated with a single client
	public static ArrayList<Account> getAccounts(String clientID, DataSource ds1) {

		ArrayList<Account> accountList = new ArrayList<Account>();
		Connection con;

		try {
			con = ds1.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ACCOUNT\" WHERE \"CLIENTID\"=?");
			ps.setString(1, clientID);

			ResultSet rs = ps.executeQuery();

			Account account;
			while (rs.next()) {
				account = new Account(rs.getString("ACCOUNTNUMBER"), rs.getInt("REGNO"), rs.getString("ACCOUNTTYPE"),
						rs.getString("CLIENTID"), rs.getDouble("BALANCE"), rs.getString("CURRENCY"),
						findInterestRate(rs.getString("ACCOUNTTYPE"), ds1));
				account.setTransactions(get3NewestTransactions(account.getAccountNumber(), account.getRegNo(), ds1));
				accountList.add(account);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountList;
	}

	// Fills a single account object with data and returns it
	public static Account getAccountInfo(String accountNumber, int regNo, DataSource ds1) {
		Account account = new Account();

		Connection con;

		try {
			con = ds1.getConnection();

			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM \"DTUGRP16\".\"ACCOUNT\" WHERE \"ACCOUNTNUMBER\"=? AND \"REGNO\"=?");

			ps.setString(1, accountNumber);
			ps.setInt(2, regNo);
			ResultSet rs = ps.executeQuery();

			account.setAccountNumber(rs.getString("ACCOUNTNUMBER"));
			account.setRegNo(rs.getInt("REGNO"));
			account.setAccountType(rs.getString("ACCOUNTTYPE"));
			account.setClientID(rs.getString("CLIENTID"));
			account.setBalance(rs.getDouble("BALANCE"));
			account.setCurrency("CURRENCY");

			// TODO - Consider if this only should be done when the information
			// is actually needed
			account.setTransactions(getNewTransactions(rs.getString("ACCOUNTNUMBER"), rs.getInt("REGNO"), ds1));

			rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return account;
	}

	// Returns the 3 newest transactions associated with an account number and
	// regno
	public static ArrayList<Transaction> get3NewestTransactions(String accountNumber, int regNo, DataSource ds1) {
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

		Connection con;
		try {
			con = ds1.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM \"DTUGRP16\".\"TRANSACTION\" WHERE (\"ACCOUNTNUMBER\" = ? AND \"REGNO\" = ?) OR (\"RECIEVEACCOUNT\" = ? AND \"RECIEVEREGNO\" = ?) "
							+ "ORDER BY DATEOFTRANSACTION DESC FETCH FIRST 3 ROWS ONLY");

			ps.setString(1, accountNumber);
			ps.setInt(2, regNo);
			ps.setString(3, accountNumber);
			ps.setInt(4, regNo);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				transactionList.add(new Transaction(rs.getInt("TRANSACTIONID"), rs.getString("ACCOUNTNUMBER"),
						rs.getInt("REGNO"), rs.getString("RECIEVEACCOUNT"), rs.getInt("RECIEVEREGNO"),
						rs.getDate("DATEOFTRANSACTION"), rs.getDouble("AMOUNT"), rs.getString("CURRENCY")));
			}
			if (transactionList.size() < 3) {
				for (int i = 0; i < 3 - transactionList.size(); i++) {
					transactionList.add(null);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transactionList;
	}

	// Returns all 'new' transactions associated with an account
	public static ArrayList<Transaction> getNewTransactions(String accountNumber, int regNo, DataSource ds1) {

		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		Connection con;
		try {
			con = ds1.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM \"DTUGRP16\".\"TRANSACTION\" WHERE (\"ACCOUNTNUMBER\" = ? AND \"REGNO\" = ?) OR (\"RECIEVEACCOUNT\" = ? AND \"RECIEVEREGNO\" = ?)");

			ps.setString(1, accountNumber);
			ps.setInt(2, regNo);
			ps.setString(3, accountNumber);
			ps.setInt(4, regNo);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				transactionList.add(new Transaction(rs.getInt("TRANSACTIONID"), rs.getString("ACCOUNTNUMBER"),
						rs.getInt("REGNO"), rs.getString("RECIEVEACCOUNT"), rs.getInt("RECIEVEREGNO"),
						rs.getDate("DATEOFTRANSACTION"), rs.getDouble("AMOUNT"), rs.getString("CURRENCY")));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactionList;
	}

	// Fills a single Admin object with data and returns it
	public static Admin getAdminInfo(String userID, DataSource ds1) {
		Admin admin = new Admin("", "");

		Connection con;

		try {
			con = ds1.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ADMIN\" WHERE \"ADMINID\"=?");

			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			admin.setUsername(rs.getString("ADMINID"));
			admin.setPassword(rs.getString("PASSWORD"));

			rs.close();
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

			PreparedStatement ps = con
					.prepareStatement("INSERT INTO \"DTUGRP16\".\"ADMIN\" (ADMINID, password) VALUES(?, ?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Returns all clients associated with a single banker
	public static ArrayList<Client> getClients(String bankerID, DataSource ds1) {
		ArrayList<Client> clientList = new ArrayList<>();
		Connection con;
		try {
			con = ds1.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"CLIENT\" WHERE \"CLIENTID\" = ?");

			ps.setString(1, bankerID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				clientList.add(new Client(rs.getString("EMAIL"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"),
						rs.getString("CLIENTID"), rs.getString("STREET"), rs.getString("COUNTRY"),
						findCity(rs.getInt("POSTAL"), rs.getString("COUNTRY"), ds1), rs.getString("CPR"),
						rs.getString("PHONENO"), rs.getInt("POSTAL")));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientList;
	}

	// Returns the city associated with the postal and country
	public static String findCity(int postal, String country, DataSource ds1) {
		String city = "Orgrimmar";
		Connection con;
		try {
			con = ds1.getConnection();

			PreparedStatement ps = con.prepareStatement(
					"SELECT CITY FROM \"DTUGRP16\".\"PLACE\" WHERE \"POSTAL\" = ? AND \"COUNTRY\" = ?");

			ps.setInt(1, postal);
			ps.setString(2, country);
			ResultSet rs = ps.executeQuery();

			rs.next();

			city = rs.getString("CITY");

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return city;
	}

	// Returns the interestRate associated with the given account type
	public static double findInterestRate(String accountType, DataSource ds1) {
		double rate = 0;
		Connection con;
		try {
			con = ds1.getConnection();

			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ACCOUNTTYPE\" WHERE \"ACCOUNTTYPE\" = ?");

			ps.setString(1, accountType);
			ResultSet rs = ps.executeQuery();

			rs.next();

			rate = rs.getDouble("INTERESTRATE");

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rate;
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

	// Returns all admins in the database
	public static ArrayList<Admin> getAdminList(DataSource ds1) {
		ArrayList<Admin> adminList = new ArrayList<>();
		Connection con;

		try {
			con = ds1.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ADMIN\"");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				adminList.add(new Admin(rs.getString(1), rs.getString(2)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adminList;
	}

	public static void deleteAdmin(String adminID, DataSource ds1) {
		Connection con;
		try {
			con = ds1.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM \"DTUGRP16\".\"ADMIN\" WHERE \"ADMINID\"=?");
			ps.setString(1, adminID);
			ps.executeUpdate();

		} catch (SQLException e) {
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

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO \"DTUGRP16\".\"BANKER\" (BANKERID, PASSWORD, FIRSTNAME, LASTNAME, REGNO, EMAIL, MOBILE) VALUES(?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, generateBankerID(ds1));
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, regno);
			ps.setString(6, email);
			ps.setString(7, telephone);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void deleteClient(String clientID, DataSource ds1) {
		Connection con;
		try {
			con = ds1.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM \"DTUGRP16\".\"CLIENT\" WHERE \"CLIENTID\"=?");
			ps.setString(1, clientID);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Returns all clients in the database
	public static ArrayList<Client> getClientList(DataSource ds1) {

		ArrayList<Client> clientList = new ArrayList<>();
		Connection con;

		try {
			con = ds1.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"CLIENT\"");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Client client = setClientInfo(rs);
				clientList.add(client);
				// TODO: fill out this shit

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientList;
	}

	public static Client setClientInfo(ResultSet rs) {
		Client client = new Client();
		try {
			client.setClientID(rs.getString("CLIENTID"));
			client.setFirstName(rs.getString("FIRST_NAME"));
			client.setLastName(rs.getString("LAST_NAME"));
			client.setEmail(rs.getString("EMAIL"));
			client.setPhoneNo(rs.getString("MOBILE"));
			client.setCPR(rs.getString("CPR"));
			client.setCountry(rs.getString("COUNTRY"));
			client.setPostal(rs.getInt("POSTAL"));
			client.setStreet(rs.getString("STREET"));
			return client;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

	// Returns all bankers in the database
	public static ArrayList<Banker> getBankerList(DataSource ds1) {

		ArrayList<Banker> bankerList = new ArrayList<>();
		Connection con;

		try {
			con = ds1.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"BANKER\"");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// TODO: fill out this shit
				Banker banker = new Banker();
				banker.setBankerID(rs.getString("BANKERID"));
				banker.setFirstName(rs.getString("FIRSTNAME"));
				banker.setLastName(rs.getString("LASTNAME"));
				banker.setRegNo(rs.getInt("REGNO"));
				banker.setEmail(rs.getString("EMAIL"));
				banker.setPhoneNo(rs.getString("MOBILE"));
				bankerList.add(banker);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bankerList;
	}

	public static void deleteBanker(String bankerID, DataSource ds1) {
		Connection con;
		try {
			con = ds1.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM \"DTUGRP16\".\"BANKER\" WHERE \"BANKERID\"=?");
			ps.setString(1, bankerID);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<Client> searchClient(String search, DataSource ds1) {
		ArrayList<Client> result = new ArrayList<>();
		ArrayList<String> terms = new ArrayList<>();
		Connection con;
		System.out.println(search);
		try {
			con = ds1.getConnection();
			PreparedStatement ps;

			if (search.contains(" ")) {
				for (int i = 0; i < search.length(); i++) {
					if (search.charAt(i) == ' ') {
						terms.add(search.substring(0, i));
						terms.add(search.substring(i + 1));
						break;
					}
				}

				ps = con.prepareStatement(
						"SELECT * FROM \"DTUGRP16\".\"CLIENT\" WHERE ((\"FIRST_NAME\" LIKE ?) AND (\"LAST_NAME\" LIKE ?)) OR (\"LAST_NAME\" LIKE ?)");

				ps.setString(1, "%" + terms.get(0) + "%");
				ps.setString(2, "%" + terms.get(1) + "%");
				ps.setString(3, "%" + search + "%");
				ps.executeQuery();
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					result.add(setClientInfo(rs));
				}
			} else {
				ps = con.prepareStatement(
						"SELECT * FROM \"DTUGRP16\".\"CLIENT\" WHERE (\"FIRST_NAME\" LIKE ?) OR (\"LAST_NAME\" LIKE ?)");
				ps.setString(1, "%" + search + "%");
				ps.setString(2, "%" + search + "%");
				ps.executeQuery();
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					result.add(setClientInfo(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
