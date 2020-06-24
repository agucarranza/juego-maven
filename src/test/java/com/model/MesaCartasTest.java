package com.model;

import junit.framework.TestCase;

public class MesaCartasTest extends TestCase {
    MesaCartas mesa;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mesa = new MesaCartas(2);
    }

    public void testAgregarCarta() {
        mesa.agregarCarta(new Carta(1,1));
        mesa.agregarCarta(new Carta(2,2));
        assertEquals(2, mesa.getCartas().size());
    }
}