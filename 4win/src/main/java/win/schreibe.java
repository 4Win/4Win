package win;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class schreibe {
  
  FileWriter writer;
  File file;
  
  public void schreiben(String zahl){
	file = new File("C://Users//Michael//Desktop//Spielordner//PlayerX.txt");
	if(!file.exists()){
		try {
				file.createNewFile();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("test");
		}
	}
	try {
		writer = new FileWriter(file ,true);
		writer.write(zahl);
		writer.flush();
		writer.close();
		
	} catch(IOException e) {
      e.printStackTrace();
    }
  }
}