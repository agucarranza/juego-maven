package com.model;

import com.view.VistaPartida;
import junit.framework.TestCase;

import java.util.ArrayList;

public class ModeloPartidaTest extends TestCase {
    ModeloPartida modelo;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        modelo = new ModeloPartida();
        modelo.agregarObserver(new VistaPartida(modelo));
        modelo.startJugada();
        modelo.getJugadaActiva().repartirManos();
    }

    public void testBajarALaMesa() {
        modelo.bajarALaMesa(modelo.getUsuarioEnTurno(), 0);
        assertEquals(1,modelo.getMesaCartas().getCartas().size());
    }

    public void testToggleTurno() {
        Usuario usuario = modelo.getUsuarioEnTurno();
        modelo.toggleTurno();
        assertNotSame(usuario, modelo.getUsuarioEnTurno());
    }

    public void testLimpiarMesa() {
        modelo.bajarALaMesa(modelo.getUsuarioEnTurno(),0);
        int mesa = modelo.getMesaCartas().getCartas().size();
        modelo.limpiarMesa();
        assertTrue(mesa > modelo.getMesaCartas().getCartas().size());
    }

    public void testAsignarPuntoUsuario() {
        modelo.asignarPuntoUsuario();
        ArrayList<Usuario> usuarios = modelo.getUsuarios();
        for (Usuario usuario: usuarios
             ) {
            if (usuario.getPuntos() == 1) {
                assertTrue(true);
                break;
            }
            else fail();
        }
    }
}