package com.jcg.maven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

public class Baza {
    private ArrayList<Carta> cartas;
    private Usuario owner = null;
    private int paloBaza = -1;
    private final int paloTriunfo;

    public Baza(int paloTriunfo) {
        cartas = new ArrayList<>();
        this.paloTriunfo = paloTriunfo;
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
        if (cartas.isEmpty())
            paloBaza = cartas.get(0).getPalo();  // Palo de la primera carta.
    }

    public Usuario getOwner() {
        if (owner == null) {
            throw new NullPointerException("Todavia no hay un ganador.");
        }
        else
            return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }

    /**
     * Funcion que calcula el Usuario ganador de una baza una vez que ha terminado.
     * @return El jugador cuya carta es ganadora.
     */
    public Usuario calcularGanador() {
        ArrayList<Carta> cartasTriunfo = new ArrayList<>();
        ArrayList<Carta> cartasBaza = new ArrayList<>();
        Carta cartaGanadora;

        for (Carta carta: cartas) {
            if (carta.getPalo() == paloTriunfo)
                cartasTriunfo.add(carta);
            if (carta.getPalo() == paloBaza)
                cartasBaza.add(carta);
        }

        if (!cartasTriunfo.isEmpty())  //Hay cartas que tienen triunfo?
            cartaGanadora = Collections.max(cartasTriunfo, Comparator.comparing(Carta::getNumero));
        else if (cartasBaza.size() > 0)  //Hay por lo menos una carta del palo de la baza?
            cartaGanadora = Collections.max(cartasBaza, Comparator.comparing(Carta::getNumero));
        else
            throw new InputMismatchException("El palo de la baza no coincide con las cartas analizadas");

        this.owner = cartaGanadora.getOwner();
        return owner;
    }

    public int getPaloBaza() {
        return paloBaza;
    }

    @Override
    public String toString() {
        return "Baza{" +
                "cartas=" + cartas +
                ", owner=" + owner +
                '}';
    }
}
