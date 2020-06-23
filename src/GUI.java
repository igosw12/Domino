import javax.swing.*;
import java.util.ArrayList;

public class GUI extends JFrame{
    public JPanel jpanel;
    public JPanel jpanelTop;
    public JPanel jpanelCenter;
    public JPanel jpanelBottom;
    public JButton Dobierz;
    public JPanel jpanelHand;
    public JPanel jpanelRight;
    public JLabel pozostaleKostki;
    public JLabel aktualnyGracz;
    public ArrayList<Gracz> allPlayers;

    private String zagraj = "";

    public GUI(){
        super("Domino");
        setContentPane(jpanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setVisible(true);
        Dobierz.addActionListener(e -> zagraj = "dobierz");
        revalidate();
        repaint();
    }

    public void polozDomino(String kostka, String miejsce, boolean odwroc){
        ImageIcon image = new ImageIcon(this.getClass().getResource("/img/" + kostka + ".png"));
        RotatedIcon ri = new RotatedIcon(image, RotatedIcon.Rotate.UPSIDE_DOWN);
        JLabel picLabel = new JLabel();
        if(odwroc) {
            picLabel.setIcon(ri);
        } else {
            picLabel.setIcon(image);
        }
        if(miejsce.equals("r")) {
            jpanelCenter.add(picLabel);
        } else if (miejsce.equals("f")){
            jpanelCenter.add(picLabel, 0);
        }
        revalidate();
        repaint();
    }

    public String zagraj(){
        while (zagraj.isEmpty()){
            try {
                Thread.sleep(200);
            } catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        String odpowiedz = zagraj;
        zagraj = "";
        return odpowiedz;
    }

    public void wyczyscZestaw(){
        jpanelHand.removeAll();
        revalidate();
        repaint();
    }

    public void dodajZestaw(String kostka){
        JButton button = new JButton();
        ImageIcon image = new ImageIcon(this.getClass().getResource("/img/" + kostka + ".png"));
        RotatedIcon ri = new RotatedIcon(image, RotatedIcon.Rotate.DOWN);

        button.setIcon(ri);
        button.addActionListener(e -> zagraj = kostka);
        jpanelHand.add(button);
        revalidate();
        repaint();
    }

    public void ustawKostkiPozostale(int kostki_do_dobrania){
        pozostaleKostki.setText(Integer.toString(kostki_do_dobrania));
        revalidate();
        repaint();
    }

    public void ustawAktualnegoGracza(String aktGracz){
        aktualnyGracz.setText(aktGracz);
        revalidate();
        repaint();
    }
    /*public void Demo(){
        JLabel background;
        setSize(800,600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon(this.getClass().getResource("/img/" + "133153.jpg"));
        background = new JLabel("" ,img, JLabel.CENTER);
    background.setBounds(0,0,800,600);
    add(background);
        setVisible(true);
    }
     */
}

