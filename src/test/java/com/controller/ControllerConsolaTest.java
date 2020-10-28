package com.controller;

import com.model.ModeloPartida;
import com.view.VistaConsola;
import junit.framework.TestCase;


public class ControllerConsolaTest extends TestCase {



    public void testControllerController(){
        final ModeloPartida modelo = new ModeloPartida();
        final VistaConsola vista = new VistaConsola(modelo);
        new ControllerConsola(vista, modelo);

    }


}