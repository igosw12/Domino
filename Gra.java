import java.util.*;

public class Gra {
    private Gracz gracz0;
    private Gracz gracz1;
    private Kostki kostki;

    private int aktu = 0;
    private String szereg = "";
    private boolean koniec = false;
    private boolean debug = false;
    private Gracz winner = null;


    public Gra(){


        gracz0 = new Gracz("Gracz 1");
        gracz1 = new Gracz("Gracz 2");

        kostki = new Kostki();

        kostki.wylosuj_zestawy(gracz0, gracz1);

        String poczatkowaKostka = kostki.wylosuj_kostke_poczatkowa();

        while (!koniec) {
            if (aktu == 0) {
                wykonaj_ruch(gracz0);
                aktu = 1;
            } else if (aktu == 1){
                wykonaj_ruch(gracz1);
                aktu = 0;
            }
            if(gracz0.ilosc_kostek() == 0) {
                System.out.println(gracz0.nick + " Wygral!!!");
                winner = gracz0;
                koniec = true;
            } else if (gracz1.ilosc_kostek() == 0) {
                System.out.println(gracz1.nick + " Wygral!!!");
                winner = gracz1;
                koniec = true;
            }
        }
    }

    public boolean wykonaj_ruch(Gracz gracz){

            System.out.println("###  " + gracz.nick + "   ###");
            System.out.println("Aktualny szereg kostek:");
            System.out.println(szereg);
            System.out.println("Kostki gracza:");
            System.out.println(gracz.pokaz_kostki());
            System.out.println("Wyrzuc kosttke badz tez dobierz ");

	return true;
    }

}
