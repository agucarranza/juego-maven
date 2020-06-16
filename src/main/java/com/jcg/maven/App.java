package com.jcg.maven;

/**
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ModeloPartida modelo = new ModeloPartida();
        modelo.startJugada();
        VistaPartida vista = new VistaPartida(modelo);
        new ControllerPartida(vista, modelo, new GanadorMaximo());
        vista.setVisible(true);

        VistaConsola vistaConsola = new VistaConsola(modelo);
        ControllerConsola controllerConsola = new ControllerConsola(vistaConsola, modelo);
       // vistaConsola.setVisible(true);
    }
}
