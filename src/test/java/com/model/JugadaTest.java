package com.model;

import com.view.VistaPartida;
import junit.framework.TestCase;

import java.util.ArrayList;

public class JugadaTest extends TestCase {
    ModeloPartida modelo;
    Jugada jugada;

    public void setUp() throws Exception {
        super.setUp();
        modelo = new ModeloPartida();
        modelo.agregarObserver(new VistaPartida(modelo));
        modelo.startJugada();
        jugada = modelo.getJugadaActiva();
    }

    public void testRepartirManos() {
        jugada.repartirManos();
        for (Usuario usuario: modelo.getUsuarios()
             ) {
            assertTrue(5 == usuario.getMyMano().size());
        }
    }
}