package win;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileCom {

	FileWriter writer;
	File file;
	String path;
	String player;
	String freigabe;
	String sieger;
	String satzstatus;
	String gegnerzug;

	public FileCom(String paths, String players, Double time) {
		System.out.println("Erfolgreich FileCommunicator erstellt");
		System.out.println("Du bist: " + players);
		System.out.println("Die Datei liegt da: " + paths);
		path = paths;
		player = players;
	}

	public void start() throws SAXException, IOException, InterruptedException {
		if (lesen() == 1) {
			System.out.println("keine Serverdatei gefunden - 2sec Wartezeit...");
			Thread.sleep(10000);
			start();
		} else {
			löschen();

			// KI k = new KI();
			// k.einlesen(spalte, player);
			// int neuespalte = k.intelligence();
			// schreiben(neueSpalte);
			// k.einlesen(neueSpalte, );
			// Serverdatei prüfen
			if (freigabe == "false") {
				System.out.println("Freigabe gesperrt");
				return;
			} else {

				// KI + Spielzug
				Random rnd = new Random();
				int zahl = rnd.nextInt(5) + 1;
				// spielen
				schreiben(Integer.toString(zahl));
				Thread.sleep(10000);
				start();
			}
		}
	}

	public void löschen() {
		file = new File(path + "/" + player + "2server.txt");
		file.delete();
		System.out.println("Datei wurde gelöscht: " + file.getPath());
	}

	public void schreiben(String zahl) throws IOException {
		file = new File(path + "/" + player + "2server.txt");
		System.out.println("Neue Agentendatei wird erstellt: " + file.getPath()
				+ " Spalte: " + zahl);
		file = new File(path + "/" + player + "2server.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (file.exists()) {
			file.delete();
			file = new File(path + "/" + player + "2server.txt");
			file.createNewFile();
		}
		try {
			writer = new FileWriter(file, true);
			writer.write(zahl);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int lesen() throws SAXException, IOException {
		String serverfilename = "\\server2" + player + ".xml";
		System.out.println("Die Datei soll gelesen werden: " + path + "/"
				+ serverfilename);

		// Überprüfen ob Datei vorhanden ist
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();

		documentBuilderFactory.setNamespaceAware(true);
		documentBuilderFactory.setValidating(false);
		DocumentBuilder documentBuilder;
		Element content;
		File file = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			boolean exist = new File(path + "/" + serverfilename).exists();
			System.out.println("Serverdatei suchen: " + path + "/"
					+ serverfilename);
			if (exist == false) {
				System.out.println("Serverdatei existiert nicht!!!");
				return 1;
			}
			if (exist == true) {
				System.out.println("Serverdatei wurde gefunden!");
				file = new File(path + "/" + serverfilename);
				Document doc = documentBuilder.parse(file);
				NodeList contentlist1 = doc.getElementsByTagName("freigabe");
				NodeList contentlist2 = doc.getElementsByTagName("satzstatus");
				NodeList contentlist3 = doc.getElementsByTagName("gegnerzug");
				NodeList contentlist4 = doc.getElementsByTagName("sieger");

				Node c = contentlist1.item(0);
				if (c.getNodeType() == Node.ELEMENT_NODE) {
					content = (Element) c;
					System.out.println("Freigabe: " + content.getTextContent());
					freigabe = content.getTextContent();
				}

				Node d = contentlist2.item(0);
				if (d.getNodeType() == Node.ELEMENT_NODE) {
					content = (Element) d;
					System.out.println("Satzstatus: "
							+ content.getTextContent());
					satzstatus = content.getTextContent();
				}

				Node e = contentlist3.item(0);
				if (e.getNodeType() == Node.ELEMENT_NODE) {
					content = (Element) e;
					System.out
							.println("Gegnerzug: " + content.getTextContent());
					gegnerzug = content.getTextContent();
				}
				Node f = contentlist4.item(0);
				if (f.getNodeType() == Node.ELEMENT_NODE) {
					content = (Element) f;
					System.out.println("Sieger: " + content.getTextContent());
					sieger = content.getTextContent();
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 2;

	}
}