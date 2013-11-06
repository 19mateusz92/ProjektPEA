package Wyzarzane;

import java.util.Random;


public class Proces {
	int waga /*w*/, czasZakonczenia/*d*/;	//T=max{0, C-d} C - termin zakończenia
	int wymaganiaCzasowe = 0/*p*/;
	Proces() {
		Random generator = new Random();
		waga = generator.nextInt(100)+1;
		wymaganiaCzasowe = generator.nextInt(100)+1;	 					//sekund
		czasZakonczenia = generator.nextInt(100)+1+wymaganiaCzasowe;//oczekiwany czas zakończenia sekund
	}
	
	public String toString() {
		return "Waga: " +waga+  "  Wymagania czasowe: " +wymaganiaCzasowe + "  Oczekiwany czas zakonczenia: "
				+ czasZakonczenia;
	}
}
