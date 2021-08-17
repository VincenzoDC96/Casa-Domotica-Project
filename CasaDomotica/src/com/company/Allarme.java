package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Allarme {
    private static Allarme unico = new Allarme();
    LinkedList<String> listaAllarmi = new LinkedList();
    int nTapp,coda;
    private Allarme()  {

        Scanner r = null;
        try {
            Scanner s = new Scanner(new File("Sensori.txt"));
            r = s;

        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("File non trovato! Aggiungere <Sensori.txt>");
        }

        while(r.hasNextLine()){
            String line= r.nextLine();
                if (line.matches("Tapparella")) {
                    nTapp++;
                    listaAllarmi.add("Tapparella n."+nTapp);
                }
                }

        System.out.println(listaAllarmi+" numero elementi: "+listaAllarmi.size());
                    coda=nTapp;
                for  (int i=1;i<=nTapp;i++){
                        System.out.println("Tapparella n."+i+" si sta abbassando!");
                        listaAllarmi.poll();               //ELIMINIAMO IL PRIMO ELEMENTO DALLA CODA
                    coda--;

                    try {
                        TimeUnit.SECONDS.sleep(2);
                        if (coda==0){
                            System.out.println("Tutte le tapparelle sono state abbassate!");
                            break;
                        }
                        System.out.println(listaAllarmi+ "numero elementi: "+coda);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

    }


    public static Allarme getInstance(){
        if (unico==null){
            unico= new Allarme();
        }
        return unico;
    }

}


