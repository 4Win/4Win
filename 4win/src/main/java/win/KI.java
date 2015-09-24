import java.util.Random;

public class KI {

	int[][] feld = new int[6][7];
	private static final int MAXCONST = 1;
	private static final int MINCONST = -1;
	int HOEHE = 6;
	int BREITE = 7;

	// werden übergeben aus ServerCom
	public static void main(String[] args) {

		KICon ki = new KICon();
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
				System.out.println(zeile);
				return;
			}
		}

	}

	public int berechnen() {
		
		Random random = new Random(); 
		int zufall = random.nextInt(6); 
		einlesen(zufall,1); 
		return zufall; 
		
	}

}
