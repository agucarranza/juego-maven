package com.controller;

import com.model.ModeloPartida;
import com.view.VistaPartida;
import junit.framework.TestCase;

import static com.controller.ControllerPartida.*;

public class ControllerPartidaTest extends TestCase {
    ModeloPartida modelo;
    VistaPartida vista;
    ControllerPartida partida;

    public void setUp() throws Exception {
        super.setUp();
        modelo = new ModeloPartida();
        vista = new VistaPartida(modelo);
        partida = new ControllerPartida(vista, modelo);
    }

    public void tearDown()  {
    }

   public void testMouseClicked() {

   }
 }