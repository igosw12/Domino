import java.io.*;
import java.net.*;

public class Klient {

	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	public int good=1;

	public String address;
	public String hostName;
    public int portNumber;

    public Gracz gracz;

	public Klient(Socket s) {
		socket = s;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(),true);
			InetAddress addr = socket.getLocalAddress();
        	address = addr.getHostAddress();
        	System.out.printf("[Server] Adres klienta: %s\n", address);
		} catch (IOException e){
			System.out.println("[Server] Blad inicjalizacji klienta");
		}
	}
	
	public Klient(String hostName, int portNumber) {
		try {
			socket = new Socket(hostName, portNumber);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(),true);

			this.portNumber = portNumber;
			this.hostName = hostName;

			InetAddress addr = socket.getLocalAddress();
        	address = addr.getHostAddress();
        	System.out.printf("[Client] Adres serwera: %s\n", address);
       	} catch (UnknownHostException e) {
            System.err.println("[Client] Nieznany host: " + hostName);
            System.exit(1);
		} catch (IOException e) {
			System.out.println("[Client] Blad polaczenia");
		}
	}
	
	public String listen() {
		try {
			return reader.readLine();
		} catch (IOException e){
			System.out.println("Blad funkcji listen()");
			System.exit(-1);
			return null;
		}
	}
	
	public void tell(String message) {
		writer.println(message);	
	}
	
	public void disconnect() {
        writer.println(-2);
        try {
	        socket.close();
	        writer.close();
	        reader.close();
	        System.out.println("[Client] Rozlaczenie klienta");
    	} catch (IOException e){
    		System.out.println("[Client] Blad rozlaczenia klienta");
    	}
    }
    
    public String toString(){
    	return address;
    }
}