package win;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
	public FileCom(String path, String player) {
		System.out.println("Erfolgreich FileCommunicator erstellt");
		System.out.println("Du bist: " + player);
		System.out.println("Die Datei liegt da: " + path);
		path = path;
	}
	
	public void start() throws SAXException, IOException, InterruptedException
	{
		if(lesen(path) == 1)
		{
			System.out.println("keine Serverdatei gefunden - warten");
//			wait(1000);
		}
	}

	public void schreiben(String zahl, String path, String player) {
		file = new File(path + "/" + player + "2server.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("test");
			}
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

	public int lesen(String path) throws SAXException, IOException {
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
			boolean exist = new File(path + "/Serverfile.xml").exists();
			if (exist == false) {
				System.out.println("Serverdatei existiert nicht!!!");
				return 1;
			}
			if (exist == true) {
				file = new File(path + "/Serverfile.xml");
				System.out.println("File: " + file);
				Document doc = documentBuilder.parse(file);
				NodeList contentlist1 = doc.getElementsByTagName("freigabe");
				NodeList contentlist2 = doc.getElementsByTagName("satzstatus");
				NodeList contentlist3 = doc.getElementsByTagName("gegnerzug");
				NodeList contentlist4 = doc.getElementsByTagName("sieger");

				Node c = contentlist1.item(0);
				if (c.getNodeType() == Node.ELEMENT_NODE) {
					content = (Element) c;
					System.out.println(content.getTextContent());
				}

				Node d = contentlist2.item(0);
				if (d.getNodeType() == Node.ELEMENT_NODE) {
					content = (Element) d;
					System.out.println(content.getTextContent());
				}

				Node e = contentlist3.item(0);
				if (e.getNodeType() == Node.ELEMENT_NODE) {
					content = (Element) e;
					System.out.println(content.getTextContent());
				}
				Node f = contentlist4.item(0);
				if (f.getNodeType() == Node.ELEMENT_NODE) {
					content = (Element) f;
					System.out.println(content.getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 2;

	}
}