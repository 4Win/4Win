
package win;
import java.util.Random;

import win.VierGewinnt.SpielSituation;

public class KI {
	
	int hoehe = 6;
	int breite = 7;
	
	// Feldbezeichnung
	static int leer = 0;
	int spieler = 1;
	int gegner = -1;
	// Feldbewertungen max 1000 pkt = Sieg oder min -1000 pkt verloren 
	int muster4Spieler = 1000;
	int muster4Gegner = -1000;
	
	int muster3spieler = 100;
	int muster3gegner = 500;	
	
	int muster2spieler = 10;
	int muster2gegner = 10;
			
	
	
	int[][] feld = new int[hoehe][breite];


	//KI Objekt kann einlesen aufrufen 
	//bekommt als Eingabe Spalte und Name des Spielers aus der Serverdatei 
	public static void main(String[] args) {

		KI ki = new KI();
		ki.befüllen();
		
		//ki.einlesen(ki.berechnen(), 1);
	
	}
	
	// Feld aufbauen und befüllen - Start	
	public void befüllen (){
		for (int y=0; y<hoehe; y++){
			for (int x=0; x<breite; x++){
				feld[y][x] = leer;
				System.out.println(feld[y][x]);
			}	
		}			
	}
	
	

	// Methode die Zeile aus Spalte errechnet 
	// geht Array durch und findet erste Zeile  die in Spalte noch nicht bef�llt ist 
	//schreibt die Spielernummer an der Stelle in den Array 
	public void einlesen(int spalte, int player) {
		int zeile = 0;
		for (int x = 5; x >= 0; x--) {
			if (feld[x][spalte] == 0) {
				zeile = x;
				feld[zeile][spalte] = spieler; // 2 ist Gegener 1 sind wir
				System.out.println(zeile);
				return;
			}
		}
	}
	
	// Hilfsfunktion zum Erkennen eines 2er-, 3er- oder 4er-Musters
	// dx und dy geben die Richtung an, in die gesucht wird
	// (ab der Position (x,y))
	private static int mustercheck(int [][] feld, int spieler, int x, int y, int dx, int dy) {
	  int num = 0;
	  // dx und dy, je nach richtung 0 oder 1 oder -1 
	  if (  ((feld[x][y]==spieler) || (feld[x][y]==leer))// Überprüfung auf Koordinate
	     && ((feld[x+1*dx][y+1*dy]== spieler) || (feld[x+1*dx][y+1*dy]==leer)) // Überprüfung auf nächster Koordinate = 2-Muster
	     && ((feld[x+2*dx][y+2*dy]== spieler) || (feld[x+2*dx][y+2*dy]==leer)) // Überprüfung auf nächster Koordinate = 3 Muster
	     && ((feld[x+3*dx][y+3*dy]== spieler) || (feld[x+3*dx][y+3*dy]==leer)))
	  { // überprüfung auf nächster Koordinate = 4 Gewinnt

	    // zaehle Anzahl von spieler belegter Felder
		  for (int i=0; i<4; i++){ // geht 4 felder durch 
	    	if (feld[x+i*dx][y+i*dy]==spieler) num++;// wenn feld belegt ist +1 bis max 4
		  }
	  }
	return num;// wenn max 4 automatisch 4 gewinnt
	} // Ende Hilfsfunktion
	
	
	public int bewerten(){ // bewertet übergebenes feld

		 int spieler2er = 0; int gegner2er = 0;
		 int spieler3er = 0; int gegner3er = 0;
		  
	// zuerst nur höhe überprüfen
		 for (int x=0; x<breite; x++) {
			    for (int y=0; y<hoehe; y++) {
			        // Ist noch Platz um 4 Chips nach oben zu setzen?
			        if (hoehe-y>=4) { // Ist platz für 4 nach oben ?
			        	if (mustercheck(feld,spieler,x,y,0,-1)==4){
			        		return muster4Spieler;
			        		}
			        		else if (mustercheck(feld,gegner,x,y,0,-1)==4){
				        		return muster4Gegner;
			        		}
			        		else if (mustercheck(feld,spieler,x,y,0,-1)==3){
			        			spieler3er ++;
			        		}
			        		else if (mustercheck(feld,gegner,x,y,0,-1)==3){
			        			gegner3er ++;
			        		}
			        		else if (mustercheck(feld,spieler,x,y,0,-1)==2){
			        			spieler2er ++;
			        		}
			        		else if (mustercheck(feld,gegner,x,y,0,-1)==2){
			        			gegner2er ++;
			        		}
			        		
			        	}
			        		
			        }
			     }
		 return muster3spieler*spieler3er + muster2spieler*spieler2er - muster3gegner*gegner3er + muster2gegner*gegner2er;
	}
	
		
	


	// Diese Methode berechnet die Entscheidung der KI 
	// geht Spalten und Zeilen durch und sucht dich erstes freies Feld 
	// schreibt die SpaltenNummer in Spalte und wirft diese zur�ck
	public int berechnen() {
		
		int spalte = 1; 
		
		for(int y=0; y<=6; y++)
		{
			for (int x = 5; x >= 0; x--) {
				
				if(feld[x][y] == 0)
				{
					 spalte = y; 
					 System.out.println(spalte);
					 return spalte;  
				}
			 ;}
			}
		 
		// Dann neue Zufallszahl 
		return 1;
		
		
	
		
		
		//1. Zug in die Mitte werfen 
		//Kann ich gewinnen?  
		// max. 7 Möglichkeiten 
		// 1. überprüfen 
		// 3 nach rechts 
		// 3 nach links 
		// 3 nach unten 
		// Wenn eine der Möglichkeiten da ist dann 1 in das Feld ansosnten 0 
		// Sind alle 7 bewertet wird das mit 1 ausgeführt 
		// Ansonsten Zufall
		// Gibts mehrere mit ner 1 auch Zufall 
		
	}
}
