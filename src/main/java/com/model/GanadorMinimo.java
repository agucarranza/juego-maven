package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

public class GanadorMinimo implements GanadorBehavior {

    @Override
    public Carta seleccionarGanadorBehavior(ArrayList<Carta> cartasTriunfo, ArrayList<Carta> cartasBaza) {
        Carta cartaGanadora;
        if (!cartasTriunfo.isEmpty())  //Hay cartas que tienen triunfo?
            cartaGanadora = Collections.min(cartasTriunfo, Comparator.comparing(Carta::getNumero));
        else if (cartasBaza.size() > 0)  //Hay por lo menos una carta del palo de la baza?
            cartaGanadora = Collections.min(cartasBaza, Comparator.comparing(Carta::getNumero));
        else
            throw new InputMismatchException("El palo de la baza no coincide con las cartas analizadas");
        return cartaGanadora;
    }
}