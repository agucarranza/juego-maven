package com.jcg.maven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Robot extends Usuario {

    public Robot() {
        super(Usuario.ROBOT);
    }

    public Carta elegirCartaTirar(int paloTriunfo, int paloBaza) throws Exception {

        ArrayList<Carta> cartasTriunfo = new ArrayList<>();
        ArrayList<Carta> cartasBaza = new ArrayList<>();

        for (Carta carta: myMano) {
            if (carta.getPalo() == paloTriunfo)
                cartasTriunfo.add(carta);
            if (carta.getPalo() == paloBaza)
                cartasBaza.add(carta);
        }

        if (!cartasTriunfo.isEmpty())  //Hay cartas que tienen triunfo? {
            return Collections.max(cartasTriunfo, Comparator.comparing(Carta::getNumero));

        else if (cartasBaza.size() > 0)  //Hay por lo menos una carta del palo de la baza?
            return Collections.max(cartasBaza, Comparator.comparing(Carta::getNumero));
        else
            throw new Exception("El palo de la baza no coincide con las cartas analizadas");

    }
}
