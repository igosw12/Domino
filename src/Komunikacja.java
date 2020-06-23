import files.Gracz;
import files.Gra;
import files.Kostki;

public class Komunikacja extends Thread {
	private static Klient server;
	private static Gracz gracz;
	private static Gra gra;
	private static Kostki kostki;
	private static String name;
	
	public Komunikacja(String a) {
    	name=a;
    }
	
	public void run(){
		String hostName;
		int portNumber = 4444;
		hostName = name;

		server = new Klient(hostName, portNumber);
		
		System.out.printf("Polaczono z serwerem: %s\n", server.address);
		
		try {
			gracz = new Gracz();
		} catch (Exception e) {
			e.printStackTrace();
		}

		listen();
	}
	
	private static void listen() {
		String decyzja;
		game:
		do {
			decyzja = server.listen();
			
			while(!gra.finished) {
				if(player.ilosc_kostek() == 0) {
					String winner = gracz;
					winner = server.listen();
					finished = true;
				}
				
				if(kostki.sprawdz_ilosc_kostek_do_dobierania()) == 0) {
					String winner = gracz;
					winner = server.listen();
					finished = true;
				}
			}
			
			if(decyzja.toUpperCase().equals("DOBIERZ")) {
				gui.pozostaleKostki.setText(Integer.toString(kostki_do_dobrania));
				revalidate();
				repaint();
				gui.wyczyscZestaw();
				gui.dodajZestaw();
			}
			
			//if(!decyzja.toUpperCase().equals("DOBIERZ")) {
			//}
		} while(true);	
	}
}