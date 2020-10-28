package com.model;

import junit.framework.TestCase;

import java.util.ArrayList;

public class GanadorMinimoTest extends TestCase {

    private ArrayList<Carta> cartasTriunfo;
    private ArrayList<Carta> cartasBaza;
    GanadorBehavior ganadorBehavior;

    public void setUp() throws Exception {
        super.setUp();
        cartasTriunfo = new ArrayList<>();
        cartasBaza = new ArrayList<>();
        cartasBaza.add(new Carta(1,1));
        cartasTriunfo.add(new Carta(1,2));
        cartasTriunfo.add(new Carta(1,3));
        ganadorBehavior = new GanadorMinimo();
    }

    public void testSeleccionarGanadorBehavior() {
        Carta carta = ganadorBehavior.seleccionarGanadorBehavior(cartasTriunfo, cartasBaza);
        assertEquals(2,carta.getNumero());
    }

    public void testSeleccionarGanadorBehavior2() {
        cartasTriunfo.clear();
        cartasBaza.add(new Carta(1,1));
        Carta carta = ganadorBehavior.seleccionarGanadorBehavior(cartasTriunfo, cartasBaza);
        assertEquals(1,carta.getNumero());
    }
}