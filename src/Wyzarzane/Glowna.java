package Wyzarzane;

import java.util.ArrayList;
import java.util.Random;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Glowna {
	private ArrayList<Proces> zbiorProcesow;
	private int biezacyPotrzebnyCzas = 0, wtw = 0;;
	
	Glowna(int ileProcesow) {
		zbiorProcesow = new ArrayList<Proces>();

		for (int j = 0; j < ileProcesow; j++){
			zbiorProcesow.add(new Proces());
		}
	}
	
	int oblicz(ArrayList<Proces> tablica) {
		//szuka ustawienia wg kryterium: minimalna wartość z TWT = SUMA(w*T)
		wtw = 0;
		
		for (Proces i : tablica) {
			System.out.println(i);
			biezacyPotrzebnyCzas += i.wymaganiaCzasowe;		//sumowanie
			System.out.println(biezacyPotrzebnyCzas + "  biezacy czas"); //@test
			if (biezacyPotrzebnyCzas < i.czasZakonczenia) {
				wtw += i.waga;
			}
			else {
				wtw += i.waga*(biezacyPotrzebnyCzas-i.czasZakonczenia);
			}
		}
		System.out.println("To jest wtw    " + wtw);
		biezacyPotrzebnyCzas = 0;
		return wtw; //wtw temteratura początkowa
	}
	
	void szukajOptimum() {
		int rozwiazanie = oblicz(zbiorProcesow), ktory = 0, zamienZ = 0;
		Random generator = new Random();
		ArrayList<Proces> kopia = zbiorProcesow;
		Proces tmp;
		
		while (rozwiazanie > zbiorProcesow.size()*50) {
			 ktory = generator.nextInt(zbiorProcesow.size());			//losuje dwa elementy które zamienie
			 zamienZ = generator.nextInt(zbiorProcesow.size());
			 
			 tmp = kopia.get(ktory);
			 kopia.set(ktory, kopia.get(zamienZ));
			 kopia.set(zamienZ, tmp);
			 
			 if (oblicz(kopia) <= rozwiazanie) {
				 zbiorProcesow = kopia;
				 for (Proces i : zbiorProcesow) {
					 System.out.println(" Jest  " + i);
				 }
			 }
		}
		//kopiuje tablice, losuje, usuwam wylosowany element z kopii tablicy
	}
	
	public static void main(String [] args) {
		
		Glowna program = new Glowna(30);
		program.szukajOptimum();
	}
}
