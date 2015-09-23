package win;

import java.io.IOException;

import org.xml.sax.SAXException;

public class Test {
public static void main(String[] args) throws SAXException, IOException {
	FileCom fc = new FileCom("/Users/dwome/Desktop/einlesen/src", "PlayerX");
	fc.lesen("/Users/dwome/Desktop/einlesen/src");
}
}
