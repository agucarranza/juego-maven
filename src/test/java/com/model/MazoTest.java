package com.model;

import junit.framework.TestCase;

public class MazoTest extends TestCase {
    Mazo mazo = Mazo.getInstance();
    public void setUp() throws Exception {
        super.setUp();
    }



    public void testRepartir() {
        Carta carta = mazo.repartir();
        assertTrue(carta.getPalo() >0 && carta.getNumero() >0);
    }
}