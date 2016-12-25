package database;

import java.math.RoundingMode;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Database {

	/**
	 * Konstruktor zum Erstellen der Datenbanktabellen) 
	 * Tabelle Spiele (ID, Gegner, Sieger, Startspieler)
	 * Tabelle Saetze (ID, SatzCount, Spiel, Sieger)
	 * Tabelle Zuege (ID, Satz, Zeile, Spalte und Spieler)
	 */
	public Database() throws Exception {

		try {
			Connection con = getConnection();
			Statement stmt1 = con.createStatement();
			String sql1 = "CREATE TABLE Spiele(ID INTEGER NOT NULL IDENTITY, Gegner VARCHAR(25) NOT NULL, Sieger INTEGER NOT NULL, Startspieler INTEGER NOT NULL)";
			ResultSet rs1 = stmt1.executeQuery(sql1);
			rs1.close();
			stmt1.close();
			con.close();
		} catch (SQLSyntaxErrorException e) {
			System.out.println(e.getMessage());
		}

		try {
			Connection con = getConnection();
			Statement stmt2 = con.createStatement();
			String sql2 = "CREATE TABLE Saetze(ID INTEGER NOT NULL IDENTITY, SatzCount INTEGER NOT NULL, Spiel INTEGER NOT NULL, Sieger INTEGER NOT NULL)";
			ResultSet rs2 = stmt2.executeQuery(sql2);
			rs2.close();
			stmt2.close();
			con.close();
		} catch (SQLSyntaxErrorException e) {
			System.out.println(e.getMessage());
		}

		try {
			Connection con = getConnection();
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

	/**
	 * Methode zum Herstellen der Datenbankverbindung
	 * 
	 * @return Connection
	 */
	private static Connection getConnection() throws Exception {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Treiberklasse nicht gefunden!");
		}
		return DriverManager.getConnection("jdbc:hsqldb:file:src/res/Datenhaltung;shutdown=true", "root", "Welcome1");
	}

	/**
	 * Methode für ein Select-Statement der Tabelle Spiele
	 */
	public void selectSpiele() throws Exception {
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sqlSelect = "SELECT * FROM Spiele";

			ResultSet res = stmt.executeQuery(sqlSelect);
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
			System.out.println("SELECT SPIELE");
			System.out.println(rowData.length);
			// drawArray(rowData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode für ein Select-Statement der Tabelle Saetze
	 */
	public void selectSaetze() throws Exception {
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sqlSelect = "SELECT * FROM Saetze";

			ResultSet res = stmt.executeQuery(sqlSelect);
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode für ein Select-Statement der Tabelle Zuege
	 */
	public void selectZuege() throws Exception {
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sqlSelect = "SELECT * FROM Zuege";

			ResultSet res = stmt.executeQuery(sqlSelect);
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode zum Speichern eines neuen Spiels
	 * 
	 * @param gegner
	 * @param sieger
	 * @param startspieler
	 */
	public void insertSpiele(String gegner, int sieger, int startspieler) throws Exception {
		Connection con = getConnection();
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
			prep.setInt(3, sieger);
			prep.setInt(4, startspieler);
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

	/**
	 * Methode zum Speichern eines neuen Satzes
	 * 
	 * @param satzCount
	 * @param spiel
	 * @param sieger
	 */
	public void insertSaetze(int satzCount, int spiel, int sieger) throws Exception {
		Connection con = getConnection();
		String sqlSelect = "SELECT * FROM SAETZE";
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
			String sqlInsert = "INSERT INTO SAETZE VALUES (?, ?, ?, ?)";
			prep = con.prepareStatement(sqlInsert);
			prep.setInt(1, index);
			prep.setInt(2, satzCount);
			prep.setInt(3, spiel);
			prep.setInt(4, sieger);
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

	/**
	 * Methode zum Speichern eines neuen Zugs
	 * 
	 * @param satz
	 * @param zeile
	 * @param spalte
	 * @param spieler
	 */
	public void insertZuege(int satz, int zeile, int spalte, int spieler) throws Exception {
		Connection con = getConnection();
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

	/**
	 * Methode zum Löschen aller Einträge in der Tabelle Spiele
	 */
	public void deleteSpiele() throws Exception {
		Connection con = getConnection();
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

	/**
	 * Methode zum Löschen einzelner Einträge in der Tabelle Spiele bei Eingabe
	 * eine ID
	 * 
	 * @param ID
	 */
	public void deleteSpiele(int ID) throws Exception {
		Connection con = getConnection();
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

	/**
	 * Methode zum Löschen aller Einträge in der Tabelle Saetze
	 */
	public void deleteSaetze() throws Exception {
		Connection con = getConnection();
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

	/**
	 * Methode zum Löschen einzelner Einträge in der Tabelle Saetze bei Eingabe
	 * eine ID
	 * 
	 * @param ID
	 */
	public void deleteSaetze(int ID) throws Exception {
		Connection con = getConnection();
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

	/**
	 * Methode zum Löschen aller Einträge in der Tabelle Zuege
	 */
	public void deleteZuege() throws Exception {
		Connection con = getConnection();
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

	/**
	 * Methode zum Löschen einzelner Einträge in der Tabelle Zuege bei Eingabe
	 * eine ID
	 * 
	 * @param ID
	 */
	public void deleteZuege(int ID) throws Exception {
		Connection con = getConnection();
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

	/**
	 * Methode zur Ausgabe eines mit allen Spielen gefüllten ResultSet
	 * 
	 * @return ResultSet
	 */
	public ResultSet getSpiele() throws Exception {

		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT DISTINCT * FROM Spiele";
			ResultSet res = stmt.executeQuery(sql);

			stmt.close();
			con.close();
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Methode zur Ausgabe eines mit allen Sätzen, zu einem gehörigen Spiel,
	 * gefüllten ResultSets
	 * 
	 * @param gameID
	 * @return ResultSet
	 */
	public ResultSet getSaetze(int gameID) throws Exception {

		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT DISTINCT * FROM Saetze WHERE Spiel = " + gameID;
			ResultSet res = stmt.executeQuery(sql);

			stmt.close();
			con.close();
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Methode zur Übergabe der Spaltenanzahl in der Tabelle Spiele
	 * 
	 * @return ColumnCount
	 */
	public int getColumnCountSpiele() throws Exception {
		int columnCount = 0;
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM Spiele";
			ResultSet res = stmt.executeQuery(sql);
			ResultSetMetaData resmd = res.getMetaData();
			columnCount = resmd.getColumnCount();
			// res.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columnCount;
	}

	/**
	 * Methode zur Übergabe der Spaltenanzahl in der Tabelle Saetze
	 * 
	 * @return ColumnCount
	 */
	public int getColumnCountSaetze() throws Exception {
		int columnCount = 0;
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM Saetze";
			ResultSet res = stmt.executeQuery(sql);
			ResultSetMetaData resmd = res.getMetaData();
			columnCount = resmd.getColumnCount();
			// res.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columnCount;
	}

	/**
	 * Methode zum Testen von gefüllten ResultSets
	 * 
	 * @param sql
	 * @return ResultSet
	 */
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

	/**
	 * Methode zum Auslesen eines Arrays aus der KI, welches mit allen Zügen
	 * eines Satzes gefüllt ist und in die Datenbank gespeichert werden sollen
	 * 
	 * @param temp
	 * @param game
	 * @param satz
	 */
	public void KIArrayAuslesen(int[][] temp, int game, int satz) throws Exception {
		System.out.println("Speichere Züge von Satz " + satz + " von Spiel " + game);
		int spieler = 0;
		int zeile = 0;
		int spalte = 0;
		PreparedStatement prep = null;
		Connection con = null;
		try {
			con = getConnection();

			for (int j = 0; j <= 5; j++) {

				for (int i = 0; i <= 6; i++) {
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

					spieler = temp[j][i];
					if (spieler == 1 || spieler == 2) {
						System.out.println("Spalte: " + i + " Zeile: " + j + " Spieler: " + spieler);
						spalte = i;
						zeile = j;

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
						}
					}
				}
			}
		} finally {
			try {
//				prep.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Methode zum Auslesen der Züge eines Satzes aus der Datenbank
	 * 
	 * @param satzID
	 * @return Array int [][]
	 */
	public int[][] getZuegeFromSatzID(int satzID) throws Exception {
		int[][] zuege = new int[6][7];
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sqlSelect = "SELECT * FROM Zuege WHERE SATZ = " + satzID;

			ResultSet res = stmt.executeQuery(sqlSelect);
			ResultSetMetaData resmd = res.getMetaData();
			int columnCount = resmd.getColumnCount();
			String[] row = new String[columnCount];

			while (res.next()) {
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = res.getString(i);
				}
				zuege[Integer.parseInt(row[2])][Integer.parseInt(row[3])] = Integer.parseInt(row[4]);
			}

			res.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zuege;
	}

	/**
	 * Testmethode zum Anzeigen eines Integer Arrays
	 * 
	 * @param array
	 */
	public void drawArray(int[][] array) {
		System.out.println("Derzeitiges Spielfeld");
		for (int m = 0; m <= 5; m++) {
			for (int n = 0; n <= 6; n++) {
				if (array != null) {
					System.out.print("|" + array[m][n]);
				}
			}
			System.out.println("");
		}
	}

	/**
	 * Testmethode zum Anzeigen eines String Arrays
	 * 
	 * @param array
	 */
	public void drawArray(String[][] array) {
		System.out.println("Derzeitiges Spielfeld");
		for (int m = 0; m <= 5; m++) {
			for (int n = 0; n <= 6; n++) {
				if (array != null) {
					System.out.print("|" + array[m][n]);
				}
			}
			System.out.println("");
		}
	}

	/**
	 * Methode zur Ausgabe der Anzahl der gespeicherten Spiele
	 * 
	 * @return Sum
	 */
	public int getSummeSpiele() throws Exception {
		// Count über Spieltabelle
		int sum = 0;
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sqlSelect = "SELECT COUNT(ID) FROM Spiele";

			ResultSet res = stmt.executeQuery(sqlSelect);
			ResultSetMetaData resmd = res.getMetaData();
			int columnCount = resmd.getColumnCount();
			String[] row = new String[columnCount];

			while (res.next()) {
				row[0] = res.getString(1);

				sum = Integer.parseInt(row[0]);
			}

			res.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}

	/**
	 * Methode zur Ausgabe Anzahl der gewonnenen Spiele
	 * 
	 * @return sum
	 */
	public int getSummeGewonneneSpiele() throws Exception {
		// sum über spieltabelle wo spieler = 1
		int sum = 0;
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sqlSelect = "SELECT COUNT(ID) FROM Spiele WHERE Sieger = 1";

			ResultSet res = stmt.executeQuery(sqlSelect);
			ResultSetMetaData resmd = res.getMetaData();
			int columnCount = resmd.getColumnCount();
			String[] row = new String[columnCount];

			while (res.next()) {
				row[0] = res.getString(1);

				sum = Integer.parseInt(row[0]);
			}

			res.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}

	/**
	 * Methode zum Errechnen und einer Win-Lose-Rate
	 * 
	 * @return Sum
	 */
	public String getSiegeNiederlageRate() throws Exception {
		float sum;
		 	
			int spiele = getSummeSpiele();
			int win = getSummeGewonneneSpiele();
			if(spiele == 0)
			{
				return "0";
			}
			else
			{
				sum = (win * 1f) / spiele;
			}
			NumberFormat numberFormat = new DecimalFormat("0.00");
		    numberFormat.setRoundingMode(RoundingMode.DOWN);
		    System.out.println(numberFormat.format(sum));
		return numberFormat.format(sum);
	}
	/**
	 * Methode zur Ausgabe der gesamten Anzahl der gespielten Spiele
	 * 
	 * @return Sum
	 * @throws Exception
	 */
	public int getGespielteSteine() throws Exception {
		int sum = 0;
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sqlSelect = "SELECT COUNT(ID) FROM Zuege";

			ResultSet res = stmt.executeQuery(sqlSelect);
			ResultSetMetaData resmd = res.getMetaData();
			int columnCount = resmd.getColumnCount();
			String[] row = new String[columnCount];

			while (res.next()) {
				row[0] = res.getString(1);

				sum = Integer.parseInt(row[0]);
			}

			res.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}

	/**
	 * Methode gibt den nächst freien Index in der Tabelle Spiele aus
	 * @return index
	 */
	public int getFreeSpielID() throws Exception {
		Connection con = getConnection();
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
			res.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return index;
	}

	/**
	 * Methode gibt den nächst freien Index in der Tabelle Saetze aus
	 * @return index
	 */
	public int getFreeSatzID() throws Exception {
		Connection con = getConnection();
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
			res.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return index;
	}

	/**
	 * Methode zum Löschen der Datenbank
	 */
	public void dropTable() throws Exception {
		try {
			Connection con = getConnection();

			Statement stmt = con.createStatement();
			String sql = "TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK";

			stmt.executeQuery(sql);
			stmt.executeUpdate("shutdown");

			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Database db = new Database();
//		db.selectSpiele();
//		db.dropTable();
//		db.selectSpiele();
	}
}