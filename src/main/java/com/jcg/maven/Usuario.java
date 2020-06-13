package com.jcg.maven;

import java.util.ArrayList;

public class Usuario {
    public static final int HUMANO = 1;
    public static final int ROBOT = 0;

    protected ArrayList<Carta> myMano;
    private ArrayList<Baza>  myBazas;
    private int tipoUsuario;
    private boolean pie = false;


    public Usuario(int tipo) {
        myMano = new ArrayList<>();
        myBazas = new ArrayList<>();
        tipoUsuario = tipo;
        System.out.println("Usuario tipo " + tipoUsuario +  " creado");
    }

    public void clearBazas() { myBazas.clear(); }

    public void agregarBaza(Baza baza) {
        if (myBazas.size() > 5)
            throw new ArrayIndexOutOfBoundsException();
        else
            myBazas.add(baza);
    }

    public void recibirCarta(Carta carta) {
        if (myMano.size() > 5)
            throw new ArrayIndexOutOfBoundsException("La mano esta llena");
        myMano.add(carta);
        carta.setOwner(this);
    }

    public Carta descartar(int index) {
        if (index < myMano.size())
            return myMano.remove(index);
        else throw new IndexOutOfBoundsException("Esa carta no existe.");
    }

    public boolean isPie() {return pie;}

    public void setPie(boolean pie) {this.pie = pie;}

    @Override
    public String toString() { return "" + tipoUsuario; }

    public ArrayList<Carta> getMyMano() {
        return myMano;
    }
}
