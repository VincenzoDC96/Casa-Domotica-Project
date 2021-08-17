package com.company;

public class Esecutore {
    InterfacciaAttiva attiva;
    InterfacciaCollaudo collaudo;
    public Esecutore(){
       attiva = new InterfacciaAttiva();
       collaudo = new InterfacciaCollaudo(attiva);
       attiva.add(collaudo);
       attiva.setState(1);
    }

}
