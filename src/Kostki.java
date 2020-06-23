import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Kostki {
    private ArrayList<String> kostki = new ArrayList<String>();
    private ArrayList<String> kostki_do_dobierania = new ArrayList<String>();
    private Random rand = new Random();
    private GUI gui;

    public Kostki(GUI gui) {
        kostki.addAll(Arrays.asList("0-0", "0-1", "0-2", "0-3", "0-4", "0-5", "0-6", "1-1",
                "1-2", "1-3", "1-4", "1-5", "1-6", "2-2", "2-3", "2-4", "2-5", "2-6",
                "3-3", "3-4", "3-5", "3-6", "4-4", "4-5", "4-6", "5-5", "5-6", "6-6"));
        kostki_do_dobierania.addAll(kostki);
        this.gui = gui;
    }

    public int sprawdz_ilosc_kostek_do_dobierania(){
        return kostki_do_dobierania.size();
    }

    public void wylosuj_zestawy(Gracz gracz0, Gracz gracz1){
        ArrayList<String> zestaw_gracza1 = new ArrayList<String>();
        ArrayList<String> zestaw_gracza2 = new ArrayList<String>();

        for(int i = 0; i < 14; i++){
            int id = rand.nextInt(kostki_do_dobierania.size());
            String kostka = kostki_do_dobierania.get(id);
            kostki_do_dobierania.remove(id);
            if(i < 7){
                zestaw_gracza1.add(kostka);
            } else {
                zestaw_gracza2.add(kostka);
            }
        }

        gracz0.nadaj_zestaw_kostek(zestaw_gracza1);
        gracz1.nadaj_zestaw_kostek(zestaw_gracza2);
    }

    public void dobierz_kostke(Gracz gracz){
        int id = rand.nextInt(kostki_do_dobierania.size());
        String kostka = kostki_do_dobierania.get(id);
        kostki_do_dobierania.remove(id);
        gracz.dodaj_kostke(kostka);
    }

    public String wylosuj_kostke_poczatkowa(){
        int id = rand.nextInt(kostki_do_dobierania.size());
        String kostka = kostki_do_dobierania.get(id);
        kostki_do_dobierania.remove(id);
        return kostka;
    }
}
