package com.jcg.maven;


public class ControllerPartida implements Controller {
    //Solucionar problema con GanadorBehavior, para que lo puedan usar los 2 metodos a la vez y estos modifiquen el mismo objeto
    public ControllerPartida(VistaPartida vista, ModeloPartida modelo, GanadorBehavior ganadorBehavior) {
        vista.addRepartirListener(new RepartirListener(vista, modelo));
        vista.addJuegaPCListener(new JuegaPCListener(vista, modelo, ganadorBehavior));
        vista.addNuevaBazaListener(new NuevaBazaListener(vista, modelo));
        vista.addNuevaJugadaListener(new NuevaJugadaListener(vista, modelo));
        vista.addInvertirValoresListener(new InvertirValoresListener(vista, modelo, ganadorBehavior));
    }
}
