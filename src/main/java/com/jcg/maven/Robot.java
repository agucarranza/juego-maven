package com.jcg.maven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

public class Robot extends Usuario {

    public Robot() {
        super(Usuario.ROBOT);
    }

    public int elegirCartaTirar(int paloTriunfo, int paloBaza) {

        ArrayList<Carta> cartasTriunfo = new ArrayList<>();
        ArrayList<Carta> cartasBaza = new ArrayList<>();

        for (Carta carta : myMano) {
            if (carta.getPalo() == paloTriunfo)
                cartasTriunfo.add(carta);
            if (carta.getPalo() == paloBaza)
                cartasBaza.add(carta);
        }

        Carta carta;
        if (!cartasTriunfo.isEmpty()) { //Hay cartas que tienen triunfo?
            carta = Collections.max(cartasTriunfo, Comparator.comparing(Carta::getNumero));
            return myMano.indexOf(carta);
        }

        else if (cartasBaza.size() > 0) { //Hay por lo menos una carta del palo de la baza?
            carta = Collections.max(cartasBaza, Comparator.comparing(Carta::getNumero));
            return myMano.indexOf(carta);
        }
        else
            throw new InputMismatchException("El palo de la baza no coincide con las cartas analizadas");
    }
}
