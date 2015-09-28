package win;
import java.util.Random;

public class KI {

	int[][] feld = new int[6][7];

	//KI Objekt kann einlesen aufrufen 
	//bekommt als Eingabe Spalte und Name des Spielers aus der Serverdatei 
	public static void main(String[] args) {

		KI ki = new KI();
		ki.einlesen(ki.berechnen(), 1);
	
	}

	// Methode die Zeile aus Spalte errechnet 
	// geht Array durch und findet erste Zeile  die in Spalte noch nicht bef�llt ist 
	//schreibt die Spielernummer an der Stelle in den Array 
	public void einlesen(int spalte, int player) {
		int zeile = 0;
		for (int x = 5; x >= 0; x--) {
			if (feld[x][spalte] == 0) {
				zeile = x;
				feld[zeile][spalte] = player; // 2 ist Gegener 1 sind wir
				System.out.println(zeile);
				return;
			}
		}
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
	}
}
