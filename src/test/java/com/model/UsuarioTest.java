package com.model;

import junit.framework.TestCase;

public class UsuarioTest extends TestCase {
    Usuario usuario;

    public void setUp() throws Exception {
        super.setUp();
        usuario = new Usuario(1);
    }

    public void testAgregarBaza() {
        usuario.agregarBaza(new Baza(1));
        assertEquals(1,usuario.getMyBazas().size());
    }

    public void testRecibirCarta() {
        int tamanoAntes = usuario.getMyMano().size();
        usuario.recibirCarta(new Carta(2,2));
        assertEquals(tamanoAntes+1,usuario.getMyMano().size());
    }

    public void testDescartar() {
        usuario.recibirCarta(new Carta(1,1));
        int tamanoAntes = usuario.getMyMano().size();
        usuario.descartar(0);
        assertEquals(tamanoAntes-1,usuario.getMyMano().size());
    }

    public void testAgregarPunto() {
    }
}