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
	int gegnerzug;
	KI k;

	public FileCom(String paths, String players, Double time) {
		System.out.println("Erfolgreich FileCommunicator erstellt");
		System.out.println("Du bist: " + players);
		System.out.println("Die Datei liegt da: " + paths);
		path = paths;
		player = players;
		k = new KI();
	}

	public int start() throws SAXException, IOException, InterruptedException {
		int res = 0;
		if (lesen() == 1) {
			Thread.sleep(500);
			start();
		} else {
			if (freigabe.equals("false")) 
			{
				System.out.println("Freigabe gesperrt");
				System.out.println("Sieger: " + sieger);
				char tempsieger = sieger.toLowerCase().charAt(8);
				char tempplayer = player.charAt(7);
				if (tempplayer == tempsieger) {
					System.out.println("YOU WIN!!!");
					res = 1;
				} else {
					System.out.println("YOU LOSE!!!");
					res = 2;
				}
			} 
			else {
				if (gegnerzug == -1) {
					agentenlöschen(); // Agentendateilöschen
					serverlöschen();
					schreiben(k.berechnen());
					Thread.sleep(500);
					start();
				} else {
					agentenlöschen(); // Agentendateilöschen
					// // spielen
					k.einlesen(gegnerzug, 2);
					serverlöschen();
					schreiben(k.berechnen());
					Thread.sleep(500);
					start();
				}

			}
		}
		System.out.println("Ergebnis: " + res);
		return res;
	}

	public void agentenlöschen() {
		file = new File(path + "/" + "\\" + player + "2server.txt");
		file.delete();
		System.out.println("Agentendatei von " + player + " wurde gelöscht: "
				+ file.getPath());
	}

	public void serverlöschen() {
		file = new File(path + "/" + "\\server2" + player + ".xml");
		file.delete();
		System.out.println("Serverdatei von " + player + " wurde gelöscht: "
				+ file.getPath());
	}

	public void schreiben(int zahl) throws IOException {
		file = new File(path + "/" + "\\" + player + "2server.txt");
		System.out.println("Neue Agentendatei wird erstellt: " + file.getPath()
				+ " Wert: " + zahl);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			writer = new FileWriter(file, true);
			writer.write(Integer.toString(zahl));
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
					String gegnerzug2 = content.getTextContent();
					gegnerzug = Integer.parseInt(gegnerzug2);
				}
				Node f = contentlist4.item(0);
				if (f.getNodeType() == Node.ELEMENT_NODE) {
					content = (Element) f;
					System.out.println("Sieger: " + content.getTextContent());
					sieger = content.getTextContent();
				}
				return 2;
			}
		}

		catch (ParserConfigurationException e) {
			return 1;
		}
		return 2;

	}

}