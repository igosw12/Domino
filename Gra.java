import javax.swing.*;

public class Gra {
    private Gracz gracz0;
    private Gracz gracz1;
    private Kostki kostki;

    private int aktu = 0;
    private String szereg = "";
    private boolean koniec = false;
    private boolean debug = false;
    private Gracz zwyciezca = null;

    private GUI gui;

    public Gra(GUI gui){

        this.gui = gui;

        gracz0 = new Gracz("Gracz 1", this.gui);
        gracz1 = new Gracz("Gracz 2", this.gui);

        kostki = new Kostki(this.gui);

        kostki.wylosuj_zestawy(gracz0, gracz1);

        String poczatkowaKostka = kostki.wylosuj_kostke_poczatkowa();

        szereg = poczatkowaKostka;
        this.gui.polozDomino(poczatkowaKostka, "f", false);

        gui.ustawKostkiPozostale(kostki.sprawdz_ilosc_kostek_do_dobierania());

        while (!koniec) {
            if (aktu == 0) {
                ruch(gracz0);
                aktu = 1;
            } else if (aktu == 1){
                ruch(gracz1);
                aktu = 0;
            }
            if(gracz0.ilosc_kostek() == 0) {
                zwyciezca = gracz0;
                koniec = true;
            } else if (gracz1.ilosc_kostek() == 0) {
                zwyciezca = gracz1;
                koniec = true;
            }
        }
        gui.revalidate();
        gui.repaint();
        JOptionPane.showMessageDialog(gui, "Zwyciezca zostal: " + zwyciezca.nick, "Gra skonczona", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public boolean ruch(Gracz gracz){
        gui.ustawAktualnegoGracza(gracz.nick);
            System.out.println(gracz.nick);
            System.out.println("Szereg kostek:");
            System.out.println(szereg);
            System.out.println("Kostki gracza:");
            System.out.println(gracz.pokaz_kostki());
            System.out.println("Wybierz kostke lub kliknij dobierz");

        if (wykonajAkcje(gracz)){

        }
        return true;
    }

    public boolean wykonajAkcje(Gracz gracz) {
            gracz.odswiez_zestaw();
            String decyzja = gui.zagraj();
            if (decyzja.toUpperCase().equals("DOBIERZ")) {
                return dobieranie(gracz);
            } else {
                if (gracz.sprawdz_kostki().contains(decyzja)) {
                    if(!sprawdzDecyzje(gracz, decyzja)) {
                        return wykonajAkcje(gracz);
                    } else {
                        gracz.odswiez_zestaw();
                        return true;
                    }
                } else {
                    return wykonajAkcje(gracz);
                }
            }
        }


    public boolean sprawdzDecyzje(Gracz gracz, String decyzja){
        if (szereg.endsWith(String.valueOf(decyzja.charAt(0)))) {
            szereg = szereg + " | " + decyzja;
            gracz.usun_kostke_z_zestawu(decyzja);
            gui.polozDomino(decyzja, "r", false);
            return true;
        } else if (szereg.endsWith(String.valueOf(decyzja.charAt(2)))) {
            szereg = szereg + " | " + new StringBuilder(decyzja).reverse().toString();
            gracz.usun_kostke_z_zestawu(decyzja);
            gui.polozDomino(decyzja, "r", true);
            return true;
        } else if (szereg.startsWith(String.valueOf(decyzja.charAt(0)))) {
            szereg = new StringBuilder(decyzja).reverse().toString() + " | " + szereg;
            gracz.usun_kostke_z_zestawu(decyzja);
            gui.polozDomino(decyzja, "f", true);
            return true;
        } else if (szereg.startsWith(String.valueOf(decyzja.charAt(2)))) {
            szereg = decyzja + " | " + szereg;
            gracz.usun_kostke_z_zestawu(decyzja);
            gui.polozDomino(decyzja, "f", false);
            return true;
        } else {
            return false;
        }
    }

    public boolean dobieranie(Gracz gracz){
        if (kostki.sprawdz_ilosc_kostek_do_dobierania() == 0) {
            if(gracz.nick.equals("Gracz 1")){
                zwyciezca = gracz1;
            } else {
                zwyciezca = gracz0;
            }
            koniec = true;
        } else {
            kostki.dobierz_kostke(gracz);
            gui.ustawKostkiPozostale(kostki.sprawdz_ilosc_kostek_do_dobierania());
                System.out.println(gracz.pokaz_kostki());
        }
            gracz.odswiez_zestaw();
        return true;
    }
}
