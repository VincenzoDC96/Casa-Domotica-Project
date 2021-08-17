package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class InterfacciaAttiva implements IObservable {

    //DICHIARAZIONE OGGETTI E VARIABILI

    List<IObserver> observers = new ArrayList<>();
    private final JFrame frame;
    private final JPanel panel;
    private final JButton attivo, disattivo, indietro;
    private int state;



    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        Notify();
    }


    public void add(IObserver o) {
        this.observers.add(o);
    }


    public void Notify() {
        for (IObserver o : observers)
            o.update(state);
    }


    public InterfacciaAttiva() {

        //CREAZIONE OGGETTI


        indietro = new JButton("Torna indietro");
        indietro.addActionListener(new ButtonListener());
        attivo = new JButton("Attivazione sensori");
        attivo.addActionListener(new ButtonListener());
        disattivo = new JButton("Disattivazione sensori");
        disattivo.addActionListener(new ButtonListener());
        panel = new JPanel();
        frame = new JFrame();

        //SETTAGGI DEL FRAME

        frame.setTitle("Modalità attiva");

        /*Se non viene settata l'immagine con successo, sostituire il percorso tra ().
        Fare click destro su "logocasa.jpg" -> Copy Path -> Incollare tra le ().*/

        ImageIcon image = new ImageIcon("C:\\Users\\vince\\Intellij Projects\\CasaDomotica\\src\\com\\company\\logocasa.jpg");
        frame.setIconImage(image.getImage());
        frame.setBounds(0, 0, 500, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        //SETTAGGI DEL PANNELLO

        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 300);
        panel.setBackground(new Color(0,155,255));
        panel.add(attivo);
        panel.add(disattivo);
        panel.add(indietro);

        //SETTAGGIO DEI BOTTONI

        attivo.setBounds(40, 25, 200, 100);
        attivo.setBackground(new Color(235,255,255));
        disattivo.setBounds(240, 25, 200, 100);
        disattivo.setBackground(new Color(235,255,255));
        indietro.setBounds(40, 125, 400, 100);
        indietro.setBackground(new Color(235,255,255));

        //RENDIAMO IL TUTTO VISIBILE

        frame.setVisible(true);

    }



    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == attivo) {
                new Esecutore();    //UTILIZZO DEL OBSERVER PATTERN
                System.out.println("Sensori attivati!");
                frame.dispose();
            } else if (e.getSource() == disattivo) {
                new Esecutore2();   //UTILIZZO DEL OBSERVER PATTERN
                System.out.println("Sensori disattivati!");
                frame.dispose();
            } else if (e.getSource() == indietro) {
                frame.dispose();
               new SelezioneModalità();
            }
       }
    }

}