package com;
import java.sql.*;
public class Billing {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billingmanagement", "root", "Tashmi@2000");
	 }
	 catch (Exception e)
	 {
	 e.printStackTrace();
	 }
	 return con;
	 }
	public String readbill()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>User Id</th><th>Usage(units)</th><th>Month</th><th>Year</th>"+ "<th>Amount</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from bill";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	
	 while (rs.next())
	 {
	 String id = Integer.toString(rs.getInt("id"));
	 String uid= Integer.toString(rs.getInt("uid"));
	 String units= Integer.toString(rs.getInt("units"));
	 String month=rs.getString("month");
	 String year=Integer.toString(rs.getInt("year"));
	 String amount= Float.toString(rs.getFloat("amount"));
		
	 // Add into the html table
	 output += "<tr><td><input id='hidUserIDUpdate' name='hidUserIDUpdate' type='hidden' value='" + id+ "'>" + uid + "</td>";
	 output += "<td>" + units + "</td>";
	 output += "<td>" + month + "</td>";
	 output += "<td>" + year + "</td>";
	 output += "<td>" + amount + "</td>";
	
	 // buttons
	output += "<td><input name='btnUpdate'type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-id='"+ id + "'>" + "</td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the billing.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String insertbill(String uid,String units, String month, String year,String amount)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for inserting.";
	 }
	 // create a prepared statement
	 String query = " insert into bill (`id`,`uid`,`units`,`month`,`year`,`amount`)" + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2,uid );
	 preparedStmt.setString(3,units);
	 preparedStmt.setString(4,month );
	 preparedStmt.setString(5,year );
	 preparedStmt.setString(6,amount );
	
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newBilling = readbill();
	 output = "{\"status\":\"success\", \"data\": \"" +newBilling + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Bill.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updatebill(String id,String uid, String units,String amount)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for updating.";
	 }
	 // create a prepared statement
	 String query = "update bill set unitUsage='"+units+"', amount='"+amount+"' where userid='"+uid+"'";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, uid);
	 preparedStmt.setString(2, units);
	 preparedStmt.setString(3, amount);
	 preparedStmt.setInt(4, Integer.parseInt(id)); 
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newBilling = readbill();
	 output = "{\"status\":\"success\", \"data\": \"" + newBilling + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deletebill(String uid)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for deleting.";
	 }
	 // create a prepared statement
	 String query = "delete from bill where userid='"+uid+"'";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, uid);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newBilling = readbill();
	 output = "{\"status\":\"success\", \"data\": \"" + newBilling + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while deleting the bill.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	}



