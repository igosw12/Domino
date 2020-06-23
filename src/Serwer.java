import java.io.*;
import java.net.*;
import files.Gracz;

public class Serwer extends Thread {
	private static ServerSocket socket;
	private static Gracz gracz;
	static public String aString;
	Klienci = new ArrayList<Klient>();
	
	public Serwer(String a) {
		aString = a;
	}
	
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(4444)) {
        	socket = serverSocket;
            while (true) {
            	System.out.println("[Server] oczekiwanie na polaczenie");
            	dodajKlienta(przyjmijKlienta());
	        }
	    } catch (IOException e) {
            System.exit(-1);
        }
	}
	
	public boolean dodajKlienta(Klient k) {
		k.tell("Oczekiwanie na polaczenie");
		for (Klient klient : Klienci) {
			klient.tell("Pojawil sie nowy klient");
		}
		k.gracz = new Gracz();
		Klienci.add(k);
		System.out.printf("Dodano klienta");
		return true;
	}
	
    public static Klient przyjmijKlienta(){
    	Klient klient = null;
    	try{
    		klient = new Klient(socket.accept());
    		System.out.println("[Server] Klient przyjety");
    	} catch (IOException e) {
    		System.out.println("[Server] Blad przyjecia klienta");
    	}
    	return klient;
    }
}