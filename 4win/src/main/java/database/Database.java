package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

	public Database() throws Exception {
		try {
			// Verbindung zur Datenbank herstellen
			Connection con = getConnection();
			// Tabelle "Spiele" anlegen
			Statement stmt1 = con.createStatement();
			String sql1 = "CREATE TABLE Spiele(ID INTEGER NOT NULL IDENTITY, Gegner VARCHAR(25) NOT NULL, Sieger VARCHAR(25) NOT NULL, Startspieler VARCHAR(25) NOT NULL)";
			ResultSet rs1 = stmt1.executeQuery(sql1);
			rs1.close();
			stmt1.close();
			con.close();
		} catch (SQLSyntaxErrorException e) {
			System.out.println(e.getMessage());
		}

		try {
			// Verbindung zur Datenbank herstellen
			Connection con = getConnection();
			// Tabelle "Sätze" anlegen
			Statement stmt2 = con.createStatement();
			String sql2 = "CREATE TABLE Saetze(ID INTEGER NOT NULL IDENTITY, Spiel INTEGER NOT NULL, Sieger VARCHAR(25) NOT NULL)";
			ResultSet rs2 = stmt2.executeQuery(sql2);
			rs2.close();
			stmt2.close();
			con.close();
		} catch (SQLSyntaxErrorException e) {
			System.out.println(e.getMessage());
		}

		try {
			// Verbindung zur Datenbank herstellen
			Connection con = getConnection();
			// Tabelle "Züge" anlegen
			Statement stmt3 = con.createStatement();
			String sql3 = "CREATE TABLE Zuege(ID INTEGER NOT NULL IDENTITY, Satz INTEGER NOT NULL, Zeile INTEGER NOT NULL, Spalte INTEGER NOT NULL, Spieler INTEGER NOT NULL)";
			ResultSet rs3 = stmt3.executeQuery(sql3);
			rs3.close();
			stmt3.close();
			con.close();
		} catch (SQLSyntaxErrorException e) {
			System.out.println(e.getMessage());
		}
	}

	private static Connection getConnection() throws Exception {
		try {
			// Datenbanktreiber laden
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Treiberklasse nicht gefunden!");
		}

		// Verbindung zur Datenbank herstellen
	    return DriverManager.getConnection("jdbc:hsqldb:file:src/res/Datenhaltung;shutdown=true", "root", "Welcome1");
	  }
	
	public void selectSpiele() throws Exception {
		try {
			// Verbindung zur Datenbank herstellen
			Connection con = getConnection();
			// Alle Einträge aus der Tabelle Spiele auslesen
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM Spiele";

			ResultSet res = stmt.executeQuery(sql);
			ResultSetMetaData resmd = res.getMetaData();
			int columnCount = resmd.getColumnCount();

			List rows = new ArrayList();
			while (res.next()) {
				String[] row = new String[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = res.getString(i);
				}
				rows.add(row);
			}

			res.close();
			stmt.close();
			con.close();

			String[][] rowData = (String[][]) rows.toArray(new String[rows.size()][columnCount]);
			for (int j = 1; j <= columnCount; j++) 
			{
				System.out.print(resmd.getColumnName(j) + " | ");
			}
			System.out.println(" ");
			System.out.println("---------------------------");
			for (int i = 0; i < rows.size(); i++) {

				for (int j = 0; j < columnCount; j++) {
					System.out.print(rowData[i][j] + " | ");

				}
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectSaetze() throws Exception {
		try {
			// Verbindung zur Datenbank herstellen
			Connection con = getConnection();
			// Alle Einträge aus der Tabelle Saetze auslesen
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM Saetze";

			ResultSet res = stmt.executeQuery(sql);
			ResultSetMetaData resmd = res.getMetaData();
			int columnCount = resmd.getColumnCount();

			List rows = new ArrayList();
			while (res.next()) {
				String[] row = new String[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = res.getString(i);
				}
				rows.add(row);
			}

			res.close();
			stmt.close();
			con.close();

			String[][] rowData = (String[][]) rows.toArray(new String[rows.size()][columnCount]);
			for (int j = 1; j <= columnCount; j++) 
			{
				System.out.print(resmd.getColumnName(j) + " | ");
			}
			System.out.println(" ");
			System.out.println("---------------------------");
			for (int i = 0; i < rows.size(); i++) {

				for (int j = 0; j < columnCount; j++) {
					System.out.print(rowData[i][j] + " | ");

				}
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectZuege() throws Exception {
		try {
			// Verbindung zur Datenbank herstellen
			Connection con = getConnection();
			// Alle Einträge aus der Tabelle Zuege auslesen
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM Zuege";

			ResultSet res = stmt.executeQuery(sql);
			ResultSetMetaData resmd = res.getMetaData();
			int columnCount = resmd.getColumnCount();

			List rows = new ArrayList();
			while (res.next()) {
				String[] row = new String[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = res.getString(i);
				}
				rows.add(row);
			}

			res.close();
			stmt.close();
			con.close();
			
			String[][] rowData = (String[][]) rows.toArray(new String[rows.size()][columnCount]);
			for (int j = 1; j <= columnCount; j++) 
			{
				System.out.print(resmd.getColumnName(j) + " | ");
			}
			System.out.println(" ");
			System.out.println("---------------------------");
			for (int i = 0; i < rows.size(); i++) {

				for (int j = 0; j < columnCount; j++) 
				{
					System.out.print(rowData[i][j] + " | ");
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertSpiele(String gegner, String sieger, String startspieler) throws Exception {
		// Verbindung zur Datenbank herstellen 
		Connection con = getConnection();
		// Einfügen eines neuen Spiels
		String sqlSelect = "SELECT * FROM SPIELE";
		ResultSet res = executeSQL(sqlSelect);

		int index = 0;
		try {
			while (res.next()) {
				if (res.getInt(1) > index) {
					index = res.getInt(1);
				}
			}
			index++;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			res.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement prep = null;
		try {
			String sqlInsert = "INSERT INTO Spiele VALUES (?, ?, ?, ?)";
			prep = con.prepareStatement(sqlInsert);
			prep.setInt(1, index);
			prep.setString(2, gegner);
			prep.setString(3, sieger);
			prep.setString(4, startspieler);
			prep.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insertSaetze(int spiel, String sieger) throws Exception {
		// Verbindung zur Datenbank herstellen 
		Connection con = getConnection();
		// Einfügen eines neuen Spiels
		String sqlSelect = "SELECT * FROM Saetze";
		ResultSet res = executeSQL(sqlSelect);

		int index = 0;
		try {
			while (res.next()) {
				if (res.getInt(1) > index) {
					index = res.getInt(1);
				}
			}
			index++;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			res.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement prep = null;
		try {
			String sqlInsert = "INSERT INTO Saetze VALUES (?, ?, ?)";
			prep = con.prepareStatement(sqlInsert);
			prep.setInt(1, index);
			prep.setInt(2, spiel);
			prep.setString(3, sieger);
			prep.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertZuege(int satz, int zeile, int spalte, int spieler) throws Exception {
		// Verbindung zur Datenbank herstellen 
		Connection con = getConnection();
		// Einfügen eines neuen Spiels
		String sqlSelect = "SELECT * FROM Zuege";
		ResultSet res = executeSQL(sqlSelect);

		int index = 0;
		try {
			while (res.next()) {
				if (res.getInt(1) > index) {
					index = res.getInt(1);
				}
			}
			index++;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			res.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement prep = null;
		try {
			String sqlInsert = "INSERT INTO Zuege VALUES (?, ?, ?, ?, ?)";
			prep = con.prepareStatement(sqlInsert);
			prep.setInt(1, index);
			prep.setInt(2, satz);
			prep.setInt(3, zeile);
			prep.setInt(4, spalte);
			prep.setInt(5, spieler);
			prep.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteSpiele() throws Exception {
		// Verbindung zur Datenbank herstellen
		Connection con = getConnection();
		// Löschen aller Einträge in Spiele
		Statement stmt = null;
		try {
			String sqlDelete = "DELETE FROM SPIELE";
			stmt = con.createStatement();			
			stmt.executeUpdate(sqlDelete);
			System.out.println("Alle Daten sind gelöscht");
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void deleteSpiele(int ID) throws Exception {
		// Verbindung zur Datenbank herstellen
		Connection con = getConnection();
		// Löschen einzelner Einträge in Spiele
		PreparedStatement prep = null;
		try {
			String sqlDelete = "DELETE FROM SPIELE WHERE ID = ?";
			prep = con.prepareStatement(sqlDelete);
			prep.setInt(1, ID);
			prep.executeUpdate();
			System.out.println("ID " + ID + " wurde gelöscht");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
	
	public void deleteSaetze() throws Exception {
		// Verbindung zur Datenbank herstellen
		Connection con = getConnection();
		// Löschen aller Einträge in Spiele
		Statement stmt = null;
		try {
			String sqlDelete = "DELETE FROM Saetze";
			stmt = con.createStatement();			
			stmt.executeUpdate(sqlDelete);
			System.out.println("Alle Daten sind gelöscht");
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void deleteSaetze(int ID) throws Exception {
		// Verbindung zur Datenbank herstellen
		Connection con = getConnection();
		// Löschen einzelner Einträge in Spiele
		PreparedStatement prep = null;
		try {
			String sqlDelete = "DELETE FROM Saetze WHERE ID = ?";
			prep = con.prepareStatement(sqlDelete);
			prep.setInt(1, ID);
			prep.executeUpdate();
			System.out.println("ID " + ID + " wurde gelöscht");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
	
	public void deleteZuege() throws Exception {
		// Verbindung zur Datenbank herstellen
		Connection con = getConnection();
		// Löschen aller Einträge in Spiele
		Statement stmt = null;
		try {
			String sqlDelete = "DELETE FROM Zuege";
			stmt = con.createStatement();			
			stmt.executeUpdate(sqlDelete);
			System.out.println("Alle Daten sind gelöscht");
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void deleteZuege(int ID) throws Exception {
		// Verbindung zur Datenbank herstellen
		Connection con = getConnection();
		// Löschen einzelner Einträge in Spiele
		PreparedStatement prep = null;
		try {
			String sqlDelete = "DELETE FROM Zuege WHERE ID = ?";
			prep = con.prepareStatement(sqlDelete);
			prep.setInt(1, ID);
			prep.executeUpdate();
			System.out.println("ID " + ID + " wurde gelöscht");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
	
	public ResultSet getSpiele() throws Exception {
		// Verbindung zur Datenbank herstellen
		Connection con = getConnection();
		ResultSet res = null;
		Statement stmt = null;
		
		try {
			
			// Alle Einträge aus der Tabelle Spiele auslesen
			stmt = con.createStatement();
			String sql = "SELECT * FROM Spiele";
			res = stmt.executeQuery(sql);
			
			return res;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				res.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
		
	}
	
	private static void outputResultSet(ResultSet rs) throws Exception {
	    ResultSetMetaData rsMetaData = rs.getMetaData();
	    int numberOfColumns = rsMetaData.getColumnCount();
	    for (int i = 1; i < numberOfColumns + 1; i++) {
	      String columnName = rsMetaData.getColumnName(i);
	      System.out.print(columnName + "   ");

	    }
	    System.out.println();
	    System.out.println("----------------------");

	    while (rs.next()) {
	      for (int i = 1; i < numberOfColumns + 1; i++) {
	        System.out.print(rs.getString(i) + "   ");
	      }
	      System.out.println();
	    }
	  }
	
	public ResultSet executeSQL(String sql) throws Exception {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();

			ResultSet res = stmt.executeQuery(sql);

			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return null;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Database db = new Database();
//		db.insertSpiele("y", "y", "y");
//		db.insertSpiele("y", "y", "x");
//		db.insertSpiele("y", "x", "x");
//		db.insertSpiele("x", "y", "x");
//		db.insertSpiele("y", "y", "y");
		//db.insertSaetze(1, "x");
		//db.insertZuege(1, 5, 3, "x");
		//db.insertZuege(1, 5, 4, "y");
		db.selectZuege();
		db.selectSaetze();
//		db.selectSpiele();
//		db.deleteSpiele(3);
		db.selectSpiele();
		//db.deleteSpiele();
		// db.selectSaetze();
		// db.selectZuege();
	}
}
