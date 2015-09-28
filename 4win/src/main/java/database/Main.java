package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class Main {
	
	Connection con = null;
	
	public Main() throws SQLException
	{
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} 
		catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("Treiberklasse nicht gefunden!");
			return;
		}
		
		Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:hsqldb:file:C:\\Users\\Lucas\\Desktop\\Datenhaltung; shutdown=true", "root", "Welcome1");
				Statement stmt = con.createStatement();
				String sql = "CREATE TABLE SpieleBspTest(ID INTEGER NOT NULL IDENTITY, Gegner VARCHAR(25) NOT NULL, Sieger VARCHAR(25) NOT NULL, Startspieler VARCHAR(25) NOT NULL)";
				ResultSet rs = stmt.executeQuery(sql);
				rs.close();
				stmt.close();
		} 
			catch (SQLSyntaxErrorException e) {
			System.out.println("Note: Table already existing");
	}
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		new Main();
		 
	}
	
	public void selectAll()
	{
		try {
		Statement stmt1 = con.createStatement();
		String sql = "SELECT * FROM Spiele";
		
			ResultSet res = stmt1.executeQuery(sql);
			while(res.next())
			{
				String id = res.getString(1);
				String gegner = res.getString(2);
				String sieger = res.getString(3);
				String startspieler = res.getString(4);
				System.out.println(id + ", " + gegner + " " + sieger + " " + startspieler);
			}
			res.close();
			stmt1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}