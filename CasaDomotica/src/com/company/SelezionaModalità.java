package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SelezioneModalità    {

    private final JButton collaudo, attivo;
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel label;


    public SelezioneModalità (){

        //AGGIUNTA DI COMPONENTI

        frame = new JFrame();
        panel = new JPanel();
        label = new JLabel("DoHome - The home that Does :)");
        collaudo = new JButton("Modalità collaudo");
        attivo = new JButton("Modalità attiva");

        //SETTING FRAME E BOTTONI

        collaudo.addActionListener(new ButtonListener());
        attivo.addActionListener(new ButtonListener());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        panel.add(collaudo);
        panel.add(attivo);
        panel.add(label);

        //SETTING COLORI E DIMENSIONI

        /*Se non viene settata l'immagine con successo, sostituire il percorso tra ().
        Fare click destro su "logocasa.jpg" -> Copy Path -> Incollare tra le ().*/

        ImageIcon image = new ImageIcon("C:\\Users\\vince\\Intellij Projects\\CasaDomotica\\src\\com\\company\\logocasa.jpg");

        frame.setTitle("Pannello di selezione");
        frame.setSize(400,300);
        frame.setIconImage(image.getImage());
        panel.setBounds(0,0,400,300);
        panel.setBackground(new Color(0,155,255));
        attivo.setBackground(new Color(235,255,255));
        collaudo.setBackground(new Color(235,255,255));
        panel.setLayout(new GridLayout(3,1));
        label.setFont(new Font("MV Boli",Font.BOLD,20));

        //RENDIAMO VISIBILE IL TUTTO

        frame.setVisible(true);
    }


    private class ButtonListener implements ActionListener {


        public void actionPerformed(ActionEvent e) {

            if (e.getSource()==collaudo) {
                frame.dispose();
                new InterfacciaCollaudo();

            } else if (e.getSource()==attivo){
                frame.dispose();
                new InterfacciaAttiva();

            }

        }
    }
}