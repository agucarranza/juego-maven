package com.model;

import com.view.VistaPartida;
import junit.framework.TestCase;

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
            assertEquals(5, usuario.getMyMano().size());
        }
    }

    public void testProcesarBaza() {
        jugada.repartirManos();
        modelo.bajarALaMesa(modelo.usuarios.get(0),1);
        modelo.bajarALaMesa(modelo.usuarios.get(1),1);
        Baza baza = new Baza(1);
        baza.setGanadorBehavior(new GanadorMinimo());
        jugada.procesarBaza(1,baza);
        assertEquals(new Usuario(1).getTipoUsuario(), modelo.usuarios.get(0).getTipoUsuario());





    }
}