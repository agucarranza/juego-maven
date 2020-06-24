package com.model;

import java.util.ArrayList;

public class Baza {
    protected final ArrayList<Carta> cartas;
    protected Usuario owner = null;
    protected int paloBaza = -1;
    protected final int paloTriunfo;
    private GanadorBehavior ganadorBehavior = null; //GanadorMinimo(); //GanadorMaximo();

    public Baza(int paloTriunfo) {
        cartas = new ArrayList<>();
        this.paloTriunfo = paloTriunfo;
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
        if (cartas.size() == 1)
            paloBaza = cartas.get(0).getPalo();  // Palo de la primera carta.
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
        cartaGanadora = ganadorBehavior.seleccionarGanadorBehavior(cartasTriunfo, cartasBaza);
        this.owner = cartaGanadora.getOwner();
        return owner;
    }

    public int getPaloBaza() {
        return paloBaza;
    }

    public void setGanadorBehavior(GanadorBehavior ganadorBehavior) {
        this.ganadorBehavior = ganadorBehavior;
    }

    @Override
    public String toString() {
        return "Baza{" +
                "cartas=" + cartas +
                ", owner=" + owner +
                '}';
    }
}
