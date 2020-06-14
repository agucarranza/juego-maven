package com.jcg.maven;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {


        ModeloPartida modelo = new ModeloPartida();

        Jugada jugada = modelo.startJugada();
        modelo.getJugadaActiva().repartirManos();
        VistaPartida vista = new VistaPartida(modelo);
        ControllerPartida controlador = new ControllerPartida(vista, modelo);

        vista.setVisible(true);
        modelo.estadisticasEvento();





    }
}
