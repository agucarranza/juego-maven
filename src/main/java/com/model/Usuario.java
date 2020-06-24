package com.model;

import java.util.ArrayList;

public class Usuario {
    public static final int HUMANO = 1;
    public static final int ROBOT = 0;

    protected ArrayList<Carta> myMano;
    private final ArrayList<Baza>  myBazas;
    private final int tipoUsuario;
    private boolean pie = false;
    private int puntos = 0;


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

    public void agregarPunto() {puntos++;}

    public int getPuntos() {return puntos;}

    public boolean isPie() {return pie;}

    public void setPie(boolean pie) {this.pie = pie;}

    @Override
    public String toString() { return "" + tipoUsuario; }

    public ArrayList<Carta> getMyMano() {
        return myMano;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public ArrayList<Baza> getMyBazas() {
        return myBazas;
    }
}
