package win;

import java.util.Random;

public class KI {

	int[][] feld = new int[6][7];
	int HOEHE = 6;
	int BREITE = 7;

	// werden �bergeben aus ServerCom
	public static void main(String[] args) {

		KI ki = new KI();
		ki.einlesen(0, 1);
		ki.einlesen(0, 1);
		ki.einlesen(1, 1);
		System.out.println(ki.berechnen());
	}

	// Methode die Spalte und

	public void einlesen(int spalte, int player) {
		int zeile = 0;
		for (int x = 5; x >= 0; x--) {
			if (feld[x][spalte] == 0) {
				zeile = x;
				feld[zeile][spalte] = player; // 2 ist Gegener 1 sind wir
				System.out.println("Zeile: "+ zeile + " Spalte: "+ spalte + " Player: "+player);
				return;
			}
			System.out.println("weiter");
		}

	}

	public int berechnen() {
		
		Random random = new Random(); 
		int zufall = random.nextInt(6); 
		return zufall;
	}
	
	public void loadToDB() {
	}

}
