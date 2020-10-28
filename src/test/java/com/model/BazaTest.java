package com.model;

import junit.framework.TestCase;

public class BazaTest extends TestCase {
    Baza baza;
    public void setUp() throws Exception {
        super.setUp();
        baza = new Baza(1);
    }

    public void testAgregarCarta() {
        baza.agregarCarta(new Carta(1,1));
        assertEquals(1, baza.cartas.size());
    }

    public void testCalcularGanador() {
        Carta carta = new Carta(1,1);
        carta.setOwner(new Usuario(1));
        baza.agregarCarta(carta);

        baza.setGanadorBehavior(new GanadorMinimo());
        baza.calcularGanador();
        assertEquals(1, baza.calcularGanador().getTipoUsuario());
    }

    public void testGetPaloBaza() {
        Carta carta = new Carta(1,1);
        baza.agregarCarta(carta);
        assertEquals(1, baza.getPaloBaza());
    }

    public void testSetGanadorBehavior() {
        Carta carta = new Carta(1,1);
        carta.setOwner(new Usuario(1));
        baza.agregarCarta(carta);
        baza.setGanadorBehavior(new GanadorMinimo());
        assertNotNull(baza.calcularGanador());
    }
}