package com.model;

import java.util.ArrayList;

public class MesaCartas {

    private final ArrayList<Carta> cartas;
    private final int cantUsuarios;


    public MesaCartas(int cantUsuarios) {
        cartas = new ArrayList<>();
        this.cantUsuarios = cantUsuarios;
        System.out.println("Mesa creada");
    }

    public void agregarCarta(Carta carta){
        if (cantUsuarios <= cartas.size())
            throw new IndexOutOfBoundsException("La mesa esta llena");
        else
            cartas.add(carta);
    }


    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    @Override
    public String toString() {
        return "|| Cartas en la Mesa ||\n" + cartas;
    }
}



