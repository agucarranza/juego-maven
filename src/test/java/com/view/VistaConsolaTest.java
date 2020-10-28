package com.view;


import com.model.ModeloPartida;
import junit.framework.TestCase;

public class VistaConsolaTest extends TestCase {
    VistaConsola vista;
    ModeloPartida modelo;

    public void setUp() throws Exception {
        super.setUp();
        modelo = new ModeloPartida();
        vista = new VistaConsola(modelo);

    }


    public void testGetEntrada() {
        assertNotNull(vista.getEntrada());
    }


    public void testDisplayErrorMessage() {
        vista.displayErrorMessage();

    }

    public void testUpdate() {
    }

    public void testUpdateMano() {

        modelo.startJugada();
        modelo.getJugadaActiva().repartirManos();
        vista.updateMano();

    }

    public void testUpdateMesa() {
        assertTrue(true);
    }

    public void testUpdateManoPC() {
        assertTrue(true);
    }
}