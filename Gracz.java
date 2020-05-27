import java.util.*;


public class Gracz {
	public String nick = "";
	private ArrayList<String> zestaw_kostek = new ArrayList<String>();
	public Gracz(String nick) {
		this.nick = nick;
	}
	
	public void nadaj_zestaw_kostek(ArrayList<String> k) {
		zestaw_kostek = k;
	}
	
	public void dodaj_kostke(String kostka) {
		zestaw_kostek.add(kostka);
	}
	
	public void usun_kostke_z_zestawu(String kostka) {
		zestaw_kostek.remove(zestaw_kostek.indexOf(kostka));
	}
	
	public ArrayList<String> sprawdz_kostki() {
		return zestaw_kostek;
	}
	
	public String pokaz_kostki() {
		return Arrays.toString(zestaw_kostek.toArray());
	}
	
	public int ilosc_kostek() {
		return zestaw_kostek.size();
	}
	
	public void odswiez_zestaw() {
	}
}
