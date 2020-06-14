package com.jcg.maven;

import java.util.ArrayList;

public class MesaCartas {

    private ArrayList<Carta> cartas;
    private int cantUsuarios;



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
        return "MesaCartas{" +
                "cartas=" + cartas +
                '}';
    }
}



