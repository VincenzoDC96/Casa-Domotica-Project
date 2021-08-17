package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AggiungiSensore {

    //DICHIARAZIONE OGGETTI

    private final JButton add;
    private final JFrame frame;
    private final JLabel label, label1;
    private final JPanel panel, panel1;
    private final JComboBox comboBox;

    public AggiungiSensore() {

        //CREAZIONE OGGETTI

        frame = new JFrame();
        panel = new JPanel();
        panel1 = new JPanel();
        label = new JLabel();
        label1 = new JLabel();
        add = new JButton("Aggiungi");
        add.addActionListener(new ButtonListener());
        String [] sens = {"Telecamera","Tapparella","Termometro"};
        comboBox = new JComboBox(sens);

        //SETTING DEL FRAME ED AGGIUNTA DEI PANNELLI

        /*Se non viene settata l'immagine con successo, sostituire il percorso tra ().
        Fare click destro su "logocasa.jpg" -> Copy Path -> Incollare tra le ().*/

        ImageIcon image = new ImageIcon("C:\\Users\\vince\\Intellij Projects\\CasaDomotica\\src\\com\\company\\logocasa.jpg");
        frame.setIconImage(image.getImage());
        frame.setTitle("Pannello di aggiunta sensori");
        frame.setBounds(0, 0, 400, 200);
        frame.setResizable(false);
        frame.add(panel);
        frame.add(panel1);

        //SETTING DEI PANNELLI E AGGIUNTA DELLE ETICHETTE

        panel.setBounds(0,0,400,100);
        panel1.setBounds(0,100,400,100);
        panel1.setBackground(new Color(235,235,235));
        panel1.setLayout(null);
        panel.setBackground(new Color(235,235,235));
        panel.setLayout(null);
        panel.add(label);
        panel1.add(label1);

        //SETTING DELLE ETICHETTE ----------

        label1.setText("DoHome - The home that Does :)");
        label1.setForeground(new Color(0,155,255));
        label1.setFont(new Font("MV Boli",Font.BOLD,20));
        label1.setBounds(-15,80,400,100);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(-30,0,400,100);
        Image logo = image.getImage();
        Image logoscaled = logo.getScaledInstance(95,75,Image.SCALE_AREA_AVERAGING);
        ImageIcon logoscalato = new ImageIcon(logoscaled);
        label.setIcon(logoscalato);

        //AGGIUNGIAMO I COMPONENTI AL PANNELLO

        panel.add(add);
        panel.add(comboBox);

        //SETTING DEL BOTTONE E DEL COMBOBOX---------
        comboBox.setBackground(new Color(235,255,255));
        comboBox.setBounds(240,0,150,100);
        add.setBackground(new Color(235,255,255));
        add.setBounds(0,0,100,100);

        //SETTING FINALI DEL FRAME

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==add){

                //ASSOCIAMO LA SCELTA DEL COMBOBOX AD UNA STRINGA

                String s = (String)comboBox.getSelectedItem();
                FileWriter w = null;        //LEGGIAMO IL FILE
                try {
                    w = new FileWriter("Sensori.txt", true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("File non trovato!Aggiungere <Sensori.txt>");
                }
                BufferedWriter b;
                b=new BufferedWriter(w);        //SCRIVIAMO SUL FILE
                try {
                    b.write(s + "\n");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("Scrittura non riuscita!");
                }
                try {
                    b.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("File non chiuso!");
                }finally {
                    System.out.println("Sensore aggiunto: "+s);
                    frame.dispose();
                    new InterfacciaCollaudo();
                }
            }

        }
    }
}
