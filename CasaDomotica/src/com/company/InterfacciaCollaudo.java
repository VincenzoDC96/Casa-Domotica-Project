package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class InterfacciaCollaudo implements IObserver {

    //DICHIARAZIONE OGGETTI E VARIABILI

    private JButton indietro, addSensore, deleteSensori, printStats, addComponent;
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    Random random = new Random();
    private int cTelecamera = 0;
    private int numero,meteo = 0;
    private int cTapparella = 0;
    private InterfacciaAttiva IAttiva; //UTILIZZO DEL OBSERVER PATTERN
   private int state;


    public InterfacciaCollaudo() {

        //CREAZIONE OGGETTI

        frame = new JFrame();
        panel = new JPanel();
        label = new JLabel();
        addSensore = new JButton("Aggiungi sensore");
        addSensore.addActionListener(new ButtonListener());
        deleteSensori = new JButton("Rimuovi tutti i sensori");
        deleteSensori.addActionListener(new ButtonListener());
        printStats = new JButton("Mostra statistiche");
        printStats.addActionListener(new ButtonListener());
        indietro = new JButton("Torna indietro");
        indietro.addActionListener(new ButtonListener());
        addComponent = new JButton("Aggiungi componente ad un sensore");
        addComponent.addActionListener(new ButtonListener());

        //SETTAGGI DEL FRAME

        frame.setTitle("Modalità collaudo");
        frame.setBounds(0, 0, 900, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*Se non viene settata l'immagine con successo, sostituire il percorso tra ().
        Fare click destro su "logocasa.jpg" -> Copy Path -> Incollare tra le ().*/

        ImageIcon image = new ImageIcon("C:\\Users\\vince\\Intellij Projects\\CasaDomotica\\src\\com\\company\\logocasa.jpg");
        frame.setIconImage(image.getImage());
        frame.add(panel);

        //SETTAGGI DEL PANNELLO

        panel.setLayout(new GridLayout(2, 3));
        panel.setBackground(new Color(235, 235, 235));
        panel.add(addSensore);
        panel.add(addComponent);
        panel.add(indietro);
        panel.add(deleteSensori);
        panel.add(printStats);
        panel.add(label);

        //SETTAGGI DEI BOTTONI

        addSensore.setBackground(new Color(235, 255, 255));
        addComponent.setBackground(new Color(235, 255, 255));
        indietro.setBackground(new Color(235, 255, 255));
        deleteSensori.setBackground(new Color(235, 255, 255));
        printStats.setBackground(new Color(235, 255, 255));

        //SETTAGGI DELLE ETICHETTE

        label.setForeground(new Color(0, 155, 255));
        label.setText("DoHome - The home that Does :)");
        label.setFont(new Font("MV Boli", Font.BOLD, 17));

        //RENDIAMO IL TUTTO VISIBILE

        frame.setVisible(true);

        //-----------------------------CONTROLLO LO STATO ATTUALE-----------------------------------------//
        Scanner s= null;
        try {
            s = new Scanner(new File("Stato.txt"));
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File non trovato! Aggiungere <Stato.txt>");
        }while (true) {
            assert s != null;
            if (!s.hasNextLine()) break;
            String lineState = s.nextLine();
            if (lineState.matches("1")){
                printStats.setEnabled(true);
            }
            else if (lineState.matches("2")){
                printStats.setEnabled(false);
            }
        }
    }

    public InterfacciaCollaudo(InterfacciaAttiva IAttiva) { //   UTILIZZO DEL OBSERVER PATTERN
        this.IAttiva = IAttiva;
    }

    public void update(int state){//  UTILIZZO DEL OBSERVER PATTERN
        this.state = state;

        int s = state;
        FileWriter w = null;        //LEGGIAMO IL FILE
        try {
            w = new FileWriter("Stato.txt");
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println("File non trovato!Aggiungere <Stato.txt>");
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
        }
    }


    public void setNumero(int numero) {
        this.numero = numero;
    } //Metodo usato per i gradi del termometro

    public int getNumero() {
        return numero;
    }  //Metodo usato per i gradi del termometro

    public void setCTelecamera(int cTelecamera) {   //Metodo usato per la simulazione della telecamera
        this.cTelecamera=cTelecamera;
}
    public int getCtelecamera() {
        return cTelecamera;
    } //Metodo usato per la simulazione della telecamera

    public void setcTapparella(int cTapparella){
        this.cTapparella=cTapparella;
    } //Metodo usato per la simulazione della tapparella

    public int getcTapparella(){
        return cTapparella;
    } //Metodo usato per la simulazione della tapparella

    public void setMeteo(int meteo){
        this.meteo=meteo;
    }   //Metodo usato per la simulazione di un evento meteorologico

    public int getMeteo(){
        return meteo;
    }   //Metodo usato per la simulazione di un evento meteorologico

    private class ButtonListener implements ActionListener {


        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == indietro) {
                frame.dispose();
                new SelezioneModalità();

            } else if (e.getSource() == addSensore) {
                frame.dispose();
                new AggiungiSensore();

            } else if (e.getSource() == deleteSensori) {
                FileWriter w = null;        //LEGGIAMO IL FILE
                try {
                    w = new FileWriter("Sensori.txt");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("File non trovato!Aggiungere <Sensori.txt>");
                }
                BufferedWriter b;
                b = new BufferedWriter(w);        //SCRIVIAMO SUL FILE
                try {
                    b.write("");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("Scrittura non riuscita!");
                }
                try {
                    b.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("File non chiuso!");
                }
                System.out.println("Tutti i sensori sono stati resettati!");

            } else if (e.getSource() == addComponent) {
                frame.dispose();
                new AggiungiComponente();

            } else if (e.getSource() == printStats) {     //<<<<<<<<<----APPLICAZIONE OBSERVER PATTERN

                cTelecamera = 1+ random.nextInt(2); //SETTIAMO UN VALORE CASUALE TRA 1 E 2 PER SIMULARE LA TELECAMERA
                setCTelecamera(cTelecamera);

                numero = 20 + random.nextInt(15); //SETTIAMO UN VALORE AL TERMOMETRO PER SIMULARNE L'UTILIZZO
                setNumero(numero);

                meteo = random.nextInt(3); // SETTIAMO UN VALORE PER LA CONDIZIONE METEOROLOGICA
                setMeteo(meteo);

                Scanner r = null;   //LEGGIAMO IL FILE
                try {
                    r = new Scanner(new File("Sensori.txt"));

                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("File non trovato! Aggiungere <Sensori.txt>");
                }
                while (true) {
                    assert r != null;
                    if (!r.hasNextLine()) break;
                    String line = r.nextLine();

                    if (line.matches("Termometro")) { // CASO IN CUI E' PRESENTE UN TERMOMETRO
                        System.out.println("Termomentro segnala: " + getNumero() + "gradi.");

                    }
                    else if (line.matches("Termometro: Deumidificatore")) { // CASO IN CUI VIENE AGGIUNTO UN SISTEMA DI DEUMIDIFICAZIONE
                        System.out.println("Termometro: Stanza deumidificata.");

                    }
                    else if (line.matches("Termometro: Meteo") && getMeteo()==0) {
                        System.out.println("Termomentro segnala bel tempo!");

                    }
                    else if (line.matches("Termometro: Meteo") && getMeteo()==1) {
                        System.out.println("Termomentro segnala pioggia!");

                    }
                    else if (line.matches("Termometro: Meteo") && getMeteo()==2) {
                        System.out.println("Termomentro segnala neve!");

                    }

                    else if (line.matches("Telecamera")&& getCtelecamera() == 1) { //CASO IN CUI NESSUNO VIENE INDIVIDUATO DALLA TELECAMERA
                        System.out.println("Telecamera: Nessuna persona individuata.");
                        setcTapparella(1);

                    }
                    else if (line.matches("Telecamera: Audio")&& getCtelecamera() == 1) { //CASO IN CUI NESSUNO VIENE INDIVIDUATO ED E' PRESENTE L'AUDIO ALLA TELECAMERA
                        System.out.println("Telecamera: Nessun suono registrato.");

                    }
                    else if (line.matches("Telecamera") && getCtelecamera() == 2) { //CASO IN CUI QUALCUNO VIENE INDIVIDUATO DALLA TELECAMERA
                        System.out.println("Telecamera: Persona individuata!");
                        setcTapparella(1);

                    }
                    else if (line.matches("Telecamera: Audio") && getCtelecamera() == 2) { //CASO IN CUI QUALCUNO VIENE INDIVIDUATO ED E' PRESENTE L'AUDIO ALLA TELECAMERA
                        System.out.println("Telecamera: Audio in registrazione!");

                    }
                    else if (line.matches("Tapparella")&& getCtelecamera() == 2 && getcTapparella()==1) { //CASO IN CUI QUALCUNO VIENE INDIVIDUATO DALLA TELECAMERA E SI HANNO DELLE TAPPARELLE AUTOMATICHE
                      Allarme.getInstance();
                    }
                }

                    r.close();
                }
            }
        }


}