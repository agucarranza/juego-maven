package com.jcg.maven;

/**
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ModeloPartida modelo = ModeloPartida.getInstance();
        Jugada jugada = modelo.startJugada();


        VistaPartida vista = new VistaPartida(modelo);
        VistaConsola vistaConsola = new VistaConsola(modelo);

        ControllerPartida controllerPartida = new ControllerPartida(vista, modelo);
        ControllerConsola controllerConsola = new ControllerConsola(vistaConsola, modelo);

        vista.setVisible(true);
        vistaConsola.setVisible(true);

    }
}
