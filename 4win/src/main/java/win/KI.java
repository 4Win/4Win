package win;
import java.util.Random;
import database.Database;
import java.util.Random;

import database.Database;

public class KI {
	static int leer = 0;
	int hoehe = 6;
	int breite = 7;
	
	// Feldbezeichnung
	int spieler = 1;
	int gegner = 2;
	// Feldbewertungen max 1000 pkt = Sieg oder min -1000 pkt verloren 
	int muster4Spieler = 1000;
	int muster4Gegner = 1000;
	
	int muster3spieler = 100;
	int muster3gegner = 500;	
	
	int muster2spieler = 10;
	int muster2gegner = 20;

	int freieFelder [] = new int [7];
	int bewertungenFelder[] = new int [7];

	int[][] feld = new int[6][7];
	
	private static KI instance = null;

	private KI() {
		// Exists only to defeat instantiation.
	}

	public static KI getInstance() {
		if (instance == null) {
			System.out.println("KI - erfolgreich erzeugt!");
			instance = new KI();
		}
		return instance;
	}
	
	public void clean()
	{
		feld = new int[6][7];
	}

	// Getter / Setter Methoden
	public int[][] getFeld() {
		return this.feld;
	}

	// Feld aufbauen und befüllen - Start
	public void befüllen() {
		synchronized (feld) {
			for (int y = 0; y < hoehe; y++) {
				for (int x = 0; x < breite; x++) {
					feld[y][x] = leer;
					System.out.println(feld[y][x]);
				}
			}
		}
	}

	/**
	 * Methode zum einpflegen des Gegner Zug
	 * @param spalte
	 * @param spieler
	 * @return zeile 
	 * @throws Exception
	 */
	public int zugeinlesen(int spalte, int spieler) throws Exception {
		int zeile = 0;
		if(spalte == -1)
		{
			zeile = 5;
			feld[5][3] = 1;
		}
		else
		{
		for (int x = 5; x >= 0; x--) {
			if (feld[x][spalte] == 0) {
				zeile = x;
				feld[zeile][spalte] = spieler;
					break; 
			}
		}
		}
		
		return zeile; 
		
	}


