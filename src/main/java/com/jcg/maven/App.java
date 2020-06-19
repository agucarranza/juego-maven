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
        GanadorBehavior ganadorBehavior = new GanadorMaximo();

        VistaPartida vista = new VistaPartida(modelo);
        VistaConsola vistaConsola = new VistaConsola(modelo);

        new ControllerPartida(vista, modelo, ganadorBehavior);
        new ControllerConsola(vistaConsola, modelo, ganadorBehavior);

        vista.setVisible(true);
        vistaConsola.setVisible(true);
    }
}
