import java.util.ArrayList;
import java.util.Arrays;


public class Gracz {
    private ArrayList<String> zestaw_kostek = new ArrayList<String>();
    public String nick = "";
    private GUI gui;

    public Gracz(String nick, GUI gui){
        this.nick = nick;
        this.gui = gui;
    }

    public void nadaj_zestaw_kostek(ArrayList<String> k){
        zestaw_kostek = k;
    }

    public ArrayList<String> sprawdz_kostki(){
        return zestaw_kostek;
    }

    public int ilosc_kostek(){
        return zestaw_kostek.size();
    }

    public void dodaj_kostke(String kostka){
        zestaw_kostek.add(kostka);
    }

    public String getNick(){
        return nick;
    }

    public String pokaz_kostki(){
        return Arrays.toString(zestaw_kostek.toArray());
    }

    public void usun_kostke_z_zestawu(String kostka){
        zestaw_kostek.remove(zestaw_kostek.indexOf(kostka));
    }

    public void odswiez_zestaw(){
        gui.wyczyscZestaw();
        for(String item: zestaw_kostek){
            gui.dodajZestaw(item);
        }
    }
}

