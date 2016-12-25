package win;

// Diese Datei lie�t das Serverfile aus 
// ( File dass vom Server kommt und Spielinformationen enth�lt) 
// Lie�t Daten aus XML aus und gibt sie an Methode weiter 
// Wenn in Seiger nicht Offen steht abbruch !! 




import java.io.IOException;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class java_dom_parser {

//	/*public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//	try {
//		
//		JFileChooser chooser = new JFileChooser();
//	    chooser.setCurrentDirectory(new java.io.File("."));
//	    chooser.setDialogTitle("choosertitle");
//	    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//	    chooser.setAcceptAllFileFilterUsed(false);
//	    
//	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//	    } else {
//	      System.out.println("No Selection ");
//	    }
//		
//		DocumentBuilder builder = factory.newDocumentBuilder();
// 		Document doc = builder.parse(chooser.getSelectedFile());
//		NodeList contentlist1 = doc.getElementsByTagName("freigabe");
//		NodeList contentlist2 = doc.getElementsByTagName("satzstatus");
//		NodeList contentlist3 = doc.getElementsByTagName("gegnerzug");
//		NodeList contentlist4 = doc.getElementsByTagName("sieger");
//		
//		Node c = contentlist1.item(0);
//		if(c.getNodeType() == Node.ELEMENT_NODE){
//			Element content = (Element) c; 
//			System.out.println(content.getTextContent());
//		}
//		
//		Node d = contentlist2.item(0);
//		if(d.getNodeType() == Node.ELEMENT_NODE){
//			Element content = (Element) d; 
//			System.out.println(content.getTextContent());
//		}
//		
//		Node e = contentlist3.item(0);
//		if(e.getNodeType() == Node.ELEMENT_NODE){
//			Element content = (Element) e; 
//			System.out.println(content.getTextContent());
//		}
//		Node f = contentlist4.item(0);
//		if(f.getNodeType() == Node.ELEMENT_NODE){
//			Element content = (Element) f; 
//			System.out.println(content.getTextContent());
//		}
//		
//	} catch (ParserConfigurationException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (SAXException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} 
//	
//	//KI 
//	// .
//	// .
//	// .
//	
//	
//	// �bergabe der Viriable mit Zahl f�r .txt
//	String zahl = "3";
//	//ERSTELLEN DER TEXT DATEI !!!! 
//	
//	schreibe textdatei = new schreibe();
//	
//	textdatei.schreiben(zahl); 
//	
//	}
//
//	
//}*/
}