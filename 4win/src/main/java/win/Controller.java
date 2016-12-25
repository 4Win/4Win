package win;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.xml.sax.SAXException;

import database.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Controller {

	// Array mit 6 Zeilen und 7 Spalten
	// 0 = kein Eintrag
	// 1 = Wir haben einen Eintrag
	// 2 = gegner hat einen Eintrag
	int[][] temparray = new int[6][7];
	Stage stage;
	Pane root;
	String opponent;
	String path;
	String player;
	int counter;
	int os; //Betriebssystem: Windows(1); Linux(2)
	Double time;
	int[] saetze = new int[2];
	int game;
	ObservableList<ObservableList> data;
	ObservableList<ObservableList> data2;
	int loadcolumncount = 0;
	int loadcolumncount2 = 0;
	KI k = KI.getInstance();
	DataStorage ds = DataStorage.getInstance();
	int set = 1;

	@FXML
	Button refreshtalk;
	@FXML
	Text talk1;
	@FXML
	Text talk2;
	@FXML
	Label count1;
	@FXML
	Label count2;
	@FXML
	Label name2;
	@FXML
	Label numgame;
	@FXML
	Label numstone;
	@FXML
	Label numwin;
	@FXML
	Label winratio;
	@FXML
	Label w1;
	@FXML
	Label w2;
	@FXML
	Label w3;
	@FXML
	Label w4;
	@FXML
	Label w5;
	@FXML
	Label w6;
	@FXML
	Text rec2;
	@FXML
	Label warnlabel;
	@FXML
	Rectangle rec3;
	@FXML
	Button win1;
	@FXML
	Button win3;
	@FXML
	TableColumn loadtable;
	@FXML
	TableView tableview;
	@FXML
	TableView tableview2;
	@FXML
	Label res;
	@FXML
	RadioButton os1;
	@FXML
	RadioButton os2;

	// Label für Fussball Logos
	// Reihe 1
	@FXML
	Label pic00;
	@FXML
	Label pic01;
	@FXML
	Label pic02;
	@FXML
	Label pic03;
	@FXML
	Label pic04;
	@FXML
	Label pic05;
	@FXML
	Label pic06;
	// Reihe 2
	@FXML
	Label pic10;
	@FXML
	Label pic11;
	@FXML
	Label pic12;
	@FXML
	Label pic13;
	@FXML
	Label pic14;
	@FXML
	Label pic15;
	@FXML
	Label pic16;
	// Reihe 3
	@FXML
	Label pic20;
	@FXML
	Label pic21;
	@FXML
	Label pic22;
	@FXML
	Label pic23;
	@FXML
	Label pic24;
	@FXML
	Label pic25;
	@FXML
	Label pic26;
	// Reihe 4
	@FXML
	Label pic30;
	@FXML
	Label pic31;
	@FXML
	Label pic32;
	@FXML
	Label pic33;
	@FXML
	Label pic34;
	@FXML
	Label pic35;
	@FXML
	Label pic36;
	// Reihe 5
	@FXML
	Label pic40;
	@FXML
	Label pic41;
	@FXML
	Label pic42;
	@FXML
	Label pic43;
	@FXML
	Label pic44;
	@FXML
	Label pic45;
	@FXML
	Label pic46;
	// Reihe 6
	@FXML
	Label pic50;
	@FXML
	Label pic51;
	@FXML
	Label pic52;
	@FXML
	Label pic53;
	@FXML
	Label pic54;
	@FXML
	Label pic55;
	@FXML
	Label pic56;
	// Ende Label für Fussball Logos
	@FXML
	Label set1;
	@FXML
	Label playerlabel;
	@FXML
	Label oslabel;
	@FXML
	Label pathlabel;
	@FXML
	Label channellabel;
	@FXML
	Label eventlabel;
	@FXML
	TextField channelfield;
	@FXML
	TextField eventfield;
	@FXML
	Button file;
	@FXML
	RadioButton rb1;
	@FXML
	RadioButton rb2;
	@FXML
	RadioButton rb3;
	@FXML
	RadioButton rb4;
	@FXML
	TableColumn col1;
	@FXML
	TableColumn col2;
	@FXML
	TableColumn col3;
	@FXML
	TableColumn col4;
	@FXML
	Label pathlb;
	@FXML
	TextField tf1;
	@FXML
	TextField tf2;
	@FXML
	Pane p2;
	@FXML
	Button b2;
	@FXML
	Button b3;
	@FXML
	Button b4;
	@FXML
	Button b5;
	@FXML
	Button b6;
	@FXML
	Button b7;
	@FXML
	Button bstat;
	@FXML
	Button bfieldset;
	@FXML
	Button bloadback;
	@FXML
	Button win2;
	@FXML
	Button okgame;
	@FXML
	Slider sl1;
	@FXML
	RadioButton rbwin11;
	@FXML
	RadioButton rbwin12;
	@FXML
	RadioButton rbwin21;
	@FXML
	RadioButton rbwin22;
	@FXML
	RadioButton rbwin31;
	@FXML
	RadioButton rbwin32;
	@FXML
	Text spieltag;
	@FXML
	Text spielrun;
	@FXML
	TextArea tazuege1, tazuege2;

	/**
	 * Wechselt zum Screen um ein neues Spiel zu starten.
	 */
	@FXML
	protected void StartPop() {
		ds.clean();
		stage = (Stage) b2.getScene().getWindow();

		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource(
					"StartPopup.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene2 = new Scene(p2);
		stage.setScene(scene2);
		stage.show();
	}

	/**
	 * Wechselt zum Screen um Statistik aufzuzeigen.
	 * @throws Exception
	 */
	@FXML
	protected void StatPop() throws Exception {
		Database db = new Database();
		stage = (Stage) b2.getScene().getWindow();

		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource("StatPop.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene2 = new Scene(p2);
		stage.setScene(scene2);
		stage.show();
		int spieler = db.getSummeSpiele();
		int win = db.getSummeGewonneneSpiele();
		int stone = db.getGespielteSteine();
		String ratio = db.getSiegeNiederlageRate();
		Label dlabel1 = (Label) p2.lookup("#numgame");
		Label dlabel2 = (Label) p2.lookup("#numwin");
		Label dlabel3 = (Label) p2.lookup("#numstone");
		Label dlabel4 = (Label) p2.lookup("#winratio");
		dlabel1.setText(Integer.toString(spieler));
		dlabel2.setText(Integer.toString(win));
		dlabel3.setText(Integer.toString(stone));
		dlabel4.setText(ratio);
	}

	/**
	 * Wechselt zum Screen um ein Spiel/Satz zu laden.
	 * @throws SQLException
	 * @throws IOException
	 * @throws Exception
	 */
	@FXML
	protected void LoadPop() throws SQLException, IOException, Exception {
		stage = (Stage) b2.getScene().getWindow();

		try {
			p2 = (Pane) FXMLLoader.load(getClass()
					.getResource("LoadPopup.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene2 = new Scene(p2);
		stage.setScene(scene2);
		stage.show();
	}

	/**
	 * Wechselt zurück zum Einstiegsbildschirm
	 */
	@FXML
	protected void Back() {
		// Ausgehend von StartPopup.fxml
		stage = (Stage) b3.getScene().getWindow();

		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource(
					"javafxscene.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene2 = new Scene(p2);
		stage.setScene(scene2);
		stage.show();
	}

	/**
	 * Wechselt zurück zum Einstiegsbildschirm
	 */
	@FXML
	protected void Back2() {
		stage = (Stage) b5.getScene().getWindow();

		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource(
					"javafxscene.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene2 = new Scene(p2);
		stage.setScene(scene2);
		stage.show();
	}

	/**
	 * Wechselt zurück zum Einstiegsbildschirm
	 */
	@FXML
	protected void Back3() {
		stage = (Stage) b6.getScene().getWindow();

		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource(
					"javafxscene.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene2 = new Scene(p2);
		stage.setScene(scene2);
		stage.show();
	}

	/**
	 * Wechselt zurück zum Einstiegsbildschirm
	 */
	@FXML
	protected void Back4() {
		stage = (Stage) bstat.getScene().getWindow();

		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource(
					"javafxscene.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene2 = new Scene(p2);
		stage.setScene(scene2);
		stage.show();
	}

	/**
	 * Wechselt zurück zum Einstiegsbildschirm
	 */
	@FXML
	protected void Back5() {
		stage = (Stage) bloadback.getScene().getWindow();

		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource(
					"javafxscene.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene2 = new Scene(p2);
		stage.setScene(scene2);
		stage.show();
	}

	
	/**
	 * Öffnet das Spielfeld eines gespielten Satzes.
	 */
	@FXML
	protected void LoadGame() {
		stage = (Stage) b6.getScene().getWindow();

		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource("Field.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene2 = new Scene(p2);
		stage.setScene(scene2);
		stage.show();
	}

	@FXML
	protected void SetBall(int zeile, int spalte) {

		stage = (Stage) win1.getScene().getWindow();
		// System.out.println(stage);
		String labeltag = "#" + "pic" + zeile + spalte;
		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource("Field.fxml"));
			Label dlabel = (Label) p2.lookup(labeltag);
			// System.out.println(dlabel.getText());
//			Image img = new Image("./pictures/ball.png");
			Image img  = new Image(getClass().getResourceAsStream("/pictures/ball.png"));
			ImageView imv = new ImageView(img);
			dlabel.setGraphic(imv);
			dlabel.setText("halloooooo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			e.printStackTrace();
		}
		// Scene scene2 = new Scene(p2);
		// stage.setScene(scene2);
		stage.show();

	}

	/**
	 * Startet neuen Satz mit gegebenem Modus.
	 * Maximal 3 Sätze.
	 * @throws Exception
	 */
	@FXML
	protected void StartNewSet() throws Exception {
		k.clean();
		if (ds.getSet() == 1) {
			// Starte Satz 2
			set = set + 1;
			ds.setSet(set);
//			set1.setText(Integer.toString(ds.getSet()));
			System.out.println("Starte Satz 2");
	
			Start(ds.getMode(), ds.getSet());
		} else if (ds.getSet() == 2) {
			// Starte Satz 3
			ds.setSet((ds.getSet() + 1));
//			set1.setText("hallo" + Integer.toString(ds.getSet()));
			System.out.println("Starte Satz 3");
			Start(ds.getMode(), ds.getSet());
		}

	}

	/**
	 * Baut Spielfeld aus Array auf.
	 * @param array
	 * @throws InterruptedException
	 */
	@FXML
	public synchronized void buildFromArray(int[][] array)
			throws InterruptedException {
		for (int i = 0; i <= 5; i++) {
			for (int y = 0; y <= 6; y++) {
				String labeltag = "#" + "pic" + i + y;
				if (array[i][y] == 1) {
					Label dlabel2 = (Label) p2.lookup(labeltag);
					if (dlabel2.getGraphic() == null) {
//						Image img = new Image("./pictures/treal.png");
						Image img  = new Image(getClass().getResourceAsStream("/pictures/treal.png"));
						ImageView imv = new ImageView(img);
						dlabel2.setGraphic(imv);
					}

				}
				if (array[i][y] == 2) {
					Label dlabel3 = (Label) p2.lookup(labeltag);
					if (dlabel3.getGraphic() == null) {
//						Image img2 = new Image("./pictures/tbarca.png");
						Image img2  = new Image(getClass().getResourceAsStream("/pictures/tbarca.png"));
						ImageView imv2 = new ImageView(img2);
						dlabel3.setGraphic(imv2);
					}
				}
			}
		}

	}

	/**
	 * Setzt Tokens auf das Spielfeld.
	 * @param zeile
	 * @param spalte
	 * @param spieler
	 */
	@FXML
	public synchronized void setStone(int zeile, int spalte, int spieler) {
		Image img = null;
		String label1 = "#pic" + zeile + spalte;
		Label dlabel2 = (Label) p2.lookup(label1);
		switch (spieler) {
		case 1:
//			img = new Image("./pictures/real.png");
			img  = new Image(getClass().getResourceAsStream("/pictures/treal.png"));

			break;

		default:
//			img = new Image("./pictures/barca.png");
			img  = new Image(getClass().getResourceAsStream("/pictures/tbarca.png"));
			break;
		}

		ImageView imv = new ImageView(img);
		dlabel2.setGraphic(imv);
	}

	/**
	 * Überprüft die Startkonfiguration.
	 * @throws Exception
	 */
	@FXML
	protected void CheckStartPopup() throws Exception {
		k.clean();
		ds.setPlayer(tf1.getText());
		System.out.println(ds.getPlayer());
		ds.setAppkey(channelfield.getText());
		ds.setAppsecret(eventfield.getText());
		if (rb3.isSelected() == false && rb4.isSelected() == false
				&& rb1.isSelected() == false && rb2.isSelected() == false) {
			p2 = (Pane) FXMLLoader.load(getClass().getResource(
					"StartPopup.fxml"));
			rec3.setVisible(true);
			rec3.setDisable(false);
			warnlabel.setText("Bitte erst einen Spieler auswählen" + "\n\n"
					+ "Bitte erst einen Mode auswählen");
//			Image img = new Image("./pictures/warn.png");
			Image img  = new Image(getClass().getResourceAsStream("/pictures/warn.png"));
			ImageView imv = new ImageView(img);
			ImageView imv2 = new ImageView(img);
			w3.setVisible(true);
			w1.setVisible(true);
			w3.setGraphic(imv);
			w1.setGraphic(imv2);

		} else {
			if (rb1.isSelected() == false && rb2.isSelected() == false) {
				rec3.setVisible(true);
				rec3.setDisable(false);
				warnlabel.setText("Bitte erst einen Mode auswählen");
//				Image img = new Image("./pictures/warn.png");
				Image img  = new Image(getClass().getResourceAsStream("/pictures/warn.png"));
				ImageView imv = new ImageView(img);
				w1.setGraphic(imv);
				w3.setVisible(false);
			} else {
				if (rb3.isSelected() == false && rb4.isSelected() == false) {
					rec3.setVisible(true);
					rec3.setDisable(false);
					warnlabel.setText("Bitte erst einen Spieler auswählen");
//					Image img = new Image("./pictures/warn.png");
					Image img  = new Image(getClass().getResourceAsStream("/pictures/warn.png"));
					ImageView imv = new ImageView(img);
					w3.setGraphic(imv);
					w1.setVisible(false);
				} else {
					if (os1.isSelected() == false && os2.isSelected() == false) {
						if (rb2.isSelected()) {
							rec3.setVisible(true);
							rec3.setDisable(false);
							warnlabel
									.setText("Bitte erst ein Betriebssystem auswählen");
							w1.setVisible(false);
							w2.setVisible(false);
							w3.setVisible(false);
							w4.setVisible(false);
//							Image img = new Image("./pictures/warn.png");
							Image img  = new Image(getClass().getResourceAsStream("/pictures/warn.png"));
							ImageView imv = new ImageView(img);
							w5.setGraphic(imv);
						} else {
							Start(1, 1);
						}
					} else {
						if (rb1.isSelected()) {
							Start(1, 1);
						}
						if (rb2.isSelected()) {
							if (path == "" || path == null) {
								rec3.setVisible(true);
								rec3.setDisable(false);
								warnlabel
										.setText("Bitte erst ein Filepath wählen");
								w1.setVisible(false);
								w2.setVisible(false);
								w3.setVisible(false);
								w4.setVisible(false);
								w5.setVisible(false);
//								Image img = new Image("./pictures/warn.png");
								Image img  = new Image(getClass().getResourceAsStream("/pictures/warn.png"));
								ImageView imv = new ImageView(img);
								w6.setGraphic(imv);
								return;
							} else {
								Start(2, 1);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Startet einen neuen Satz.
	 * @param mode
	 * @param satz
	 * @throws Exception
	 */
	@FXML
	protected void Start(int mode, int satz) throws Exception {
		System.out.println("Starte Satz Nr. " + satz);
		// Mode 1 = Push
		// Mode 2 = File
		// Mode 3 = Load
		if (mode == 1 || mode == 2) {
			if (satz == 1) {
				// Erster Satz
				stage = (Stage) b3.getScene().getWindow();
				opponent = tf1.getText();
			} else {
				// Neuer Satz
				System.out.println("Starte neuen Satz!");
				stage = (Stage) set1.getScene().getWindow();
				// System.out.println(ds.getPath());
				// System.out.println(ds.getPlayer());
			}
		} else if (mode == 3) {
			stage = (Stage) b6.getScene().getWindow();
		}
		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource("Field.fxml"));
			Label dlabel = (Label) p2.lookup("#name2");
			if (dlabel != null) {
				dlabel.setText(opponent);
			}
			Label satzlabel = (Label) p2.lookup("#set1");
			if (satzlabel != null) {
				satzlabel.setText(Integer.toString(satz));
			}
			Label winnercount1 = (Label) p2.lookup("#count1");
			winnercount1.setText(Integer.toString(ds.getspielstand1()));
			Label winnercount2 = (Label) p2.lookup("#count2");
			winnercount2.setText(Integer.toString(ds.getspielstand2()));
			// ************* PUSHCOM Kommunikation ****************
			if (mode == 1) {
				System.out.println("Der Satz wird mit PUSHCOM gespielt");
				ds.setMode(mode);
				Scene scene2 = new Scene(p2);
				stage.setScene(scene2);
				stage.show();
				if (ds.getSet() == 3) {
					System.out.println("Setze neuen Button");
					Button nextset = (Button) p2.lookup("#win2");
					System.out.println(nextset.getText());
					nextset.setDisable(true);
					nextset.setVisible(false);
					Button savegame = (Button) p2.lookup("#win3");
					savegame.setVisible(true);
				}
				PushCom2 pc = new PushCom2(player);
				pc.setOnSucceeded(e -> {
					counter++;
					System.out.println("Spielpaar Nr: " + counter);
					Spielzug temp = pc.getValue();
					switch (temp.result) {
					case 0:
						if (temp.spieler2zeile == -1
								&& temp.spieler2spalte == -1) {
							setStone(temp.spieler1zeile, temp.spieler1spalte, 1);
							pc.restart();
							break;
						}
						// System.out.println("Normaler Satz");
						setStone(temp.spieler1zeile, temp.spieler1spalte, 1);
						TextArea ta = (TextArea)p2.lookup("#tazuege1");
						ta.appendText("Spalte: "+Integer.toString(temp.spieler1spalte));
						ta.appendText("\n");
						setStone(temp.spieler2zeile, temp.spieler2spalte, 2);
						TextArea ta2 = (TextArea)p2.lookup("#tazuege2");
						ta2.appendText("Spalte: "+Integer.toString(temp.spieler2spalte));
						ta2.appendText("\n");
						// drawArray(k.getArray());
						pc.restart();
						break;

					case 1:
						// //Spieler 1 (WIR) hat gewonnen
						try {
							database.Database db = new Database();
							// db.KIArrayAuslesen(k.getArray(), 1, ds.getSet());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("Spieler 1 gewinnt");
						Text t = (Text) p2.lookup("#spielrun");
						t.setVisible(false);
						Text t1 = (Text) p2.lookup("#spieltag");
						t1.setText("Spieler 1 hat gewonnen");
						int set = ds.getSet();
						if(set == 1)
						{
							ds.setWinner1(1);
						}
						if(set == 2)
						{
							ds.setWinner2(1);
						}
						if(set == 3)
						{
							ds.setWinner3(1);
						}
						// Label dlabel2 = (Label) p2.lookup("#res");
						// Image img = new Image("./pictures/cup.png");
						// ImageView imv = new ImageView(img);
						// dlabel2.setGraphic(imv);
						break;

					case 2:
						// Spieler 2 (GEGNER) hat gewonnen
						try {
							database.Database db = new Database();
							// db.KIArrayAuslesen(k.getArray(), 1, ds.getSet());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setStone(temp.spieler2zeile, temp.spieler2spalte, 2);
						System.out.println("Spieler 2 gewinnt");
						Text t3 = (Text) p2.lookup("#spielrun");
						t3.setVisible(false);
						Text t2 = (Text) p2.lookup("#spieltag");
						t2.setText("Spieler 2 hat gewonnen");
						int set2 = ds.getSet();
						if(set2 == 1)
						{
							ds.setWinner1(2);
						}
						if(set2 == 2)
						{
							ds.setWinner2(2);
						}
						if(set2 == 3)
						{
							ds.setWinner3(2);
						}
						break;
					}
				});
				pc.start();
			}

			if (mode == 2) {
				System.out.println("Der Satz wird mit FILECOM gespielt");
				ds.setMode(mode);
				Scene scene2 = new Scene(p2);
				stage.setScene(scene2);

				if (ds.getSet() == 3) {
					System.out.println("Setze neuen Button");
					Button nextset = (Button) p2.lookup("#win2");
					System.out.println(nextset.getText());
					nextset.setDisable(true);
					nextset.setVisible(false);
					Button savegame = (Button) p2.lookup("#win3");
					savegame.setVisible(true);
				}
				stage.show();
				FileCom2 fc = new FileCom2(ds.getPath(), ds.getPlayerselect(), time,
						ds.getOs());
				fc.setOnSucceeded(e -> {
					Spielzug temp = fc.getValue();
					switch (temp.result) {
					case 0:
						if (temp.spieler2zeile == -1
								&& temp.spieler2spalte == -1) {
							// System.out.println("Startzug");
							setStone(temp.spieler1zeile, temp.spieler1spalte, 1);
							fc.restart();
							break;
						}
						// System.out.println("Normaler Satz");
						TextArea ta = (TextArea)p2.lookup("#tazuege1");
						ta.appendText("Spalte: "+Integer.toString(temp.spieler1spalte));
						ta.appendText("\n");
						TextArea ta2 = (TextArea)p2.lookup("#tazuege2");
						ta2.appendText("Spalte: "+Integer.toString(temp.spieler2spalte));
						ta2.appendText("\n");
						setStone(temp.spieler1zeile, temp.spieler1spalte, 1);
						setStone(temp.spieler2zeile, temp.spieler2spalte, 2);
						// drawArray(k.getArray());
						fc.restart();
						break;

					case 1:
						// //Spieler 1 (WIR) hat gewonnen
						try {
							database.Database db = new Database();
							// db.KIArrayAuslesen(k.getArray(), 1, ds.getSet());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("Spieler 1 gewinnt");
						Text t1 = (Text) p2.lookup("#spielrun");
						t1.setVisible(false);
						Text t = (Text) p2.lookup("#spieltag");
						t.setText("Spieler 1 hat gewonnen");
						int set = ds.getSet();
						if(set == 1)
						{
							ds.setWinner1(1);
						}
						if(set == 2)
						{
							ds.setWinner2(1);
						}
						if(set == 3)
						{
							ds.setWinner3(1);
						}
						break;

					case 2:
						// Spieler 2 (GEGNER) hat gewonnen
						try {
							database.Database db = new Database();
							// db.KIArrayAuslesen(k.getArray(), 1, ds.getSet());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("Spieler 2 gewinnt");
						Text t3 = (Text) p2.lookup("#spielrun");
						t3.setVisible(false);
						Text t2 = (Text) p2.lookup("#spieltag");
						t2.setText("Spieler 2 hat gewonnen");
						int set2 = ds.getSet();
						if(set2 == 1)
						{
							ds.setWinner1(2);
						}
						if(set2 == 2)
						{
							ds.setWinner2(2);
						}
						if(set2 == 3)
						{
							ds.setWinner3(2);
						}

						break;
					}
				});
				fc.start();
			}
			if (mode == 3) {
				Scene scene2 = new Scene(p2);
				stage.setScene(scene2);
				stage.show();
				Button nextbutton = (Button) p2.lookup("#win2");
				Button savebutton = (Button) p2.lookup("#win3");
				nextbutton.setDisable(true);
				nextbutton.setVisible(false);
				savebutton.setDisable(true);
				savebutton.setVisible(false);
				database.Database db = new Database();
				buildFromArray(db.getZuegeFromSatzID(satz));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void HandleRadio() {
		if (rb1.isSelected()) {
			pathlb.setVisible(false);
			pathlb.setDisable(true);
			rec2.setVisible(false);
			pathlb.setText(null);
			channelfield.setVisible(true);
			channelfield.setDisable(false);
			channellabel.setVisible(true);
			channellabel.setDisable(false);
			eventfield.setVisible(true);
			eventfield.setDisable(false);
			eventlabel.setVisible(true);
			eventlabel.setDisable(false);
			file.setVisible(false);
			file.setDisable(true);
			pathlabel.setVisible(false);
			pathlabel.setDisable(true);
			oslabel.setVisible(false);
			oslabel.setDisable(true);
			os1.setVisible(false);
			os1.setDisable(true);
			os2.setVisible(false);
			os2.setDisable(true);
		}
		if (rb2.isSelected()) {
			pathlb.setVisible(true);
			pathlb.setDisable(false);
			rec2.setVisible(false);
			file.setVisible(true);
			file.setDisable(false);
			channelfield.setVisible(false);
			channelfield.setDisable(true);
			channellabel.setVisible(false);
			channellabel.setDisable(true);
			eventfield.setVisible(false);
			eventfield.setDisable(true);
			eventlabel.setVisible(false);
			eventlabel.setDisable(true);
			pathlabel.setVisible(true);
			pathlabel.setDisable(false);
			oslabel.setVisible(true);
			oslabel.setDisable(false);
			os1.setVisible(true);
			os1.setDisable(false);
			os2.setVisible(true);
			os2.setDisable(false);
		}

	}

	@FXML
	private void HandleRadioPlayer() {
		if (rb3.isSelected()) {
			ds.setPlayerselect("spielerx");
		}
		if (rb4.isSelected()) {
			ds.setPlayerselect("spielero");
		}
	}

	@FXML
	private void HandleRadioOS() {
		if (os1.isSelected()) {
			os = 1; // Windows
		}
		if (os2.isSelected()) {
			os = 2; // Linux
		}
		ds.setOs(os);
	}

	@FXML
	private void File() throws IOException {
		DirectoryChooser dc = new DirectoryChooser();
		dc.setTitle("Choose Folder");
		File file = dc.showDialog(null);
		if (file != null) {
			path = file.getPath();
			pathlb.setText(path);
			ds.setPath(path);
		}
	}

	@FXML
	private void SetProcess() {
		System.out.println("Set Process");
		tf2.setText(Integer.toString((int) sl1.getValue()));
	}

	@FXML
	private void LoadGames() throws SQLException, IOException, Exception {

		data = FXCollections.observableArrayList();
		database.Database db = new Database();
		ResultSet rs = db.getSpiele();
		int columnCount = db.getColumnCountSpiele();

		p2 = (Pane) FXMLLoader.load(getClass().getResource("LoadPopup.fxml"));

		if (loadcolumncount == 0) {
			for (int i = 0; i < columnCount; i++) {
				// We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData()
						.getColumnName(i + 1));

				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(
							CellDataFeatures<ObservableList, String> param) {
						return new SimpleStringProperty(param.getValue().get(j)
								.toString());
					}
				});
				tableview.getColumns().addAll(col);
				tableview
						.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
				// System.out.println("Column [" + i + "] ");
			}
			loadcolumncount++;
		} else
			System.out.println("Bitte warten");

		/********************************
		 * Data added to ObservableList *
		 ********************************/
		if (rs.first()) {
			ObservableList<String> row = FXCollections.observableArrayList();
			for (int i = 1; i <= columnCount; i++) {
				// Iterate Column
				row.add(rs.getString(i));
			}
			data.add(row);
			while (rs.next()) {
				// Iterate Row
				ObservableList<String> row2 = FXCollections
						.observableArrayList();
				for (int i = 1; i <= columnCount; i++) {
					// Iterate Column
					row2.add(rs.getString(i));
				}
				data.add(row2);
			}
			rs.close();
			// FINALLY ADDED TO TableView
			tableview.setItems(data);
		}
	}

	@FXML
	private void buildSelected() throws SQLException, IOException, Exception {
		if (tableview.getSelectionModel().getSelectedItem() != null) {
			ObservableList<String> item = FXCollections.observableArrayList();
			TableView.TableViewSelectionModel selectionModel = tableview
					.getSelectionModel();
			ObservableList selectedCells = selectionModel.getSelectedCells();
			TablePosition tablePosition = (TablePosition) selectedCells.get(0);
			item = (ObservableList<String>) tableview.getItems().get(
					tablePosition.getRow());
			int game = Integer.parseInt(item.get(0));
			LoadSets(game);
		} else
			System.out.println("Bitte Zeile auswählen");
	}

	@FXML
	private int getSelectedSetLoad() {
		if (tableview2.getSelectionModel().getSelectedItem() != null) {
			ObservableList<String> item = FXCollections.observableArrayList();
			TableView.TableViewSelectionModel selectionModel = tableview2
					.getSelectionModel();
			ObservableList selectedCells = selectionModel.getSelectedCells();
			TablePosition tablePosition = (TablePosition) selectedCells.get(0);
			item = (ObservableList<String>) tableview2.getItems().get(
					tablePosition.getRow());
			int set = Integer.parseInt(item.get(0));
			return set;
		} else
			System.out.println("Bitte Zeile auswählen");
		return 0;
	}

	
	@FXML
	private void refreshtalk()
	{
		Random rand = new Random();
		int randomNum = rand.nextInt((4 - 0) + 1);
		String[] talk = ds.getTalk(randomNum);
		System.out.println(randomNum);
//		Text t = (Text) p2.lookup("#talk1");
		talk1.setText(talk[2]);
		talk2.setText(talk[1]);
		
	}
	
	@FXML
	private void gameSave() {
		stage = (Stage) win2.getScene().getWindow();
		try {
			p2 = (Pane) FXMLLoader
					.load(getClass().getResource("SaveGame.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene2 = new Scene(p2);
		stage.setScene(scene2);
		stage.show();
		System.out.println("--------------");
		drawArray(ds.getSatz1());
		System.out.println("--------------");
		drawArray(ds.getSatz2());
		System.out.println("--------------");
		drawArray(ds.getSatz3());
		System.out.println("--------------");
		switch (ds.getWinner1()) {
		case 0:
			RadioButton r1 = (RadioButton) p2.lookup("#rbwin11");
			r1.setVisible(false);
			RadioButton r2 = (RadioButton) p2.lookup("#rbwin12");
			r2.setVisible(false);
			break;
		case 1:
			r1 = (RadioButton) p2.lookup("#rbwin11");
			r1.setSelected(true);
			break;

		case 2:
			r2 = (RadioButton) p2.lookup("#rbwin12");
			r2.setSelected(true);
			break;
		}
		switch (ds.getWinner2()) {
		case 0:
			RadioButton r1 = (RadioButton) p2.lookup("#rbwin21");
			r1.setVisible(false);
			RadioButton r2 = (RadioButton) p2.lookup("#rbwin22");
			r2.setVisible(false);
			break;
		case 1:
			r1 = (RadioButton) p2.lookup("#rbwin21");
			r1.setSelected(true);
			break;

		case 2:
			r2 = (RadioButton) p2.lookup("#rbwin22");
			r2.setSelected(true);
			break;
		}
		switch (ds.getWinner3()) {
		case 0:
			RadioButton r1 = (RadioButton) p2.lookup("#rbwin31");
			r1.setVisible(false);
			RadioButton r2 = (RadioButton) p2.lookup("#rbwin32");
			r2.setVisible(false);
			break;
		case 1:
			r1 = (RadioButton) p2.lookup("#rbwin31");
			r1.setSelected(true);
			break;

		case 2:
			r2 = (RadioButton) p2.lookup("#rbwin32");
			r2.setSelected(true);
			break;
		}
//		System.out.println(rbwin11.isSelected());
//		System.out.println(rbwin12.isSelected());
	}

	@FXML
	private void okgames() throws Exception {
		int sets = 0;
		if(ds.getWinner3() == 1 || ds.getWinner3() == 2)
		{
		sets = 3;	
		}
		else{
			if(ds.getWinner2() == 1 || ds.getWinner2() == 2)
			{
				sets = 2;
			}
			else
			{
				if(ds.getWinner1() == 1 || ds.getWinner1() == 2)
				{
					sets = 1;
				}
			}
		}
		System.out.println("Es wurden "+sets+" Sätze gespielt.");
		stage = (Stage) okgame.getScene().getWindow();
		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource(
					"javafxscene.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		switch (sets) {
		case 1:
			ds.setWinner(ds.getWinner1());
			break;

		case 2:
			ds.setWinner(ds.getWinner1());
			break;
			
		case 3:
			int w1 = 0;
			int w2 = 0;
			switch (ds.getWinner1()) {
			case 1:
				w1++;
				break;
			case 2:
				w2++;
				break;
			}
			switch (ds.getWinner2()) {
			case 1:
				w1++;
				break;
			case 2:
				w2++;
				break;
			}
			switch (ds.getWinner3()) {
			case 1:
				w1++;
				break;
			case 2:
				w2++;
				break;
			}
			if(w1 == 2 || w1 == 3)
			{
				System.out.println("Spieler 1 hat gewonnen");
				ds.setWinner(1);
			}
			if(w2 == 2 || w2 == 3)
			{
				System.out.println("Spieler 2 hat gewonnen");
				ds.setWinner(2);
			}
		
			break;
		}
		System.out.println("Spieler " + ds.getWinner() + " hat gewonnen.");
		Scene scene2 = new Scene(p2);
		stage.setScene(scene2);
		stage.show();
		Database db = new Database();
		int spielid = db.getFreeSpielID();
		int satzid = db.getFreeSatzID();
		//Lege neues Spiel in DB an
		System.out.println("Neues Spiel angelegt: #"+spielid);
		//Speichere 2/3 Sätze in DB
		System.out.println("Satz 1 (#"+satzid+") Gewinner: "+ ds.getWinner1());
		System.out.println("Satz 2 (#"+(satzid+1)+") Gewinner: "+ ds.getWinner2());
		System.out.println("Satz 3 (#"+(satzid+2)+") Gewinner: "+ ds.getWinner3());
		db.insertSpiele(ds.getPlayer(), ds.getWinner(), ds.getStartplayer());
		db.insertSaetze(1, spielid, ds.getWinner1());
		db.insertSaetze(2, spielid, ds.getWinner2());
		db.insertSaetze(3, spielid, ds.getWinner3());
		
		switch (sets) {
		case 1:
			db.KIArrayAuslesen(ds.getSatz1(), spielid, satzid);
			break;

		case 2:
			db.KIArrayAuslesen(ds.getSatz1(), spielid, satzid);
			db.KIArrayAuslesen(ds.getSatz2(), spielid, (satzid+1));
			break;
		case 3:
			db.KIArrayAuslesen(ds.getSatz1(), spielid, satzid);
			db.KIArrayAuslesen(ds.getSatz2(), spielid, (satzid+1));
			db.KIArrayAuslesen(ds.getSatz3(), spielid, (satzid+2));
			break;
		}
	
		
	}

	@FXML
	private void LoadSets(int game) throws SQLException, IOException,
			Exception {
		database.Database db = new Database();
		data2 = FXCollections.observableArrayList();

		db.selectSaetze();
		ResultSet rs = db.getSaetze(game);
		int columnCount = db.getColumnCountSaetze();

		p2 = (Pane) FXMLLoader.load(getClass().getResource("LoadPopup.fxml"));

		if (loadcolumncount2 == 0) {
			for (int i = 0; i < columnCount; i++) {
				// We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData()
						.getColumnName(i + 1));

				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(
							CellDataFeatures<ObservableList, String> param) {
						return new SimpleStringProperty(param.getValue().get(j)
								.toString());
					}
				});
				tableview2.getColumns().addAll(col);
				tableview2
						.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
				// System.out.println("Column [" + i + "] ");
			}
			loadcolumncount2++;
		} else
			System.out.println("Bitte warten");

		/********************************
		 * Data added to ObservableList *
		 ********************************/
		if (rs.first()) {
			ObservableList<String> row = FXCollections.observableArrayList();
			for (int i = 1; i <= columnCount; i++) {
				// Iterate Column
				row.add(rs.getString(i));
			}
			data2.add(row);
			while (rs.next()) {
				// Iterate Row
				ObservableList<String> row2 = FXCollections
						.observableArrayList();
				for (int i = 1; i <= columnCount; i++) {
					// Iterate Column
					row2.add(rs.getString(i));
				}
				data2.add(row2);
			}
			rs.close();
			// FINALLY ADDED TO TableView
			tableview2.setItems(data2);
		}
	}

	@FXML
	private void ShowPlayedSet() throws Exception {
		int set = getSelectedSetLoad();
		if (set == 0) {
			System.out.println("Bitte wähle einen Satz aus");
		} else {
			// Baue Spielfeld von Satz <<set>> auf
			Start(3, set);

		}
	}
	
	@FXML
	private void HandleRadioSet1()
	{
		if (rbwin11.isSelected()) 
		{
			System.out.println("Spieler 1 nun ausgewählt 1");
			ds.setWinner1(1);
		}
		else if(rbwin12.isSelected())
		{
			System.out.println("Spieler 2 nun ausgewählt für Satz 1");
			ds.setWinner1(2);
		}
	}
	
	@FXML
	private void HandleRadioSet2()
	{
		if (rbwin21.isSelected()) 
		{
			System.out.println("Spieler 1 nun ausgewählt 2");
			ds.setWinner2(1);
		}
		else if(rbwin22.isSelected())
		{
			System.out.println("Spieler 2 nun ausgewählt für Satz 2");
			ds.setWinner2(2);
		}
	}
	
	@FXML
	private void HandleRadioSet3()
	{
		if (rbwin31.isSelected()) 
		{
			System.out.println("Spieler 1 nun ausgewählt 3");
			ds.setWinner3(1);
		}
		else if(rbwin32.isSelected())
		{
			System.out.println("Spieler 2 nun ausgewählt 3");
			ds.setWinner3(2);
		}
	}

	@FXML
	private void drawArray(int[][] array) {
		for (int m = 0; m <= 5; m++) {
			for (int n = 0; n <= 6; n++) {
				if (array != null) {
					System.out.print("|" + array[m][n]);
				}
			}
			System.out.println("");
		}
	}

}
