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

	Connection con = null;

	public Database() throws SQLException {
		try {
			// Datenbanktreiber laden
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("Treiberklasse nicht gefunden!");
			return;
		}

		// Verbindung zur Datenbank herstellen
		con = DriverManager.getConnection("jdbc:hsqldb:file:src/res/Datenhaltung; shutdown=true", "root", "Welcome1");

		try {
			// Tabelle "Spiele" anlegen
			Statement stmt1 = con.createStatement();
			String sql1 = "CREATE TABLE Spiele(ID INTEGER NOT NULL IDENTITY, Gegner VARCHAR(25) NOT NULL, Sieger VARCHAR(25) NOT NULL, Startspieler VARCHAR(25) NOT NULL)";
			ResultSet rs1 = stmt1.executeQuery(sql1);
			rs1.close();
			stmt1.close();
		} catch (SQLSyntaxErrorException e) {
			System.out.println(e.getMessage());
		}

		try {
			// Tabelle "S�tze" anlegen
			Statement stmt2 = con.createStatement();
			String sql2 = "CREATE TABLE Saetze(ID INTEGER NOT NULL IDENTITY, Spiel INTEGER NOT NULL, Sieger VARCHAR(25) NOT NULL)";
			ResultSet rs2 = stmt2.executeQuery(sql2);
			rs2.close();
			stmt2.close();
		} catch (SQLSyntaxErrorException e) {
			System.out.println(e.getMessage());
		}

		try {
			// Tabelle "Z�ge" anlegen
			Statement stmt3 = con.createStatement();
			String sql3 = "CREATE TABLE Zuege(ID INTEGER NOT NULL IDENTITY, Satz INTEGER NOT NULL, Zeile INTEGER NOT NULL, Spalte INTEGER NOT NULL, Spieler VARCHAR(25) NOT NULL)";
			ResultSet rs3 = stmt3.executeQuery(sql3);
			rs3.close();
			stmt3.close();
		} catch (SQLSyntaxErrorException e) {
			System.out.println(e.getMessage());
		}
	}

	public void selectSpiele() {
		try {
			// Alle Eintr�ge aus der Tabelle Spiele ausgeben
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

			String[][] rowData = (String[][]) rows.toArray(new String[rows.size()][columnCount]);
			for (int j = 1; j <= columnCount; j++) {
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

	public void selectSaetze() {
		try {
			// Alle Eintr�ge aus der Tabelle Spiele ausgeben
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

			String[][] rowData = (String[][]) rows.toArray(new String[rows.size()][columnCount]);
			for (int j = 1; j <= columnCount; j++) {
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

	public void selectZuege() {
		try {
			// Alle Eintr�ge aus der Tabelle Spiele ausgeben
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

			String[][] rowData = (String[][]) rows.toArray(new String[rows.size()][columnCount]);
			for (int j = 1; j <= columnCount; j++) {
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

	public void insertSpiele(String gegner, String sieger, String startspieler) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ResultSet executeSQL(String sql) {
		try {
			Statement stmt = con.createStatement();

			ResultSet res = stmt.executeQuery(sql);

			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Database db = new Database();
		db.insertSpiele("y", "x", "z");
		db.selectSpiele();
		// db.selectSaetze();
		// db.selectZuege();

	}
}
