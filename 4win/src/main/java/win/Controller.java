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
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.application.Application;
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

	int[][] a = new int[6][7];
	Stage stage;
	Pane root;
	String opponent;
	String path;
	String player;
	Double time;
	int satz;
	int game;
	ObservableList<ObservableList> data;

	@FXML
	Label name2;

	@FXML
	Button win1;

	@FXML
	TableColumn loadtable;

	@FXML
	TableView tableview;

	@FXML
	Label res;

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

	@FXML
	Label pic17;

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
	Slider sl1;

	@FXML
	protected void StartPop() {
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

	@FXML
	protected void LoadPop() {
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

	@FXML
	protected void Back() {
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
		System.out.println(stage);
		String labeltag = "#" + "pic" + zeile + spalte;
		System.out.println(labeltag);
		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource("Field.fxml"));
			Label dlabel = (Label) p2.lookup(labeltag);
			System.out.println(dlabel.getText());
			Image img = new Image("./pictures/ball.png");
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

	@FXML
	protected void win1action() {

		// FileCom fc = new FileCom(path, player)

	}

	@FXML
	protected void win2action() {
		SetBall(1, 7);
	}

	@FXML
	public void buildFromArray(int[][] array) {
		for (int i = 0; i <= 5; i++) {
			for (int y = 0; y <= 6; y++) {
				String labeltag = "#" + "pic" + i + y;
				if (array[i][y] == 1) {
					System.out.println("Spieler X: " + labeltag);
					Label dlabel2 = (Label) p2.lookup(labeltag);
					Image img = new Image("./pictures/ball.png");
					ImageView imv = new ImageView(img);
					dlabel2.setGraphic(imv);
				}
				if (array[i][y] == 2) {

					System.out.println("Spieler Y: " + labeltag);
					Label dlabel2 = (Label) p2.lookup(labeltag);
					Image img = new Image("./pictures/ball2.png");
					ImageView imv = new ImageView(img);
					dlabel2.setGraphic(imv);
				}
			}
		}
	}

	@FXML
	protected void CheckStartPopup() throws SAXException, InterruptedException {
		if (rb3.isSelected() == false && rb4.isSelected() == false) {
			System.out.println("Bitte erst einen Spieler auswählen");
		} else {
			if (rb1.isSelected() == false && rb2.isSelected() == false) {
				System.out.println("Bitte erst einen Mode auswählen");
			} else {
				if (rb1.isSelected()) {
					Start(1);
				}
				if (rb2.isSelected()) {
					if (path == "" || path == null) {
						System.out.println("Bitte erst ein Filepath wählen");
						return;
					} else {
						Start(2);
					}
				}
			}
		}
	}

	@FXML
	protected void Start(int mode) throws SAXException, InterruptedException {
		// Mode 1 = Push
		// Mode 2 = File
		// Mode 3 = Load
		a[1][1] = 1;
		a[1][6] = 2;
		stage = (Stage) b3.getScene().getWindow();
		opponent = tf1.getText();

		try {
			p2 = (Pane) FXMLLoader.load(getClass().getResource("Field.fxml"));
			Label dlabel = (Label) p2.lookup("#name2");
			if (dlabel != null) {
				dlabel.setText(opponent);
			}
			if (mode == 1) {
				Scene scene2 = new Scene(p2);
				stage.setScene(scene2);
				stage.show();
				PushCom pc = new PushCom(player);
			}
			if (mode == 2) {
				Scene scene2 = new Scene(p2);
				stage.setScene(scene2);
				stage.show();
				FileCom fc = new FileCom(path, player, time);
				int erg = fc.start2();
				switch (erg) {
				case 1:
					Label dlabel2 = (Label) p2.lookup("#res");
					Image img = new Image("./pictures/cup.png");
					ImageView imv = new ImageView(img);
					dlabel2.setGraphic(imv);
					break;

				case 2:
					Label dlabel3 = (Label) p2.lookup("#res");
					Image img2 = new Image("./pictures/goat3.png");
					ImageView imv2 = new ImageView(img2);
					dlabel3.setGraphic(imv2);
					break;

				case 0:
					System.out.println("RandomCase");
					break;
				}
			}
			if (mode == 3) {
				buildFromArray(a);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	protected void HandleRadio() {
		if (rb1.isSelected()) {
			file.setDisable(true);
			pathlb.setText(null);
		}
		if (rb2.isSelected()) {
			file.setDisable(false);
		}

	}

	@FXML
	protected void HandleRadioPlayer() {
		if (rb3.isSelected()) {
			player = "spielerx";
		}
		if (rb4.isSelected()) {
			player = "spielero";
		}
	}

	@FXML
	protected void File() throws IOException {
		DirectoryChooser dc = new DirectoryChooser();
		dc.setTitle("Choose Folder");
		File file = dc.showDialog(null);
		path = file.getPath();
		pathlb.setText(path);
	}

	@FXML
	protected void SetProcess() {
		System.out.println("Set Process");
		tf2.setText(Integer.toString((int) sl1.getValue()));
	}

	@FXML
	protected void LoadGames() throws SQLException, IOException {
		System.out.println("Load Games");
//		database.Database db = new Database();
//
//		ResultSet rs = db.getSpiele();
//		// System.out.println(rs);
//		for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//			// We are using non property style for making dynamic table
//			final int j = i;
//			TableColumn col = new TableColumn(rs.getMetaData().getColumnName(
//					i + 1));
//			col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//				public ObservableValue<String> call(
//						CellDataFeatures<ObservableList, String> param) {
//					return new SimpleStringProperty(param.getValue().get(j)
//							.toString());
//				}
//			});
//			p2 = (Pane) FXMLLoader.load(getClass()
//					.getResource("LoadPopup.fxml"));
//			TableView tableview = (TableView) p2.lookup("#tableview");
//			tableview.getColumns().addAll(col);
//			System.out.println("Column [" + i + "] ");
//			System.out.println(tableview.getColumns().get(0));
//		}
//
//		/********************************
//		 * Data added to ObservableList *
//		 ********************************/
//		while (rs.next()) {
//			// Iterate Row
//			ObservableList<String> row = FXCollections.observableArrayList();
//			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//				// Iterate Column
//				row.add(rs.getString(i));
//			}
//			System.out.println("Row [1] added " + row);
//			data.add(row);
//
//		}
//
//		// FINALLY ADDED TO TableView
//		tableview.setItems(data);

	}

}
