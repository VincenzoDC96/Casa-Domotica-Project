package com.company;

public class Esecutore2 {
    InterfacciaAttiva attiva;
    InterfacciaCollaudo collaudo;
    public Esecutore2(){
        attiva = new InterfacciaAttiva();
        collaudo = new InterfacciaCollaudo(attiva);
        attiva.add(collaudo);
        attiva.setState(2);
    }

}