	public void drawArray(int[][] array) {
		synchronized (feld) {
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
	}

	public int[][] getArray() {
		synchronized (feld) {
			return feld;
		}
	}


	

	/**
	 * Hilfsmethode zur Bewertung eines Zuges
	 * @param player Spieler welcher bewertet werden muss
	 * @param h		 Zeilen-Position die bewertet werden muss
	 * @param b		 Spalten-Postition die bewertet werden muss
	 * @param dh	 Erkennt Bewertungsrichtung - Verschiebung in Zeilenrichtung
	 * @param db	 Erkennt Bewertungsrichtung - Verschiebung in Spaltenrichtung
	 * @return num	- Gibt eine Variable num zurück, welche für die Enhaltenen Muster steht
	 */
	private int mustercheck( int player, int h, int b, int dh, int db) {
	  int num = 0;
	  
	  if (dh == 0 && db == 1){ // Waagerecht
		  for (int i=-3; i<4; i++){
			  try{				  
				 if ((this.feld[h-i*dh][b+i*db]== player) || (this.feld[h-i*dh][b+i*db]==0)){
					 if (feld[h-i*dh][b+i*db]== player) {
						 num++;// wenn feld belegt ist +1 bis max 4
					 }
					 }
				 else if(i<0){}
				 else
					 break;
				 }
			  catch (ArrayIndexOutOfBoundsException e){}
			 {
				  if (num >= 3){
					 num = 3;
				 }
	  }
			 }
		  }
	  else if (dh == -1 && db == 0){ // Vertikal
		  for (int i= 0; i<4; i++){
			  try{				  
				 if ((this.feld[h-i*dh][b+i*db]== player) || (this.feld[h-i*dh][b+i*db]==0)){
					 if (feld[h-i*dh][b+i*db]== player) {
						 num++;// wenn feld belegt ist +1 bis max 4
					 }
					 }
				 else
					 break;
				 }
			  catch (ArrayIndexOutOfBoundsException e){}
			 {
	  }
			 }
		  if (h == 0 && num == 2){ // falls nicht genug Platz für 4-Muster
			  num = 0;
		  }
			  
		  }
	  else if (dh == 1 && db == 1){ // Diagonal rechts
		  for (int i= -3; i<4; i++){
			  try{				  
				 if ((this.feld[h-i*dh][b+i*db]== player) || (this.feld[h-i*dh][b+i*db]==0)){
					 if (feld[h-i*dh][b+i*db]== player) {
						 num++;// wenn feld belegt ist +1 bis max 4
					 }
					 }
				 else if(i<0){}
				 else
					 break;
				 }
			  catch (ArrayIndexOutOfBoundsException e){}
			  if (num >= 3){
					 num = 3;
				 }
			 {
	  }
			 }
		  }
	  else if (dh == 1 && db == -1){ // Diagonal links
		  for (int i= -3; i<4; i++){
			  try{				  
				 if ((this.feld[h-i*dh][b+i*db]== player) || (this.feld[h-i*dh][b+i*db]==0)){
					 if (feld[h-i*dh][b+i*db]== player) {
						 num++;// wenn feld belegt ist +1 bis max 4
					 }
					 }
				 else if(i<0){}
				 else
					 break;
				 }
			  catch (ArrayIndexOutOfBoundsException e){}
			  if (num >= 3){
					 num = 3;
				 }
			 {
	  }
			 }
		  }
	  return num;
	  }

	
		/**
		 * Hilfmethode zur Berechnung der Bewertung
		 * @param b - Spalte des zu berechnenden Feldes
		 * @param h - Zeile des zu berechnenden Feldes
		 * @return bewertung 
		 */
		public int bewertenFeld( int b, int h){ // bewertet übergebenes feld
			
			 int spieler2er = 0; int gegner2er = 0;
			 int spieler3er = 0; int gegner3er = 0;
			 
				        	if (h>= 0) { // Ist platz für 4 nach unten ?
				        		 if (mustercheck(spieler,h,b,-1,0)==3)
				        			spieler3er ++;
				        		else if (mustercheck(gegner,h,b,-1,0)==3)	
				        			gegner3er ++;
				        		else if (mustercheck(spieler,h,b,-1,0)==2)
				        			spieler2er ++;
				        		else if (mustercheck(gegner,h,b,-1,0)==2)
				        			gegner2er ++;				        	      
				        	}
				        	// Noch 4 Chips nach rechts moeglich?
				        	if (breite>=4) {
				        		// 3 gleiche Chips uebereinander?
				        		if (mustercheck(spieler,h,b,0,1)==3) 
				        			spieler3er++; 
				        		else if (mustercheck(gegner,h,b,0,1)==3){
				        			gegner3er++;
				        		 System.out.println("3-er gefunden");}
				        		// 2 gleiche Chips uebereinander?
				        		else if (mustercheck(spieler,h,b,0,1)==2) 
				        			spieler2er++; 
				        		else if (mustercheck(gegner,h,b,0,1)==2){
				        			gegner2er++;
				        		 System.out.println("2-er gefunden");}
				        	}
				        	
				            // Noch 4 Chips nach links oben moeglich?
				            if ((h !=0 ) && (b>=4)) {
				              // 3 gleiche Chips uebereinander?
				               if (mustercheck(spieler,h,b,1,-1)==3) 
				                spieler3er++; 
				              else if (mustercheck(gegner,h,b,1,-1)==3)
				                gegner3er++;
				              // 2 gleiche Chips uebereinander?
				              else if (mustercheck(spieler,h,b,1,-1)==2) 
				                spieler2er++; 
				              else if (mustercheck(gegner,h,b,1,-1)==2)
				                gegner2er++;
				            } 
				            // Noch 4 Chips nach rechts oben moeglich?
				            if ((h != 0)) {
				              // 3 gleiche Chips uebereinander?
				               if (mustercheck(spieler,h,b,1,1)==3) 
				                spieler3er++; 
				              else if (mustercheck(gegner,h,b,1,1)==3)
				                gegner3er++;
				              // 2 gleiche Chips uebereinander?
				              else if (mustercheck(spieler,h,b,1,1)==2) 
				                spieler2er++; 
				              else if (mustercheck(gegner,h,b,1,1)==2)
				                gegner2er++;
				            } 
				            return muster3spieler*spieler3er + muster2spieler*spieler2er + muster3gegner*gegner3er + muster2gegner*gegner2er;
				        					 
		}
	
/**
 * Diese Methode berechnet die Entscheidung der KI
 * @return ausgabeSpalte
 */
public int berechnen(){
		
		int[] volleSpalten= new int[6]; 
		int bespielbares_feld;
		int ausgabeSpalte;
		
	// Bespielbare Felder finden
		for (int b=0; b<this.breite; b++){
				bespielbares_feld = leereZeile(b);
				freieFelder[b]  = bespielbares_feld;
				
				if (bespielbares_feld == -1){
					volleSpalten [b] = -1;
				}		
		}
		// Berechnet Bewertungen
		for (int i=0; i<6; i++){
			
			if (volleSpalten[i] == -1){ // falls Spalte voll Bewertung auf -1
				bewertungenFelder[i] = -1;
			}
			else{
			bewertungenFelder[i] = bewertenFeld( i, freieFelder[i]);
			}
		}
		
    	ausgabeSpalte = 3; 
    	System.out.println(ausgabeSpalte);
    	
    	// Sucht höchste Bewertung
		for(int i=0; i<=6;i++){
			
			if(bewertungenFelder[ausgabeSpalte]<bewertungenFelder[i]){ 
				ausgabeSpalte = i; 	
			}
		}
		
		if (bewertungenFelder[ausgabeSpalte] == -1){ // falls alle Spalte voll
				System.out.println("Alle Spalten sind voll");
				return 666; 
			}
		else{ // Gibt ausgabeSpalte zurück
			for(int i=0; i<=6; i++){
			System.out.print(bewertungenFelder[i]+"/");
			}
		System.out.println(ausgabeSpalte);
		return ausgabeSpalte;
		}
	}

	/**
	 * Sucht bespielbare Zeile in Spalte
	 * @param spalte
	 * @return freie_zeile
	 */
	public int leereZeile(int spalte){ // Hilfmethode findet leere Felder
		int freie_zeile;
		for(int i = 5; i>=0; i--){
			if ( feld [i][spalte] == 0){
				freie_zeile = i;
						return freie_zeile;
			}
			
		}
		return -1;
	}
}