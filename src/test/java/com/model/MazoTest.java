package com.model;

import junit.framework.TestCase;

public class MazoTest extends TestCase {
    MazoUtils mazoUtils = MazoUtils.getInstance();
    public void setUp() throws Exception {
        super.setUp();
    }



    public void testRepartir() {
        Carta carta = mazoUtils.repartir();
        assertTrue(carta.getPalo() >0 && carta.getNumero() >0);
    }
}