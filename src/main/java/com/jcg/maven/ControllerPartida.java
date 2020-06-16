package com.jcg.maven;


public class ControllerPartida implements Controller {

    public ControllerPartida(VistaPartida vista, ModeloPartida modelo, GanadorBehavior ganadorBehavior) {
        vista.addRepartirListener(new RepartirListener(vista, modelo));
        vista.addJuegaPCListener(new JuegaPCListener(vista, modelo,ganadorBehavior));
        vista.addNuevaBazaListener(new NuevaBazaListener(vista, modelo));
        vista.addNuevaJugadaListener(new NuevaJugadaListener(vista, modelo));
    }
}
