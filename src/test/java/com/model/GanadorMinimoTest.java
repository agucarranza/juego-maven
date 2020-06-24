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
        ganadorBehavior = new GanadorMaximo();
    }

    public void testSeleccionarGanadorBehavior() {

    }
}