package com;

import com.controller.ControllerConsola;
import com.controller.ControllerPartida;
import com.model.ModeloPartida;
import com.view.VistaConsola;
import com.view.VistaPartida;

/**
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ModeloPartida modelo = new ModeloPartida();
        modelo.startJugada();
       // GanadorBehavior ganadorBehavior = new GanadorMaximo();

        VistaPartida vista = new VistaPartida(modelo);
        VistaConsola vistaConsola = new VistaConsola(modelo);

        new ControllerPartida(vista, modelo);
        new ControllerConsola(vistaConsola, modelo);

        vista.setVisible(true);
        vistaConsola.setVisible(true);
    }
}
