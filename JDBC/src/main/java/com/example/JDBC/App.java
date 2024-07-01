package com.example.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
	// connection is responsible to make connection with database
	Connection conn = null;
	// statement is use to execute sql queries in mysql server
	Statement stm = null;
	// its good to use for insert lot of data
	PreparedStatement preStatement = null;

	App() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // using here driver to connect with database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "cogent");
			System.out.println("Connected ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	void fetchAllData() throws Exception {
		String query = "select * from records";
		stm = conn.createStatement();
		// execute select query and return Resultset (Data in interface)
		ResultSet set = stm.executeQuery(query);
		while (set.next()) {
			System.out.println(set.getInt(1) + set.getString(2) + set.getString(3) + set.getString(4) + set.getString(5)
					+ set.getString(6));
		}
	}

	void insertData(String name, String purchase, String email, String password, String phone) throws Exception {
		String query = "insert into records(name,purchase,email,password,phone) values(?,?,?,?,?)";
		preStatement = conn.prepareStatement(query);
		preStatement.setString(1, name);
		preStatement.setString(2, purchase);
		preStatement.setString(3, email);
		preStatement.setString(4, password);
		preStatement.setString(5, phone);
		preStatement.executeUpdate(); // making change into records table
	}

	void fetchSingleData(String email) throws Exception {
		preStatement = conn.prepareStatement("select * from records where email = ?");
		preStatement.setString(1, email);
		ResultSet set = preStatement.executeQuery();
		set.next();
		System.out.println(set.getInt(1) + set.getString(2) + set.getString(3) + set.getString(4) + set.getString(5));
	}

	Record fetchSingleData2(String email) throws Exception {
		preStatement = conn.prepareStatement("select * from records where email = ?");
		preStatement.setString(1, email);
		ResultSet set = preStatement.executeQuery();
		set.next();
		return new Record(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5),
				set.getString(6));
	}

	void updateRecord(String email) throws Exception {

		// collected all data from mail id
		Record record = fetchSingleData2(email);
		record.setPhone("1234567890"); // new phone updated into record class

		String query = " update records set phone = ? where email = ?";
		preStatement = conn.prepareStatement(query);
		preStatement.setString(1, record.getPhone()); // setting latest phone from record'
		preStatement.setString(2, record.getEmail());
		preStatement.executeUpdate(); // updated phone based on email id

	}

	public static void main(String[] args) throws Exception {

		App app = new App();
		// app.insertData("ravi","50000","ravi@gmail.com","asd","23875235234");
		// app.fetchAllData();
		// app.fetchSingleData("ravi@gmail.com");
		app.updateRecord("ravi@gmail.com");
		app.fetchAllData();
	}
}
